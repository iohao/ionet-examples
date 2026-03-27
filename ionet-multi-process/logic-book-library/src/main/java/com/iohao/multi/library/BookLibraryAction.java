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
package com.iohao.multi.library;

import com.iohao.multi.common.BookLibraryCmd;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author 渔民小镇
 * @date 2026-03-27
 * @since 25.3
 */
@Slf4j
@ActionController(BookLibraryCmd.cmd)
public final class BookLibraryAction {

    AtomicInteger inc = new AtomicInteger(0);

    @ActionMethod(BookLibraryCmd.listBook)
    public List<String> listBook() {
        return List.of("book-" + inc.incrementAndGet(), "book-" + inc.incrementAndGet());
    }
}
