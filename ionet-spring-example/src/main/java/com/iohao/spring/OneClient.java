/*
 * ionet
 * Copyright (C) 2021 - present  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General  License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General  License for more details.
 *
 * You should have received a copy of the GNU Affero General  License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.iohao.spring;

import com.iohao.net.extension.client.AbstractInputCommandRegion;
import com.iohao.net.extension.client.join.ClientRunOne;
import com.iohao.net.extension.client.kit.ClientUserConfigs;
import com.iohao.spring.server.MyCmd;

import java.util.List;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-04
 * @since 25.1
 */
public class OneClient {
    static void main() {
        // closeLog. cn: 关闭模拟请求相关日志
        ClientUserConfigs.closeLog();
        // Startup Client. cn: 启动模拟客户端
        new ClientRunOne().setInputCommandRegions(
                List.of(new MySpringClient())
        ).startup();
    }

    private static class MySpringClient extends AbstractInputCommandRegion {
        @Override
        public void connectionComplete() {
            executeCommand(MyCmd.getTime);
        }

        @Override
        public void initInputCommand() {
            this.setCmd(MyCmd.cmd);

            ofCommand(MyCmd.getTime).setTitle("getTime").callback(result -> {
                System.out.println("Time: " + result.getString());
            });

        }
    }
}
