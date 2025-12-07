/*
 * ionet
 * Copyright (C) 2021 - present  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.iohao.cookbook.common;

import com.iohao.cookbook.common.extension.UserKit;
import com.iohao.net.server.connection.DefaultUnavailableImageHandler;
import io.aeron.Aeron;
import io.aeron.CommonContext;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;
import org.agrona.concurrent.BusySpinIdleStrategy;
import org.agrona.concurrent.SleepingMillisIdleStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("all")
public class AeronLifecycleManager {
    static final Logger log = LoggerFactory.getLogger(AeronLifecycleManager.class);

    // 10 分钟 (600 秒) 的纳秒值
    public static final long DEBUG_CLIENT_TIMEOUT_NS = 600_000_000_000L;
    // 10 分钟 (600 秒) 的毫秒值，用于 aeron.driver.timeout
    public static final long DEBUG_DRIVER_TIMEOUT_MS = 600_000L;
    // 15 分钟 (900 秒) 的纳秒值
    public static final long DEBUG_UNBLOCK_TIMEOUT_NS = 900_000_000_000L;
    // 略大于 10 分钟的纳秒值
    public static final long DEBUG_INTER_SERVICE_TIMEOUT_NS = DEBUG_CLIENT_TIMEOUT_NS + 1_000_000_000L;

    static {
        // 🚀 1. 延长客户端等待 Driver 出现的心跳超时 (单位: 毫秒)
        System.setProperty("aeron.driver.timeout", String.valueOf(DEBUG_DRIVER_TIMEOUT_MS));

        // 🚀 2. 延长客户端保活间隔 (单位: 纳秒)
        System.setProperty("aeron.keepAliveIntervalNs", String.valueOf(DEBUG_CLIENT_TIMEOUT_NS));

        // 🚀 3. 延长客户端集群服务超时，解决配置校验 (单位: 纳秒)
        System.setProperty("aeron.interServiceTimeoutNs", String.valueOf(DEBUG_INTER_SERVICE_TIMEOUT_NS));
    }

    private MediaDriver mediaDriver;
    private Aeron aeron;

    public static String getAeronDirectoryName() {
        return "%s-%s".formatted(CommonContext.getAeronDirectoryName(), "ionet");
    }

    public AeronLifecycleManager() {
        initMediaDriver();
        initAeron();

        Runtime.getRuntime().addShutdownHook(new Thread(this::destroy));
    }

    public Aeron getAeron() {
        return aeron;
    }

    // MediaDriver Bean
    private MediaDriver initMediaDriver() {

        if (mediaDriver != null) {
            return mediaDriver;
        }

        log.info("Starting Aeron Embedded Media Driver...");
        var mediaDriverCtx = new MediaDriver.Context()
                // 设置客户端心跳超时时间 (确保 Driver 知道 Client 10分钟后才断开)
                .clientLivenessTimeoutNs(DEBUG_CLIENT_TIMEOUT_NS)
                // 设置发布解除阻塞超时时间 (必须大于心跳超时)
                .publicationUnblockTimeoutNs(DEBUG_UNBLOCK_TIMEOUT_NS)
                // 确保使用独特的目录名并隔离，避免与其他 Media Driver 冲突
                // Ensure a unique and isolated directory name is used to avoid conflicts with other Media Drivers
                .aeronDirectoryName(getAeronDirectoryName())
                .sharedIdleStrategy(new SleepingMillisIdleStrategy(1))
//                .sharedIdleStrategy(new BusySpinIdleStrategy())
                // 启动时清理旧目录，解决残留文件问题
                // Clean up old directories on startup to resolve residual file issues
                .dirDeleteOnStart(true)
                // 关闭时清理目录
                // Clean up directory on shutdown
                .dirDeleteOnShutdown(true)
                // 使用专用线程模式
                // Use dedicated threading mode
                .threadingMode(ThreadingMode.DEDICATED);

        this.mediaDriver = MediaDriver.launchEmbedded(mediaDriverCtx);
        log.info("Aeron Media Driver started at: {}", this.mediaDriver.aeronDirectoryName());

        try {
            // 启动后暂停，确保驱动程序完成内部初始化
            // Pause after startup to ensure the driver completes its internal initialization
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }

        return mediaDriver;
    }

    // Aeron Client Bean
    private void initAeron() {
        log.info("Connecting Aeron Client...");
        var aeronCtx = new Aeron.Context();
        aeronCtx.driverTimeoutMs(DEBUG_DRIVER_TIMEOUT_MS);

        aeronCtx.idleStrategy(new SleepingMillisIdleStrategy(1));
//        aeronCtx.idleStrategy(new BusySpinIdleStrategy());

        // 确保客户端连接到驱动程序使用的目录
        // Ensure the client connects to the directory used by the driver
        aeronCtx.aeronDirectoryName(getAeronDirectoryName());

        var handler = new DefaultUnavailableImageHandler();
        aeronCtx.unavailableImageHandler(handler);
        aeronCtx.availableImageHandler(handler);

        // 使用更健壮的连接方法，例如设置超时，或者简单连接
        // Use a more robust connection method, such as setting a timeout, or a simple connect
        this.aeron = Aeron.connect(aeronCtx);
        log.info("Aeron Client connected.");
    }

    private void destroy() {
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

    static {
        UserKit.ofUserMessage(1378604058);
    }
}