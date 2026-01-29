/*
 * ionet
 * Copyright (C) 2021 - 2023  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
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

import com.iohao.net.framework.core.flow.DefaultFlowContext;
import lombok.Setter;

import java.util.Objects;

/**
 * MyFlowContext
 *
 * @author 渔民小镇
 * @date 2023-07-24
 */
@Setter
public class MyFlowContext extends DefaultFlowContext {
    MyAttachment attachment;

    @Override
    @SuppressWarnings("unchecked")
    public MyAttachment getAttachment() {
        if (Objects.isNull(this.attachment)) {
            this.attachment = this.getAttachment(MyAttachment.class);
        }

        return this.attachment;
    }
}