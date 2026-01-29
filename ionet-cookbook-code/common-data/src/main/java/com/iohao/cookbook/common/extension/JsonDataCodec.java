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
package com.iohao.cookbook.common.extension;

import com.iohao.net.framework.core.codec.DataCodec;
import com.iohao.net.common.kit.ArrayKit;
import com.iohao.net.common.kit.CommonConst;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-08
 * @since 25.1
 */
@Slf4j
public class JsonDataCodec implements DataCodec {
    @Override
    public byte[] encode(Object data) {

        if (Objects.isNull(data)) {
            return CommonConst.emptyBytes;
        }

        try {
            return JsonKit.objectMapper.writeValueAsBytes(data);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }

        return CommonConst.emptyBytes;
    }

    @Override
    public <T> T decode(byte[] data, Class<T> dataClass) {

        if (ArrayKit.isEmpty(data)) {
            return null;
        }

        try {
            return JsonKit.objectMapper.readValue(data, dataClass);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
