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
package com.iohao.cookbook;

import com.iohao.cookbook.common.PressureCmd;
import com.iohao.cookbook.common.message.UserMessage;
import com.iohao.net.common.kit.LocaleKit;
import com.iohao.net.common.kit.concurrent.IntervalTaskListener;
import com.iohao.net.common.kit.concurrent.TaskKit;
import com.iohao.net.extension.client.AbstractInputCommandRegion;
import com.iohao.net.extension.client.join.ClientRunOne;
import com.iohao.net.extension.client.kit.ClientUserConfigs;
import com.iohao.net.extension.client.user.ClientUser;
import com.iohao.net.extension.client.user.PressureKit;
import com.iohao.net.extension.client.user.DefaultClientUser;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.iohao.cookbook.common.PressureCmd.*;

/**
 *
 * @author 渔民小镇
 * @date 2025-11-11
 * @since 25.1
 */
@Slf4j
public class PressureClient {
    /**
     * cn: 需要模拟压测的用户数量.
     * The number of users to simulate for pressure testing
     */
    static int pressureUserSize = 80;
    /**
     * cn: 每个用户每秒的请求次数.
     * Requests per user per second
     */
    static int pressureSecondsRequest = 10;
    /**
     * cn: 共测 N 轮.
     * Total N rounds of testing
     */
    static int pressureRound = 10;

    static void main() throws InterruptedException {
        ClientUserConfigs.closeLog();

        for (int i = 1; i <= pressureUserSize; i++) {
            long userId = i;
            TaskKit.executeVirtual(() -> {
                ClientUser clientUser = new DefaultClientUser();
                clientUser.setJwt(String.valueOf(userId));

                new ClientRunOne()
                        .setClientUser(clientUser)
                        .setInputCommandRegions(List.of(new PressureRegion()))
                        .startup();
            });
        }

        PressureKit.addAfterLoginTask(() -> pressureLog(pressureUserSize, pressureSecondsRequest, pressureRound));
        TimeUnit.SECONDS.sleep(1);
    }

    static class PressureRegion extends AbstractInputCommandRegion {
        AtomicInteger count = new AtomicInteger();

        @Override
        public void connectionComplete() {
            this.executeCommand(login);
        }

        @Override
        public void initInputCommand() {
            this.setCmd(PressureCmd.cmd, "Pressure");

            ofCommand(login, "login").setRequestData(() -> {
                ClientUser clientUser = getClientUser();
                return clientUser.getJwt();
            }).callback(result -> {
                UserMessage message = result.getValue(UserMessage.class);
                log.info("loginSuccess: {}", message);
                this.setUserId(message.id);
            });

            ofCommand(inc).setTitle("inc").callback(_ -> {
            });

            // cn: 当用户全部登录完成后会执行的任务
            // Tasks to be executed after all users have completed login
            PressureKit.addAfterLoginTask(this::ppp);
        }

        private void ppp() {
            TaskKit.runInterval(new IntervalTaskListener() {
                @Override
                public void onUpdate() {
                    // Request inc action.
                    for (int i = 0; i < pressureSecondsRequest; i++) {
                        executeCommand(PressureCmd.inc);
                    }
                }

                @Override
                public boolean isActive() {
                    // cn: 停止测试条件
                    // Stop test condition
                    return count.incrementAndGet() <= pressureRound;
                }
            }, 1, TimeUnit.SECONDS);
        }
    }

    private static void pressureLog(int pressureUserSize, int pressureSecondsRequest, int pressureRound) {
        if (LocaleKit.isChina()) {
            String pressureFormat = """
                    
                    请求总次数: %,d
                    模拟用户总数: %d
                    每个用户每秒请求次数: %,d
                    每秒请求总次数: %,d
                    共测 %,d 轮
                    """.formatted(
                    (pressureUserSize * pressureSecondsRequest * pressureRound),
                    pressureUserSize,
                    pressureSecondsRequest,
                    (pressureUserSize * pressureSecondsRequest),
                    pressureRound
            );

            log.info("{}", pressureFormat);
        } else {
            String pressureFormat = """
                    
                    Total Requests: %,d
                    Total Simulated Users: %d
                    Requests per User per Second: %,d
                    Total Requests per Second: %,d
                    Total Rounds: %,d
                    """.formatted(
                    (pressureUserSize * pressureSecondsRequest * pressureRound),
                    pressureUserSize,
                    pressureSecondsRequest,
                    (pressureUserSize * pressureSecondsRequest),
                    pressureRound
            );

            log.info("{}", pressureFormat);
        }
    }
}