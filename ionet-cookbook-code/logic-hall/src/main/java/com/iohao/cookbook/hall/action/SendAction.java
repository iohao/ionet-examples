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
package com.iohao.cookbook.hall.action;

import com.iohao.cookbook.common.SendCmd;
import com.iohao.cookbook.common.InternalCmd;
import com.iohao.cookbook.common.message.AuthorMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.communication.Communication;
import com.iohao.net.framework.communication.CommunicationKit;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-04
 * @since 25.1
 */
@Slf4j
@ActionController(SendCmd.cmd)
public class SendAction {
    private Communication communication() {
        return CommunicationKit.getCommunication();
    }

    @ActionMethod(SendCmd.sendEmpty)
    private boolean sendEmpty() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendEmptyAction);
        communication().send(cmdInfo);
        return true;
    }

    @ActionMethod(SendCmd.sendInt)
    private boolean sendInt() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendIntAction);
        int data = 1;

        communication().send(cmdInfo, data);
        return true;
    }

    @ActionMethod(SendCmd.sendBool)
    private boolean sendBool() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendBoolAction);
        boolean data = true;

        communication().send(cmdInfo, data);
        return true;
    }

    @ActionMethod(SendCmd.sendLong)
    private boolean sendLong() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendLongAction);
        long data = 1L;

        communication().send(cmdInfo, data);
        return true;
    }

    @ActionMethod(SendCmd.sendString)
    private boolean sendString() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendStringAction);
        String data = "hello";

        communication().send(cmdInfo, data);
        return true;
    }

    @ActionMethod(SendCmd.sendObject)
    private boolean sendObject() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendObjectAction);
        var data = new AuthorMessage();
        data.authorName = "Joker";

        communication().send(cmdInfo, data);
        return true;
    }

    @ActionMethod(SendCmd.sendIntList)
    private boolean sendIntList() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendIntListAction);
        List<Integer> dataList = List.of(1, 2);

        communication().sendListInt(cmdInfo, dataList);
        return true;
    }

    @ActionMethod(SendCmd.sendBoolList)
    private boolean sendBoolList() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendBoolListAction);
        List<Boolean> dataList = List.of(true, false);

        communication().sendListBool(cmdInfo, dataList);
        return true;
    }

    @ActionMethod(SendCmd.sendLongList)
    private boolean sendLongList() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendLongListAction);
        List<Long> dataList = List.of(1L, 2L);

        communication().sendListLong(cmdInfo, dataList);
        return true;
    }

    @ActionMethod(SendCmd.sendStringList)
    private boolean sendStringList() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendStringListAction);
        List<String> dataList = List.of("hello", "ionet");

        communication().sendListString(cmdInfo, dataList);
        return true;
    }

    @ActionMethod(SendCmd.sendObjectList)
    private boolean sendObjectList() {
        var cmdInfo = InternalCmd.of(InternalCmd.sendObjectListAction);

        var author1 = new AuthorMessage();
        author1.authorName = "David Myers";

        var author2 = new AuthorMessage();
        author2.authorName = "Gustave Le Bon";

        List<AuthorMessage> dataList = List.of(author1, author2);
        communication().send(cmdInfo, dataList);
        return true;
    }
}
