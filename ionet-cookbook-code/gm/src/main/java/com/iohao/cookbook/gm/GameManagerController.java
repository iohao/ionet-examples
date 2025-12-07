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
package com.iohao.cookbook.gm;

import com.iohao.cookbook.common.GameManagerCmd;
import com.iohao.net.framework.communication.CommunicationKit;
import com.iohao.net.common.kit.RandomKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * @author 渔民小镇
 * @date 2025-11-05
 * @since 25.1
 */
@Slf4j
@RestController
@RequestMapping("gm")
public final class GameManagerController {
    static final AtomicLong messageId = new AtomicLong();
    static final long userId = 1378604058;

    @GetMapping("/notice")
    public String notice() {
        var noticeCmd = GameManagerCmd.of(GameManagerCmd.notice);
        var communication = CommunicationKit.getCommunication();

        String message = "GM web message " + messageId.incrementAndGet();
        var sendMessage = communication.ofSendMessage(noticeCmd, message);
        communication.send(sendMessage);

        log.info("notice:{}", message);

        return "notice: " + message;
    }

    @GetMapping("/recharge")
    public String recharge() {
        var rechargeCmd = GameManagerCmd.of(GameManagerCmd.recharge);
        var communication = CommunicationKit.getCommunication();

        long money = RandomKit.random(1, 1000);
        var requestMessage = communication.ofRequestMessage(rechargeCmd, money);
        requestMessage.bindingUserId(userId);
        var response = communication.call(requestMessage);

        log.info("recharge:{}", response.getLong());
        return "userId:%s, recharge %d".formatted(userId, response.getLong());
    }
}
