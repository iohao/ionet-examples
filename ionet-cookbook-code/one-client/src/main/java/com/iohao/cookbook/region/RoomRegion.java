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


import static com.iohao.cookbook.common.RoomCmd.*;

import com.iohao.cookbook.common.RoomCmd;
import com.iohao.cookbook.common.extension.JsonKit;
import com.iohao.cookbook.common.message.EnterRoomMessage;
import com.iohao.cookbook.common.message.MyOperationCommandMessage;
import com.iohao.cookbook.common.message.MyOperationEnum;
import com.iohao.cookbook.common.message.ReadyMessage;
import com.iohao.net.common.kit.RandomKit;
import com.iohao.net.extension.client.AbstractInputCommandRegion;
import com.iohao.net.extension.client.kit.ScannerKit;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-09
 * @since 25.1
 */
@Slf4j
public final class RoomRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        this.setCmd(RoomCmd.cmd, "Room");

        ofCommand(createRoom, "createRoom");
        ofCommand(quitRoom, "quitRoom");

        ofCommand(enterRoom, "enterRoom").setRequestData(() -> {
            ScannerKit.log(() -> log.info("\nPlease enter the roomId"));
            return ScannerKit.nextLong(1);
        });

        ofCommand(ready, "ready").setRequestData(() -> {
            // true or false
            return true;
        });

        ofCommand(listRoomId, "listRoom").callback(result -> {
            List<Long> roomIdList = result.listLong();
            log.info("\nlistRoomId: {}", roomIdList);
        });

        ofCommand(operation, "operation").setRequestData(() -> {
            MyOperationCommandMessage message = new MyOperationCommandMessage();

            if (RandomKit.randomBoolean()) {
                message.operation = MyOperationEnum.attack;
            } else {
                message.operation = MyOperationEnum.defense;
            }

            return message;
        });

        extractedBroadcast();
    }

    private void extractedBroadcast() {
        ofListen(result -> {
            var message = result.getValue(ReadyMessage.class);
            log.info("\nreceived ready message: {}", message);
        }, readyBroadcast, "readyBroadcast");

        ofListen(result -> {
            long quitUserId = result.getLong();
            log.info("\nreceived quit userId: {}", quitUserId);
        }, quitRoomBroadcast, "quitRoomBroadcast");

        ofListen(result -> {
            var message = result.getValue(EnterRoomMessage.class);
            var s = JsonKit.toJson(message);
            log.info("\nreceived enterRoom message: {}", s);
        }, enterRoomBroadcast, "enterRoomBroadcast");

        ofListen(result -> {
            var message = result.getValue(MyOperationCommandMessage.class);
            log.info("\nreceived MyOperationCommandMessage message: {}", message);
        }, operationBroadcast, "operationBroadcast");
    }
}
