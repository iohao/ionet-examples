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
package com.iohao.cookbook.room;

import com.iohao.cookbook.room.data.MyRoom;
import com.iohao.net.common.kit.CollKit;
import com.iohao.net.extension.room.*;
import com.iohao.net.extension.room.*;
import com.iohao.net.extension.room.operation.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author 渔民小镇
 * @date 2025-06-02
 * @since 21.28
 */
@Slf4j
@Getter
public final class MyRoomService implements RoomService, OperationService {
    final Map<Long, Room> roomMap = CollKit.ofConcurrentHashMap();
    final Map<Long, Long> userRoomMap = CollKit.ofConcurrentHashMap();
    final OperationFactory operationFactory = OperationFactory.of();
    final AtomicLong roomIdCounter = new AtomicLong(1);

    public MyRoom createRoom() {
        var room = new MyRoom();
        room.setRoomId(roomIdCounter.getAndIncrement());
        room.setOperationService(this);
        room.setSpaceSize(2);
        return room;
    }

    private MyRoomService() {
    }

    public static MyRoomService me() {
        return Holder.ME;
    }

    private static class Holder {
        static final MyRoomService ME = new MyRoomService();
    }
}
