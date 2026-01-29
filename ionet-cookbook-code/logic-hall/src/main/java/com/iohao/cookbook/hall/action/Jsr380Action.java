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

import com.iohao.cookbook.common.Jsr380Cmd;
import com.iohao.cookbook.common.message.ValidMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import jakarta.validation.Valid;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-07
 * @since 25.1
 */
@ActionController(Jsr380Cmd.cmd)
public class Jsr380Action {
    @ActionMethod(Jsr380Cmd.verifyData)
    private ValidMessage verifyData(@Valid ValidMessage message) {
        var newMessage = new ValidMessage();
        newMessage.email = message.email;
        newMessage.age = message.age * 10;
        return newMessage;
    }
}
