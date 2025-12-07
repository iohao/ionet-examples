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


import com.iohao.net.framework.core.BarSkeletonBuilder;
import com.iohao.net.framework.core.flow.internal.DebugInOut;
import com.iohao.net.framework.protocol.ServerBuilder;
import com.iohao.net.server.LogicServer;

/**
 * DemoLogicServer
 *
 * @author 渔民小镇
 * @date 2023-01-06
 */
public class DemoLogicServer implements LogicServer {
    @Override
    public void settingBarSkeletonBuilder(BarSkeletonBuilder builder) {
        // Scan the package where the action classes are located
        // 扫描 action 类所在包
        builder.scanActionPackage(DemoAction.class);

        // Add console output plugin
        // 添加控制台输出插件
        builder.addInOut(new DebugInOut());
    }

    @Override
    public void settingServerBuilder(ServerBuilder builder) {
        builder.setName("DemoLogicServer");
    }
}
