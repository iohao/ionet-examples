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

import com.iohao.cookbook.common.RoomCmd;
import com.iohao.cookbook.common.message.ErrorCode;
import com.iohao.cookbook.common.message.MyOperationCommandMessage;
import com.iohao.cookbook.common.message.MyOperationEnum;
import com.iohao.cookbook.room.data.InternalOperationEnum;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.flow.FlowContext;
import com.iohao.net.extension.room.Room;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 渔民小镇
 * @date 2025-06-02
 * @since 21.28
 */
@Slf4j
@ActionController(RoomCmd.cmd)
public final class RoomAction {
    final MyRoomService roomService = MyRoomService.me();

    @ActionMethod(RoomCmd.createRoom)
    private void createRoom(FlowContext flowContext) {

        long userId = flowContext.getUserId();
        Room roomByUserId = roomService.getRoomByUserId(userId);
        ErrorCode.illegalOperation.assertTrueThrows(Objects.nonNull(roomByUserId));

        var room = roomService.createRoom();
        roomService.addRoom(room);

        room.operation(InternalOperationEnum.enterRoom, flowContext);
    }

    @ActionMethod(RoomCmd.enterRoom)
    private void enterRoom(long roomId, FlowContext flowContext) {
        long userId = flowContext.getUserId();
        var room = this.roomService.optionalRoomByUserId(userId)
                .orElseGet(() -> this.roomService.getRoom(roomId));

        ErrorCode.roomNotExist.assertNonNull(room);

        room.operation(InternalOperationEnum.enterRoom, flowContext);
    }

    @ActionMethod(RoomCmd.ready)
    private void ready(boolean ready, FlowContext flowContext) {
        long userId = flowContext.getUserId();
        var room = getRoomByUserId(userId);

        room.operation(InternalOperationEnum.ready, flowContext, ready);
    }

    @ActionMethod(RoomCmd.quitRoom)
    private void quitRoom(FlowContext flowContext) {
        long userId = flowContext.getUserId();
        var room = getRoomByUserId(userId);

        room.operation(InternalOperationEnum.quitRoom, flowContext);
    }

    @ActionMethod(RoomCmd.operation)
    private void operation(MyOperationCommandMessage command, FlowContext flowContext) {
        MyOperationEnum operation = command.operation;
        var operationHandler = roomService.getUserOperationHandler(operation);
        ErrorCode.illegalOperation.assertNonNull(operationHandler);

        long userId = flowContext.getUserId();
        Room room = getRoomByUserId(userId);
        room.operation(operationHandler, flowContext, command);
    }

    @ActionMethod(RoomCmd.listRoomId)
    private List<Long> listRoomId() {
        return roomService.listRoom().stream()
                .map(Room::getRoomId)
                .collect(Collectors.toList());
    }

    private Room getRoomByUserId(long userId) {
        Room room = this.roomService.getRoomByUserId(userId);
        ErrorCode.roomNotExist.assertNullThrows(room);
        return room;
    }
}