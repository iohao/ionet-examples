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
import com.iohao.cookbook.common.WsVerifyCmd;
import com.iohao.cookbook.common.message.UserMessage;
import com.iohao.net.extension.client.AbstractInputCommandRegion;
import com.iohao.net.extension.client.join.ClientRunOne;
import com.iohao.net.extension.client.kit.ClientUserConfigs;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;

/**
 *
 * @author 渔民小镇
 * @date 2023-08-04
 * @since 25.1
 */
public final class WsClient {
    static void main() {
        Locale.setDefault(Locale.US);
        // closeLog. cn: 关闭模拟请求相关日志
        ClientUserConfigs.closeLog();

        String websocketVerify = "?token=abc&name=aaaa";

        new ClientRunOne()
                .setInputCommandRegions(List.of(
                        new WsInputCommandRegion()
                ))
                .setWebsocketVerify(websocketVerify)
                .startup();
    }

    @Slf4j
    private static class WsInputCommandRegion extends AbstractInputCommandRegion {
        @Override
        public void initInputCommand() {
            this.setCmd(WsVerifyCmd.cmd, "WsVerifyCmd");
        }
    }
}
