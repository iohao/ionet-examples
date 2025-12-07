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


import com.iohao.net.app.RunOne;
import com.iohao.net.external.core.config.ExternalGlobalConfig;
import com.iohao.net.external.core.netty.ExternalMapper;
import com.iohao.net.framework.CoreGlobalConfig;

import java.util.List;
import java.util.Locale;

/**
 * @author 渔民小镇
 * @date 2023-01-06
 */
public class DemoApplication {
    static void main() {
        // Enable source documentation parsing to display code line numbers.
        // cn: 开启源码文档解析，以显示代码行数。
        CoreGlobalConfig.setting.parseDoc = true;
        CoreGlobalConfig.setting.print = true;
        // i18n: US or CHINA
        Locale.setDefault(Locale.US);

        // Create the ExternalServer builder and set the port for establishing connections with real players
        // cn: 创建对外服构建器，并设置与真实玩家建立连接的端口
        int port = ExternalGlobalConfig.externalPort;
        var externalServer = ExternalMapper.builder(port).build();

        var aeron = new AeronLifecycleManager().getAeron();

        new RunOne()
                // aeron
                .setAeron(aeron)
                .enableCenterServer()
                // externalServer. cn: 对外服
                .setExternalServer(externalServer)
                // logicServers. cn: 逻辑服
                .setLogicServerList(List.of(new DemoLogicServer()))
                .startup();
    }
}
