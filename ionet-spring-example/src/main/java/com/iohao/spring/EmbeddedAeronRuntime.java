package com.iohao.spring;


import com.iohao.net.server.connection.DefaultUnavailableImageHandler;
import io.aeron.Aeron;
import io.aeron.CommonContext;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;
import org.agrona.concurrent.SleepingMillisIdleStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EmbeddedAeronRuntime implements DisposableBean {
    static final Logger log = LoggerFactory.getLogger(EmbeddedAeronRuntime.class);
    String aeronDirectoryName = "%s-%s".formatted(CommonContext.getAeronDirectoryName(), "ionet");

    public static final long DEBUG_CLIENT_TIMEOUT_NS = 600_000_000_000L;
    public static final long DEBUG_DRIVER_TIMEOUT_MS = 600_000L;
    public static final long DEBUG_UNBLOCK_TIMEOUT_NS = 900_000_000_000L;
    public static final long DEBUG_INTER_SERVICE_TIMEOUT_NS = DEBUG_CLIENT_TIMEOUT_NS + 1_000_000_000L;

    static {
        System.setProperty("aeron.driver.timeout", String.valueOf(DEBUG_DRIVER_TIMEOUT_MS));
        System.setProperty("aeron.keepAliveIntervalNs", String.valueOf(DEBUG_CLIENT_TIMEOUT_NS));
        System.setProperty("aeron.interServiceTimeoutNs", String.valueOf(DEBUG_INTER_SERVICE_TIMEOUT_NS));
    }

    private MediaDriver mediaDriver;
    private Aeron aeron;

    // MediaDriver Bean
    @Bean
    public MediaDriver mediaDriver() {
        if (mediaDriver != null) {
            return mediaDriver;
        }

        log.info("Starting Aeron Embedded Media Driver...");
        var mediaDriverCtx = new MediaDriver.Context()
                .clientLivenessTimeoutNs(DEBUG_CLIENT_TIMEOUT_NS)
                .publicationUnblockTimeoutNs(DEBUG_UNBLOCK_TIMEOUT_NS)
                .aeronDirectoryName(aeronDirectoryName)
                .sharedIdleStrategy(new SleepingMillisIdleStrategy(1))
                .dirDeleteOnStart(true)
                .dirDeleteOnShutdown(true)
                .threadingMode(ThreadingMode.DEDICATED);

        this.mediaDriver = MediaDriver.launchEmbedded(mediaDriverCtx);
        log.info("Aeron Media Driver started at: {}", this.mediaDriver.aeronDirectoryName());

        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }

        return mediaDriver;
    }

    // Aeron Client Bean
    @Bean
    public Aeron aeron(MediaDriver mediaDriver) {
        if (aeron != null) {
            return aeron;
        }

        log.info("Connecting Aeron Client...");
        var aeronCtx = new Aeron.Context();
        aeronCtx.driverTimeoutMs(DEBUG_DRIVER_TIMEOUT_MS);
        aeronCtx.idleStrategy(new SleepingMillisIdleStrategy(1));
        aeronCtx.aeronDirectoryName(aeronDirectoryName);

        var handler = new DefaultUnavailableImageHandler();
        aeronCtx.unavailableImageHandler(handler);
        aeronCtx.availableImageHandler(handler);

        this.aeron = Aeron.connect(aeronCtx);
        log.info("Aeron Client connected.");
        return aeron;
    }
    @Override
    public void destroy() {
        log.info("Shutting down Aeron client and Media Driver...");
        if (aeron != null) {
            try {
                aeron.close();
            } catch (Exception e) {
                log.error("Error closing Aeron client: {}", e.getMessage());
            }
        }

        if (mediaDriver != null) {
            try {
                mediaDriver.close();
            } catch (Exception e) {
                log.error("Error closing Media Driver: {}", e.getMessage());
            }
        }

        log.info("Aeron components shut down successfully.");
    }
}
