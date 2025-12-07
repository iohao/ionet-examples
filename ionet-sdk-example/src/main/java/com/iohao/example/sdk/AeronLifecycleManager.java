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
package com.iohao.example.sdk;

import com.iohao.net.server.connection.DefaultUnavailableImageHandler;
import io.aeron.Aeron;
import io.aeron.CommonContext;
import io.aeron.driver.MediaDriver;
import io.aeron.driver.ThreadingMode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.agrona.concurrent.SleepingMillisIdleStrategy;

/**
 * @author 渔民小镇
 * @date 2023-01-06
 */
@Slf4j
class AeronLifecycleManager {

    private MediaDriver mediaDriver;
    @Getter
    private Aeron aeron;

    public AeronLifecycleManager() {
        getAeron(this.getMediaDriver());

        Runtime.getRuntime().addShutdownHook(new Thread(this::destroy));
    }

    // MediaDriver Bean
    private MediaDriver getMediaDriver() {

        if (mediaDriver != null) {
            return mediaDriver;
        }

        log.info("Starting Aeron Embedded Media Driver...");
        var mediaDriverCtx = new MediaDriver.Context()
                // 确保使用独特的目录名并隔离，避免与其他 Media Driver 冲突
                // Ensure a unique and isolated directory name is used to avoid conflicts with other Media Drivers
                .aeronDirectoryName(CommonContext.getAeronDirectoryName() + "-server")
                .sharedIdleStrategy(new SleepingMillisIdleStrategy(1))
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
    private void getAeron(MediaDriver mediaDriver) {
        log.info("Connecting Aeron Client...");
        var aeronCtx = new Aeron.Context();
        aeronCtx.idleStrategy(new SleepingMillisIdleStrategy(1));
        // 确保客户端连接到驱动程序使用的目录
        // Ensure the client connects to the directory used by the driver
        aeronCtx.aeronDirectoryName(mediaDriver.aeronDirectoryName());

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
}