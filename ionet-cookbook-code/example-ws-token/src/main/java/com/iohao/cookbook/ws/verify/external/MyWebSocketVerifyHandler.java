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

import com.iohao.cookbook.common.HallCmd;
import com.iohao.net.framework.core.codec.DataCodecManager;
import com.iohao.net.external.core.netty.handler.WebSocketVerifyHandler;
import com.iohao.net.external.core.netty.session.SocketUserSession;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 *
 * @author 渔民小镇
 * @date 2023-08-04
 * @since 25.1
 */
@Slf4j
public final class MyWebSocketVerifyHandler extends WebSocketVerifyHandler {
    @Override
    protected boolean verify(SocketUserSession userSession, Map<String, String> params) {
        // ws://127.0.0.1:10100/websocket?token=abc&name=aaaa
        String token = params.get("token");
        boolean verifyResult = "abc".equals(token);

        log.info("verify name: {}", params.get("name"));

        if (verifyResult) {
            // Send a login message
            var cmdInfo = HallCmd.of(HallCmd.loginVerify);
            byte[] data = DataCodecManager.getDataCodec().encode(token);

            var message = userSession.ofMessage(cmdInfo);
            message.setData(data);

            // send message to logicServer
            this.convenientCommunication.request(message);
        }

        // 返回 true 表示验证通过，返回 false 框架会关闭连接。
        // Return true means verification passed, return false means the framework will close the connection.
        return verifyResult;
    }
}
