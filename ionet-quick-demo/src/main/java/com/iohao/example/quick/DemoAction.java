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
package com.iohao.example.quick;


import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.communication.CommunicationKit;

import java.util.List;
import java.util.stream.IntStream;

/**
 * @author 渔民小镇
 * @date 2023-01-06
 */
@ActionController(DemoCmd.cmd)
public class DemoAction {
    @ActionMethod(DemoCmd.here)
    private HelloMessage here(HelloMessage helloMessage) {
        HelloMessage newHelloMessage = new HelloMessage();
        newHelloMessage.name = helloMessage.name + ", I'm here";
        return newHelloMessage;
    }

    @ActionMethod(DemoCmd.jackson)
    private HelloMessage jackson(HelloMessage helloMessage) {
        // Example exception mechanism demonstration
        // cn: 示例 异常机制演示
        ErrorCode.nameChecked.assertTrue("Jackson".equals(helloMessage.name));

        helloMessage.name = "Hello, " + helloMessage.name;
        return helloMessage;
    }

    @ActionMethod(DemoCmd.list)
    private List<HelloMessage> list() {
        // Get a List data and return it to the request client
        // cn: 得到一个 List 列表数据，并返回给请求端
        return IntStream.range(1, 5).mapToObj(id -> {
            HelloMessage helloMessage = new HelloMessage();
            helloMessage.name = "data:" + id;
            return helloMessage;
        }).toList();
    }

    @ActionMethod(DemoCmd.triggerBroadcast)
    private void triggerBroadcast() {
        var communication = CommunicationKit.getCommunication();

        // broadcast object
        var message1 = new HelloMessage();
        message1.name = "name-1";
        communication.broadcastMulticast(DemoCmd.listenData, message1);

        // broadcast list
        var message2 = new HelloMessage();
        message2.name = "name-2";
        communication.broadcastMulticast(DemoCmd.listenList, List.of(message1, message2));
    }
}
