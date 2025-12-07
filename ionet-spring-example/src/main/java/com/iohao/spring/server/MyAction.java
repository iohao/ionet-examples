/*
 * ionet
 * Copyright (C) 2021 - present  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General  License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General  License for more details.
 *
 * You should have received a copy of the GNU Affero General  License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.iohao.spring.server;

import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import org.springframework.stereotype.Component;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-04
 * @since 25.1
 */
@Component
@ActionController(MyCmd.cmd)
public class MyAction {
    final MyService service;

    private MyAction(MyService service) {
        this.service = service;
    }

    @ActionMethod(MyCmd.getTime)
    private String getTime() {
        return service.getTime();
    }
}
