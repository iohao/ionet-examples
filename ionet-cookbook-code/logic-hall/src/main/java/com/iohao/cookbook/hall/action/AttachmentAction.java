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

import com.iohao.cookbook.common.AttachmentCmd;
import com.iohao.cookbook.common.extension.JsonKit;
import com.iohao.cookbook.common.extension.MyAttachment;
import com.iohao.cookbook.common.extension.MyFlowContext;
import com.iohao.cookbook.common.extension.UserKit;
import com.iohao.cookbook.common.message.UserMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.flow.FlowContext;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-08
 * @since 25.1
 */
@Slf4j
@ActionController(AttachmentCmd.cmd)
public class AttachmentAction {
    final AtomicInteger updateCount = new AtomicInteger(1);

    @ActionMethod(AttachmentCmd.updateAttachment)
    private boolean updateAttachment(MyFlowContext flowContext) {
        log.info("updateAttachment {}", flowContext.getUserId());

        long userId = flowContext.getUserId();
        UserMessage userMessage = UserKit.getUserMessage(userId);

        MyAttachment attachment = new MyAttachment();
        attachment.userId = userId;
        attachment.nickname = userMessage.nickname;
        attachment.updateCount = updateCount.getAndIncrement();

        // set and update attachment
        flowContext.setAttachment(attachment);
        flowContext.updateAttachment();

        return true;
    }

    @ActionMethod(AttachmentCmd.printAttachment)
    private String printAttachment(FlowContext flowContext) {
        MyAttachment attachment = flowContext.getAttachment();
        return JsonKit.toJson(attachment);
    }
}
