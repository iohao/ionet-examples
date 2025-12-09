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

import com.iohao.cookbook.common.FlowContextSendCmd;
import com.iohao.cookbook.common.InternalCmd;
import com.iohao.cookbook.common.message.AuthorMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.flow.FlowContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-04
 * @since 25.1
 */
@Slf4j
@ActionController(FlowContextSendCmd.cmd)
public class FlowContextSendAction {
    @ActionMethod(FlowContextSendCmd.sendEmpty)
    private boolean sendEmpty(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendEmptyAction);
        flowContext.send(cmdInfo);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendInt)
    private boolean sendInt(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendIntAction);
        int data = 1;

        flowContext.send(cmdInfo, data);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendBool)
    private boolean sendBool(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendBoolAction);
        boolean data = true;

        flowContext.send(cmdInfo, data);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendLong)
    private boolean sendLong(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendLongAction);
        long data = 1L;

        flowContext.send(cmdInfo, data);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendString)
    private boolean sendString(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendStringAction);
        String data = "hello";

        flowContext.send(cmdInfo, data);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendObject)
    private boolean sendObject(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendObjectAction);
        var data = new AuthorMessage();
        data.authorName = "Joker";

        flowContext.send(cmdInfo, data);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendIntList)
    private boolean sendIntList(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendIntListAction);
        List<Integer> dataList = List.of(1, 2);

        flowContext.sendListInt(cmdInfo, dataList);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendBoolList)
    private boolean sendBoolList(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendBoolListAction);
        List<Boolean> dataList = List.of(true, false);

        flowContext.sendListBool(cmdInfo, dataList);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendLongList)
    private boolean sendLongList(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendLongListAction);
        List<Long> dataList = List.of(1L, 2L);

        flowContext.sendListLong(cmdInfo, dataList);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendStringList)
    private boolean sendStringList(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendStringListAction);
        List<String> dataList = List.of("hello", "ionet");

        flowContext.sendListString(cmdInfo, dataList);
        return true;
    }

    @ActionMethod(FlowContextSendCmd.sendObjectList)
    private boolean sendObjectList(FlowContext flowContext) {
        var cmdInfo = InternalCmd.of(InternalCmd.sendObjectListAction);

        var author1 = new AuthorMessage();
        author1.authorName = "David Myers";

        var author2 = new AuthorMessage();
        author2.authorName = "Gustave Le Bon";

        List<AuthorMessage> dataList = List.of(author1, author2);
        flowContext.send(cmdInfo, dataList);
        return true;
    }
}
