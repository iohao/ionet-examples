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

import com.iohao.multi.common.MyLogicServerKit;
import com.iohao.net.framework.core.BarSkeletonBuilder;
import com.iohao.net.framework.protocol.ServerBuilder;
import com.iohao.net.server.LogicServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 渔民小镇
 * @date 2026-03-27
 * @since 25.3
 */
public final class BookLibraryLogicServer implements LogicServer {

    @Override
    public void settingBarSkeletonBuilder(BarSkeletonBuilder builder) {
        MyLogicServerKit.defaultSetting(builder, BookLibraryAction.class);
    }

    @Override
    public void settingServerBuilder(ServerBuilder builder) {
        builder.setName("book-library-LogicServer");
    }
}
