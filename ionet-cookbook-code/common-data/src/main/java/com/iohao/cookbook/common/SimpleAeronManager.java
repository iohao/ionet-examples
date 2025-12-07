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

import com.iohao.net.server.connection.DefaultUnavailableImageHandler;
import io.aeron.Aeron;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.agrona.concurrent.SleepingMillisIdleStrategy;

/**
 *
 * @author 渔民小镇
 * @date 2025-11-05
 * @since 25.1
 */
@Slf4j
@UtilityClass
public final class SimpleAeronManager {
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


    public Aeron getAeron() {
        log.info("Connecting Aeron Client...");
        var aeronCtx = new Aeron.Context();
        aeronCtx.driverTimeoutMs(DEBUG_DRIVER_TIMEOUT_MS);

        aeronCtx.idleStrategy(new SleepingMillisIdleStrategy(1));
        aeronCtx.aeronDirectoryName(AeronLifecycleManager.getAeronDirectoryName());

        var handler = new DefaultUnavailableImageHandler();
        aeronCtx.unavailableImageHandler(handler);
        aeronCtx.availableImageHandler(handler);

        // 使用更健壮的连接方法，例如设置超时，或者简单连接
        // Use a more robust connection method, such as setting a timeout, or a simple connect
        log.info("Aeron Client connected.");
        return Aeron.connect(aeronCtx);
    }
}
