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
package com.iohao.cookbook.ws.verify.external;

import com.iohao.cookbook.common.AeronLifecycleManager;
import com.iohao.cookbook.hall.HallLogicServer;
import com.iohao.net.app.RunOne;
import com.iohao.net.external.core.ExternalServer;
import com.iohao.net.external.core.config.ExternalGlobalConfig;
import com.iohao.net.external.core.netty.ExternalMapper;
import com.iohao.net.external.core.netty.micro.WebSocketMicroBootstrapFlow;

import java.util.List;
import java.util.Locale;

/**
 *
 * @author 渔民小镇
 * @date 2023-08-04
 * @since 25.1
 */
public final class WsVerifyApplication {

    static void main() {
        // US or CHINA
        Locale.setDefault(Locale.US);
//        Locale.setDefault(Locale.CHINA);

        var aeron = new AeronLifecycleManager().getAeron();

        new RunOne()
                .setAeron(aeron)
                .enableCenterServer()
                .setExternalServer(createExternalServer())
                .setLogicServerList(List.of(new HallLogicServer()))
                .startup();
    }

    static ExternalServer createExternalServer() {
        var builder = ExternalMapper.builder(ExternalGlobalConfig.externalPort);

        // WebSocketVerifyHandler
        var microBootstrapFlow = new WebSocketMicroBootstrapFlow();
        microBootstrapFlow.verifyHandler = new MyWebSocketVerifyHandler();
        builder.setMicroBootstrapFlow(microBootstrapFlow);

        return builder.build();
    }
}
