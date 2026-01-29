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
package com.iohao.cookbook.common.extension;

import com.iohao.net.framework.core.codec.DataCodecManager;
import com.iohao.net.framework.protocol.CommunicationMessage;
import com.iohao.net.framework.protocol.wrapper.LongValue;
import com.iohao.net.common.kit.concurrent.TaskKit;
import com.iohao.net.common.kit.time.TimeKit;
import com.iohao.net.external.core.netty.hook.DefaultSocketIdleHook;
import com.iohao.net.external.core.netty.hook.SocketIdleHook;
import com.iohao.net.external.core.session.UserSession;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-07
 * @since 25.1
 */
public class MyIdleHook implements SocketIdleHook {
    final DefaultSocketIdleHook defaultSocketIdleHook = new DefaultSocketIdleHook();
    /** currentTimeMillis */
    volatile byte[] timeBytes;

    @Override
    public void pongBefore(CommunicationMessage idleMessage) {
        /*
         * Set the time of the current server so that the time of the client and the server can be synchronized.
         * cn: 设置当前服务器的时间，以便客户端与服务器的时间同步。
         */
        idleMessage.setData(timeBytes);
    }

    @Override
    public boolean callback(UserSession userSession, IdleStateEvent event) {
        return defaultSocketIdleHook.callback(userSession, event);
    }

    public MyIdleHook() {
        updateTime();
        TaskKit.runInterval(this::updateTime, 1, TimeUnit.SECONDS);
    }

    private void updateTime() {
        var data = LongValue.of(TimeKit.currentTimeMillis());
        timeBytes = DataCodecManager.encode(data);
    }
}
