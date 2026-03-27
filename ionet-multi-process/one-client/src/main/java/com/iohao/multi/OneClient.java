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
package com.iohao.multi;

import com.iohao.net.extension.client.InputCommandRegion;
import com.iohao.net.extension.client.join.ClientRunOne;
import com.iohao.net.extension.client.kit.ClientUserConfigs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

/**
 *
 * @author 渔民小镇
 * @date 2026-03-27
 * @since 25.3
 */
public final class OneClient {
    static void main() {
        // US or CHINA
//        Locale.setDefault(Locale.US);
        Locale.setDefault(Locale.CHINA);
        // closeLog. cn: 关闭模拟请求相关日志
        ClientUserConfigs.closeLog();

        new ClientRunOne()
                .setInputCommandRegions(listInputCommandRegion())
                .startup();
    }

    static List<InputCommandRegion> listInputCommandRegion() {
        return List.of(
                new AuthorRegion()
        );
    }
}
