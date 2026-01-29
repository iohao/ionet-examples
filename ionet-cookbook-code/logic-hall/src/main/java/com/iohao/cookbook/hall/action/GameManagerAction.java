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

import com.iohao.cookbook.common.GameManagerCmd;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.flow.FlowContext;
import lombok.extern.slf4j.Slf4j;

import static com.iohao.cookbook.common.BroadcastCmd.*;

/**
 *
 * @author 渔民小镇
 * @date 2025-11-06
 * @since 25.1
 */
@Slf4j
@ActionController(GameManagerCmd.cmd)
public final class GameManagerAction {
    @ActionMethod(GameManagerCmd.recharge)
    private long internalRecharge(long money, FlowContext flowContext) {
        long userId = flowContext.getUserId();
        flowContext.broadcastMe(broadcastUserLong, money);

        log.info("userId:{}, recharge:{}", userId, money);

        return money;
    }

    @ActionMethod(GameManagerCmd.notice)
    private void internalNotice(String message, FlowContext flowContext) {
        log.info("broadcastMulticast:{}", message);
        flowContext.broadcastMulticast(broadcastMulticastString, message);
    }

    @ActionMethod(GameManagerCmd.test)
    private long test(long money) {
        return money;
    }
}
