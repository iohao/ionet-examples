package com.iohao.spring;


import com.iohao.net.server.connection.DefaultUnavailableImageHandler;
import io.aeron.Aeron;
import io.aeron.CommonContext;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;
import org.agrona.concurrent.SleepingMillisIdleStrategy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AeronLifecycleManager implements DisposableBean {

    private MediaDriver mediaDriver;
    private Aeron aeron;

    // MediaDriver Bean
    @Bean
    public MediaDriver mediaDriver() {
        System.out.println("Starting Aeron Embedded Media Driver...");
        var mediaDriverCtx = new MediaDriver.Context()
                // Ensure a unique and isolated directory name to avoid conflicts with other Media Drivers
                .aeronDirectoryName(CommonContext.getAeronDirectoryName() + "-server")
                .sharedIdleStrategy(new SleepingMillisIdleStrategy(1))
                // Clean up old directory on startup to resolve residual file issues
                .dirDeleteOnStart(true)
                // Clean up directory on shutdown
                .dirDeleteOnShutdown(true)
                // Use dedicated thread mode
                .threadingMode(ThreadingMode.DEDICATED);

        this.mediaDriver = MediaDriver.launchEmbedded(mediaDriverCtx);
        System.out.println("Aeron Media Driver started at: " + this.mediaDriver.aeronDirectoryName());

        try {
            // Pause after startup to ensure the driver completes internal initialization
            Thread.sleep(500);
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }

        return mediaDriver;
    }

    // Aeron Client Bean
    @Bean
    public Aeron aeron(MediaDriver mediaDriver) {
        System.out.println("Connecting Aeron Client...");
        var aeronCtx = new Aeron.Context();
        aeronCtx.idleStrategy(new SleepingMillisIdleStrategy(1));
        // Ensure the client connects to the directory used by the driver
        aeronCtx.aeronDirectoryName(mediaDriver.aeronDirectoryName());

        var handler = new DefaultUnavailableImageHandler();
        aeronCtx.unavailableImageHandler(handler);
        aeronCtx.availableImageHandler(handler);

        // Use a more robust connection method, such as setting a timeout, or a simple connect
        this.aeron = Aeron.connect(aeronCtx);
        System.out.println("Aeron Client connected.");
        return aeron;
    }

    /**
     * Implements the DisposableBean interface to ensure resources are closed when the Spring container shuts down.
     * This is a standard and reliable way to manage resource lifecycle in Spring Boot.
     */
    @Override
    public void destroy() {
        System.out.println("Shutting down Aeron client and Media Driver...");
        if (aeron != null) {
            try {
                aeron.close();
            } catch (Exception e) {
                System.err.println("Error closing Aeron client: " + e.getMessage());
            }
        }

        if (mediaDriver != null) {
            try {
                mediaDriver.close();
            } catch (Exception e) {
                System.err.println("Error closing Media Driver: " + e.getMessage());
            }
        }

        System.out.println("Aeron components shut down successfully.");
    }
}