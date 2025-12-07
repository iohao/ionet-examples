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
package com.iohao.cookbook.region;

import com.iohao.cookbook.common.FlowContextSendCmd;
import com.iohao.net.extension.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

import static com.iohao.cookbook.common.FlowContextSendCmd.*;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-04
 * @since 25.1
 */
@Slf4j
public class FlowContextSendRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        this.setCmd(FlowContextSendCmd.cmd, "FlowContextSend");

        ofCommand(sendEmpty, "sendEmpty").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(sendInt, "sendInt").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(sendBool, "sendBool").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(sendLong, "sendLong").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(sendString, "sendString").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(sendObject, "sendObject").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        // --------------- list ---------------
        ofCommand(sendIntList, "sendIntList").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(sendBoolList, "sendBoolList").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(sendLongList, "sendLongList").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(sendStringList, "sendStringList").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(sendObjectList, "sendObjectList").callback(result -> {
            log.info("{}", result.getBoolean());
        });
    }
}
