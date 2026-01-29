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

import com.iohao.cookbook.room.MyRoomService;
import com.iohao.cookbook.room.data.MyBroadcastKit;
import com.iohao.cookbook.room.data.*;
import com.iohao.net.extension.room.Room;
import com.iohao.net.extension.room.operation.*;
import com.iohao.net.extension.room.operation.PlayerOperationContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 渔民小镇
 * @date 2025-06-02
 * @since 21.28
 */
@Slf4j
public final class QuitRoomOperationHandler implements OperationHandler {
    @Override
    public void process(PlayerOperationContext context) {
        Room room = context.getRoom();
        long userId = context.getUserId();

        log.info("QuitRoom : {}", userId);

        MyRoomService.me().removePlayer(room, userId);

        if (room.countPlayer() == 0) {
            MyRoomService.me().removeRoom(room);
            return;
        }

        MyBroadcastKit.broadcastUserQuitRoom(userId, room);
    }
}