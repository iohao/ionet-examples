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

import com.iohao.cookbook.common.CallCmd;
import com.iohao.cookbook.common.InternalCmd;
import com.iohao.cookbook.common.message.AuthorMessage;
import com.iohao.cookbook.common.message.BookMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.communication.Communication;
import com.iohao.net.framework.communication.CommunicationKit;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Via CommunicationKit
 *
 * @author 渔民小镇
 * @date 2025-10-05
 * @since 25.1
 */
@Slf4j
@ActionController(CallCmd.cmd)
public class CallAction {
    private Communication communication() {
        return CommunicationKit.getCommunication();
    }

    @ActionMethod(CallCmd.callEmpty)
    private int callEmpty() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.emptyAction);

        // Asynchronous callback
        communication().callback(cmdInfo, response -> {
            log.info("{}", response.getInt());
        });

        // Synchronous call
        var response = communication().call(cmdInfo);
        return response.getInt();
    }

    @ActionMethod(CallCmd.callInt)
    private int callInt() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.intAction);
        int data = 1;

        // Asynchronous callback
        communication().callback(cmdInfo, data, response -> {
            log.info("{}", response.getInt());
        });

        // Synchronous call
        var response = communication().call(cmdInfo, data);
        return response.getInt();
    }

    @ActionMethod(CallCmd.callBool)
    private boolean callBool() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.boolAction);
        boolean data = true;

        // Asynchronous callback
        communication().callback(cmdInfo, data, response -> {
            log.info("{}", response.getBoolean());
        });

        // Synchronous call
        var response = communication().call(cmdInfo, data);
        return response.getBoolean();
    }

    @ActionMethod(CallCmd.callLong)
    private long callLong() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.longAction);
        long data = 1L;

        // Asynchronous callback
        communication().callback(cmdInfo, data, response -> {
            log.info("{}", response.getLong());
        });

        // Synchronous call
        var response = communication().call(cmdInfo, data);
        return response.getLong();
    }

    @ActionMethod(CallCmd.callString)
    private String callString() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.stringAction);
        String data = "hello";

        // Asynchronous callback
        communication().callback(cmdInfo, data, response -> {
            log.info("{}", response.getString());
        });

        // Synchronous call
        var response = communication().call(cmdInfo, data);
        return response.getString();
    }

    @ActionMethod(CallCmd.callObject)
    private BookMessage callObject() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.objectAction);
        var data = new AuthorMessage();

        // Asynchronous callback
        communication().callback(cmdInfo, data, response -> {
            log.info("{}", response.getValue(BookMessage.class));
        });

        // Synchronous call
        var response = communication().call(cmdInfo, data);
        return response.getValue(BookMessage.class);
    }

    @ActionMethod(CallCmd.callIntList)
    private List<Integer> callIntList() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.intListAction);
        List<Integer> dataList = List.of(1, 2);

        // Asynchronous callback
        communication().callbackListInt(cmdInfo, dataList, response -> {
            log.info("{}", response.listInt());
        });

        // Synchronous call
        var response = communication().callListInt(cmdInfo, dataList);
        return response.listInt();
    }

    @ActionMethod(CallCmd.callBoolList)
    private List<Boolean> callBoolList() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.boolListAction);
        List<Boolean> dataList = List.of(true, false);

        // Asynchronous callback
        communication().callbackListBool(cmdInfo, dataList, response -> {
            log.info("{}", response.listBoolean());
        });

        // Synchronous call
        var response = communication().callListBool(cmdInfo, dataList);
        return response.listBoolean();
    }

    @ActionMethod(CallCmd.callLongList)
    private List<Long> callLongList() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.longListAction);
        List<Long> dataList = List.of(1L, 2L);

        // Asynchronous callback
        communication().callbackListLong(cmdInfo, dataList, response -> {
            log.info("{}", response.listLong());
        });

        // Synchronous call
        var response = communication().callListLong(cmdInfo, dataList);
        return response.listLong();
    }

    @ActionMethod(CallCmd.callStringList)
    private List<String> callStringList() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.stringListAction);
        List<String> dataList = List.of("hello", "ionet");

        // Asynchronous callback
        communication().callbackListString(cmdInfo, dataList, response -> {
            log.info("{}", response.listString());
        });

        // Synchronous call
        var response = communication().callListString(cmdInfo, dataList);
        return response.listString();
    }

    @ActionMethod(CallCmd.callObjectList)
    private List<BookMessage> callObjectList() {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.objectListAction);

        var author1 = new AuthorMessage();
        author1.authorName = "David Myers";

        var author2 = new AuthorMessage();
        author2.authorName = "Gustave Le Bon";

        List<AuthorMessage> dataList = List.of(author1, author2);

        // Asynchronous callback
        communication().callback(cmdInfo, dataList, response -> {
            log.info("{}", response.listValue(BookMessage.class));
        });

        // Synchronous call
        var response = communication().call(cmdInfo, dataList);
        return response.listValue(BookMessage.class);
    }
}
