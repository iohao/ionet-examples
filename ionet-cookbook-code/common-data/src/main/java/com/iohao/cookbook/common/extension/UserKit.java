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

import com.iohao.cookbook.common.message.UserMessage;
import com.iohao.net.common.kit.CollKit;
import lombok.experimental.UtilityClass;
import net.datafaker.Faker;
import net.datafaker.providers.base.Name;

import java.util.Locale;
import java.util.Map;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-08
 * @since 25.1
 */
@UtilityClass
public class UserKit {
    final Map<Long, UserMessage> userMap = CollKit.ofConcurrentHashMap();
    final Name name = new Faker(Locale.getDefault()).name();

    public UserMessage ofUserMessage(long userId) {
        UserMessage userMessage = new UserMessage();
        userMessage.id = userId;
        userMessage.nickname = name.fullName();

        userMap.put(userId, userMessage);

        return userMessage;
    }

    public UserMessage getUserMessage(long userId) {
        return userMap.get(userId);
    }
}
