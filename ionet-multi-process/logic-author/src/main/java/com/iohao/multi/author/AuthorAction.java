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
package com.iohao.multi.author;

import com.iohao.multi.common.AuthorCmd;
import com.iohao.multi.common.BookLibraryCmd;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.flow.FlowContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 *
 * @author 渔民小镇
 * @date 2026-03-27
 * @since 25.3
 */
@Slf4j
@ActionController(AuthorCmd.cmd)
public final class AuthorAction {

    @ActionMethod(AuthorCmd.hello)
    public String hello() {
        return "hello author";
    }

    @ActionMethod(AuthorCmd.listBook)
    public List<String> listBook(FlowContext flowContext) {

        var response = flowContext.call(BookLibraryCmd.of(BookLibraryCmd.listBook));

        return response.listString();
    }
}
