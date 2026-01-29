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

import com.iohao.cookbook.common.PressureCmd;
import com.iohao.cookbook.common.extension.UserKit;
import com.iohao.cookbook.common.message.ErrorCode;
import com.iohao.cookbook.common.message.UserMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.flow.FlowContext;
import com.iohao.net.common.kit.concurrent.TaskKit;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

/**
 * PressureAction
 *
 * @author 渔民小镇
 * @date 2025-11-11
 * @since 25.1
 */
@Slf4j
@ActionController(PressureCmd.cmd)
public final class PressureAction {
    public static final LongAdder inc = new LongAdder();
    AtomicBoolean init = new AtomicBoolean();

    @ActionMethod(PressureCmd.login)
    private UserMessage login(String jwt, FlowContext flowContext) {
        extractedInc();

        long userId = Integer.parseInt(jwt);

        boolean success = flowContext.bindingUserId(userId);
        ErrorCode.loginFailed.assertTrue(success);

        return UserKit.ofUserMessage(userId);
    }

    @ActionMethod(PressureCmd.inc)
    private boolean inc() {
        inc.increment();
        return true;
    }


    private void extractedInc() {
        if (!init.get() && init.compareAndSet(false, true)) {
            TaskKit.runInterval(() -> {
                long value = inc.longValue();
                log.info("  inc {}", value);
            }, 1, TimeUnit.SECONDS);
        }
    }
}
