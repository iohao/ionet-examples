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

import com.iohao.cookbook.common.OnExternalCmd;
import com.iohao.cookbook.common.extension.MyOnExternalTemplateId;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.flow.FlowContext;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-29
 * @since 25.1
 */
@Slf4j
@ActionController(OnExternalCmd.cmd)
public final class OnExternalAction {
    @ActionMethod(OnExternalCmd.userIp)
    private String getIp(FlowContext flowContext) {
        var externalResponse = flowContext.callExternal(MyOnExternalTemplateId.userIp);
        return externalResponse.getPayloadAsString();
    }

    @ActionMethod(OnExternalCmd.userOnlineCount)
    private int userOnlineCount(FlowContext flowContext) {
        int onlineCount = 0;

        var responseCollectExternal = flowContext.callCollectExternal(MyOnExternalTemplateId.userOnlineCount);
        for (var externalResponse : responseCollectExternal.getResponseList()) {
            int externalServerId = externalResponse.getExternalServerId();
            int count = externalResponse.getPayloadAsInt();
            log.info("externalServerId:{}, count:{}", externalServerId, count);

            onlineCount += count;
        }

        return onlineCount;
    }
}
