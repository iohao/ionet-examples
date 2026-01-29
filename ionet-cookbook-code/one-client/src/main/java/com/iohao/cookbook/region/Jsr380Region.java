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
package com.iohao.cookbook.region;


import com.iohao.cookbook.common.Jsr380Cmd;
import com.iohao.cookbook.common.message.ValidMessage;
import com.iohao.net.extension.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-07
 * @since 25.1
 */
@Slf4j
public class Jsr380Region extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        this.setCmd(Jsr380Cmd.cmd, "Jsr380");

        AtomicInteger inc = new AtomicInteger();
        ofCommand(Jsr380Cmd.verifyData, "verifyData").setRequestData(() -> {
            ValidMessage message = new ValidMessage();
            int value = inc.getAndIncrement() % 3;

            if (value == 0) {
                // Age is not eligible
                message.age = 1;
                message.email = "luoyizhu@gmail.com";
            } else if (value == 1) {
                message.age = 99;
                // Email format is incorrect
                message.email = "luoyizhu";
            } else {
                // success
                message.age = 99;
                message.email = "luoyizhu@gmail.com";
            }

            return message;
        }).callback(result -> {
            log.info("{}", result.getValue(ValidMessage.class));
        });
    }
}
