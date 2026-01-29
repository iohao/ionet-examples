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
package com.iohao.cookbook.room.data;

import com.iohao.cookbook.common.RoomCmd;
import com.iohao.cookbook.common.message.*;
import com.iohao.net.extension.room.*;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

/**
 * @author 渔民小镇
 * @date 2025-06-02
 * @since 21.28
 */
@UtilityClass
public final class MyBroadcastKit {
    public void broadcast(ReadyMessage message, Room room) {
        room.ofRangeBroadcast(RoomCmd.readyBroadcast)
                .setData(message)
                .execute();
    }

    public void broadcastUserQuitRoom(long userId, Room room) {
        room.ofRangeBroadcast(RoomCmd.quitRoomBroadcast)
                .setData(userId)
                .execute();
    }

    public void broadcastEnterRoom(MyRoom room) {
        var enterRoomMessage = new EnterRoomMessage();
        enterRoomMessage.roomId = room.getRoomId();
        enterRoomMessage.playerMap = room.streamPlayer()
                .map((player) -> {
                    PlayerMessage playerMessage = new PlayerMessage();
                    playerMessage.userId = player.getUserId();
                    playerMessage.nickname = ((MyPlayer) player).getNickname();
                    return playerMessage;
                })
                .collect(Collectors.toMap(PlayerMessage::getUserId, p -> p));

        room.ofRangeBroadcast(RoomCmd.enterRoomBroadcast)
                .setData(enterRoomMessage)
                .execute();
    }

    public void broadcastOperation(MyOperationCommandMessage message, Room room) {
        room.ofRangeBroadcast(RoomCmd.operationBroadcast)
                .setData(message)
                .execute();
    }
}
