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
package com.iohao.example.sdk;

import com.iohao.example.sdk.action.*;
import com.iohao.example.sdk.data.*;
import com.iohao.example.sdk.data.UserMessage;
import com.iohao.net.common.kit.concurrent.TaskKit;
import com.iohao.net.extension.client.*;
import com.iohao.net.extension.client.join.ClientRunOne;
import com.iohao.net.extension.client.kit.ClientUserConfigs;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-08
 * @since 25.1
 */
public final class SdkClient {
    static void main() {
        ClientUserConfigs.closeLog();
        // i18n: CHINA or US
        Locale.setDefault(Locale.US);

        new ClientRunOne()
                .setConnectAddress("127.0.0.1")
                .idle(5)
                .setInputCommandRegions(List.of(
                        new SdkInputCommandRegion()
                ))
                .startup();
    }

    @Slf4j
    static class SdkInputCommandRegion extends AbstractInputCommandRegion {
        @Override
        public void connectionComplete() {
            // login
            this.executeCommand(SdkCmd.loginVerify);
        }

        @Override
        public void loginSuccessCallback() {
            // test
            this.executeCommand(SdkCmd.intValue);
            this.executeCommand(SdkCmd.longValue);
            this.executeCommand(SdkCmd.boolValue);
            this.executeCommand(SdkCmd.stringValue);

            this.executeCommand(SdkCmd.listInt);
            this.executeCommand(SdkCmd.listLong);
            this.executeCommand(SdkCmd.listBool);
            this.executeCommand(SdkCmd.listString);
        }

        @Override
        public void initInputCommand() {
            // 设置模拟请求登录域的主路由
            // Set the main route for the simulated request login domain
            this.setCmd(SdkCmd.cmd, "SDK");

            this.ofCommand(SdkCmd.loginVerify, "loginVerify").setRequestData(() -> {
                var message = new LoginVerifyMessage();
                message.jwt = "1";
                return message;
            }).callback(result -> {
                UserMessage message = result.getValue(UserMessage.class);
                this.setUserId(message.userId);
                log.info("message : {}", message);
            });

            this.ofCommand(SdkCmd.intValue, "intValue").setRequestData(() -> {
                // int or IntValue.of(1)
                return 1;
            }).callback(result -> {
                log.info("{}", result.getInt());
            });

            this.ofCommand(SdkCmd.longValue, "longValue").setRequestData(() -> {
                // long or LongValue.of(1)
                return 1L;
            }).callback(result -> {
                log.info("{}", result.getLong());
            });

            this.ofCommand(SdkCmd.boolValue, "boolValue").setRequestData(() -> {
                // bool or BoolValue.of(true)
                return true;
            }).callback(result -> {
                log.info("{}", result.getBoolean());
            });

            this.ofCommand(SdkCmd.stringValue, "stringValue").setRequestData(() -> {
                // String or StringValue.of("abc")
                return "abc";
            }).callback(result -> {
                log.info("{}", result.getString());
            });

            this.ofCommand(SdkCmd.listInt, "listInt").setRequestData(() -> {
                // List<Integer> or IntValueList.of(List.of(1,2))
                return List.of(1, 2);
            }).callback(result -> {
                log.info("{}", result.listInt());
            });

            this.ofCommand(SdkCmd.listLong, "listLong").setRequestData(() -> {
                // List<Long> or LongValueList.of(List.of(1L,2L))
                return List.of(1L, 2L);
            }).callback(result -> {
                log.info("{}", result.listLong());
            });

            this.ofCommand(SdkCmd.listBool, "listBool").setRequestData(() -> {
                // List<Boolean> or BoolValueList.of(List.of(true, false))
                return List.of(true, false);
            }).callback(result -> {
                log.info("{}", result.listBoolean());
            });

            this.ofCommand(SdkCmd.listString, "listString").setRequestData(() -> {
                // List<String> or StringValueList.of(List.of("a", "b"))
                return List.of("a", "b");
            }).callback(result -> {
                log.info("{}", result.listString());
            });
        }


    }
}
