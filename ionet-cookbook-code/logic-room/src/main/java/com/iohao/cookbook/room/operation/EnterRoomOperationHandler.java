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
package com.iohao.cookbook.room.operation;

import com.iohao.cookbook.common.extension.UserKit;
import com.iohao.cookbook.common.message.ErrorCode;
import com.iohao.cookbook.room.MyRoomService;
import com.iohao.cookbook.room.data.MyBroadcastKit;
import com.iohao.cookbook.room.data.MyPlayer;
import com.iohao.cookbook.room.data.MyRoom;
import com.iohao.net.extension.room.Room;
import com.iohao.net.extension.room.operation.OperationHandler;
import com.iohao.net.extension.room.operation.PlayerOperationContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 渔民小镇
 * @date 2025-06-02
 * @since 21.28
 */
@Slf4j
public final class EnterRoomOperationHandler implements OperationHandler {
    final MyRoomService roomService = MyRoomService.me();

    @Override
    public boolean processVerify(PlayerOperationContext context) {
        Room room = context.getRoom();
        ErrorCode.roomSpaceSizeNotEnough.assertTrue(room.hasSeat());
        return true;
    }

    @Override
    public void process(PlayerOperationContext context) {
        long userId = context.getUserId();
        log.info("enterRoom : {}", userId);

        MyRoom room = context.getRoom();
        room.ifPlayerNotExist(userId, () -> {
            var userMessage = UserKit.getUserMessage(userId);

            var player = new MyPlayer();
            player.setUserId(userId);
            player.setNickname(userMessage.nickname);

            roomService.addPlayer(room, player);
        });

        MyBroadcastKit.broadcastEnterRoom(room);
    }
}