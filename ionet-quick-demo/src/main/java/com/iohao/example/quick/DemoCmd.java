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
package com.iohao.example.quick;

import com.iohao.net.framework.core.CmdInfo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 渔民小镇
 * @date 2023-07-17
 */
public interface DemoCmd {
    int cmd = 1;

    int here = 0;
    int jackson = 1;
    int list = 2;

    int triggerBroadcast = 3;

    AtomicInteger broadcastIndex = new AtomicInteger(20);
    CmdInfo listenData = CmdInfo.of(cmd, broadcastIndex.getAndIncrement());
    CmdInfo listenList = CmdInfo.of(cmd, broadcastIndex.getAndIncrement());
}
