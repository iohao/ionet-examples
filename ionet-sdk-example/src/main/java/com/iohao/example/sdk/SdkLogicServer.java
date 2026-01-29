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
package com.iohao.example.sdk;

import com.iohao.example.sdk.action.*;
import com.iohao.example.sdk.data.*;
import com.iohao.net.framework.core.BarSkeletonBuilder;
import com.iohao.net.framework.core.doc.BroadcastDocument;
import com.iohao.net.framework.core.flow.internal.DebugInOut;
import com.iohao.net.framework.protocol.ServerBuilder;
import com.iohao.net.framework.protocol.wrapper.*;
import com.iohao.net.server.LogicServer;

/**
 * @author 渔民小镇
 * @date 2024-11-01
 */
public final class SdkLogicServer implements LogicServer {
    @Override
    public void settingBarSkeletonBuilder(BarSkeletonBuilder builder) {
        builder.scanActionPackage(SdkAction.class);

        extractedDoc(builder);

        builder.addInOut(new DebugInOut());
    }

    @Override
    public void settingServerBuilder(ServerBuilder builder) {
        builder.setName("SdkLogicServer");
    }

    private void extractedDoc(BarSkeletonBuilder builder) {
        builder.addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastBulletMessage)
                // broadcast type
                .setDataClass(BulletMessage.class)
                .setMethodDescription("trigger bullet broadcast; cn:触发子弹广播")
                .setMethodName("bulletBroadcast")
        ).addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastEmpty)
                .setMethodDescription("test Empty Value")
                .setMethodName("EmptyValue")
        );

        // single value
        builder.addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastInt)
                .setDataClass(int.class, "biz data description")
                .setMethodDescription("IntValue method description")
                .setMethodName("IntValue")
        ).addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastLong)
                .setDataClass(long.class)
                .setMethodDescription("LongValue")
                .setMethodName("LongValue")
        ).addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastBool)
                .setDataClass(boolean.class)
                .setMethodDescription("BoolValue")
                .setMethodName("BoolValue")
        ).addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastString)
                .setDataClass(String.class)
                .setMethodDescription("StringValue")
                .setMethodName("StringValue")
        ).addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastValue)
                .setDataClass(UserMessage.class)
                .setMethodDescription("UserMessage")
                .setMethodName("UserMessage")
        );

        // list value
        builder.addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastListIntValue)
                .setDataClassList(int.class, "biz id list")
                .setMethodDescription("IntValueList method description")
                .setMethodName("IntValueList")
        ).addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastListLong)
                .setDataClassList(long.class)
                .setMethodDescription("LongValueList")
                .setMethodName("LongValueList")
        ).addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastListBool)
                .setDataClassList(boolean.class)
                .setMethodDescription("BoolValueList")
                .setMethodName("BoolValueList")
        ).addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastListString)
                .setDataClassList(String.class)
                .setMethodDescription("StringValueList")
                .setMethodName("StringValueList")
        ).addBroadcastDocument(BroadcastDocument.builder(SdkCmd.broadcastListValue)
                .setDataClassList(UserMessage.class)
                .setMethodDescription("UserMessageList")
                .setMethodName("UserMessageList")
        );
    }
}