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
import com.iohao.cookbook.common.SpeedCmd;
import com.iohao.net.framework.CoreGlobalConfig;
import com.iohao.net.framework.communication.CommunicationKit;
import com.iohao.net.framework.core.kit.DevKit;
import com.iohao.net.framework.protocol.Response;
import com.iohao.net.framework.protocol.Server;
import com.iohao.net.common.Publisher;
import com.iohao.net.common.kit.time.TimeKit;
import com.iohao.net.external.core.message.CommunicationMessageKit;
import com.iohao.net.server.FindServer;
import com.iohao.net.server.NetServerSetting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * @author 渔民小镇
 * @date 2025-11-09
 * @since 25.1
 */
@Slf4j
@RestController
@RequestMapping("speed")
public final class SpeedController {
    LongAdder longAdder = new LongAdder();

    @GetMapping("/requestVoidBefore/{count}")
    public String requestVoidBefore(@PathVariable int count) throws InterruptedException {
        var sendCount = 10_000 * count;
        longAdder.reset();

        var communication = CommunicationKit.getCommunication();
        var cmd = SpeedCmd.of(SpeedCmd.requestVoidBefore);

        communication.callback(cmd, sendCount, _ -> {
            var requestCmd = SpeedCmd.of(SpeedCmd.requestVoid);
            for (int i = 0; i < sendCount; i++) {
                communication.send(requestCmd);
            }

            System.out.println("___sendCount:" + sendCount);
        });

        return "ok";
    }

    @GetMapping("/requestBoolBefore/{count}")
    public String requestBoolBefore(@PathVariable int count) throws InterruptedException {
        var sendCount = 10_000 * count;
        longAdder.reset();

        var communication = CommunicationKit.getCommunication();
        var cmd = SpeedCmd.of(SpeedCmd.requestBoolBefore);

        communication.callback(cmd, sendCount, response -> {
            var requestCmd = SpeedCmd.of(SpeedCmd.requestBool);
            for (int i = 0; i < sendCount; i++) {
                communication.callback(requestCmd, _ -> {
                });
            }
        });

        return "ok";
    }

    @GetMapping("/call/{count}")
    public String call(@PathVariable int count) throws InterruptedException {

        DevKit.reset();

        var cmd = GameManagerCmd.of(GameManagerCmd.test);
        var communication = CommunicationKit.getCommunication();
        long nanoTime = System.nanoTime();

        for (int i = 1; i <= count; i++) {
            var requestMessage = communication.ofRequestMessage(cmd, i);
            requestMessage.bindingUserId(i);
            // ... set message properties ...
            var response = communication.call(requestMessage);
        }

        var millis = TimeKit.elapsedMillis(nanoTime);

        TimeUnit.MILLISECONDS.sleep(100);

        String ofFutureTimes = DevKit.toString("call-FutureTimes", DevKit.ofFutureTimes);
        String requestSbeTimes = DevKit.toString("call-requestSbeTimes", DevKit.requestSbeTimes);

        String requestOnFragmentTimes = DevKit.toString("requestOnFragmentTimes", DevKit.requestOnFragmentTimes);
        String handleTimes = DevKit.toString("handleTimes", DevKit.handleTimes);
        String responseSbeTimes = DevKit.toString("responseSbeTimes", DevKit.responseSbeTimes);

        DevKit.monitor.printStats("Aeron IPC");

        return """
                millis:%,d
                avgMicrosTime:%,d
                
                %s
                %s
                
                %s
                %s
                %s
                """.formatted(millis
                , DevKit.avgMicrosTime.sum()

                , ofFutureTimes
                , requestSbeTimes

                , requestOnFragmentTimes
                , handleTimes
                , responseSbeTimes
        ).replaceAll("\n", "<br/>");
    }

    @GetMapping("/allOf/{count}")
    public String allOf(@PathVariable int count) throws InterruptedException {
        var cmd = GameManagerCmd.of(GameManagerCmd.test);
        var communication = CommunicationKit.getCommunication();
        long nanoTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(10); // 例如，使用 10 个线程

        List<CompletableFuture<Response>> futures = new ArrayList<>();

        DevKit.devEnterprise.set(true);

        for (int i = 0; i < count; i++) {
            final int currentUserId = i + 1;
            var future = CompletableFuture.supplyAsync(() -> {
                var requestMessage = communication.ofRequestMessage(cmd, currentUserId);
                requestMessage.bindingUserId(currentUserId);
                // ... set message properties ...
                return communication.call(requestMessage);
            }, executor);
            futures.add(future);
        }

        // 等待所有任务完成 (测量的是最慢一次 RTT 的完成时间 + 批处理开销)
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // ... 测量时间 ...
        // 此时测量的 millis 应该是 Max(RTT) + 少量开销，而不是 100 * Avg(RTT)

        executor.shutdown();

        DevKit.devEnterprise.set(false);

        var millis = TimeKit.elapsedMillis(nanoTime);

        TimeUnit.MILLISECONDS.sleep(100);

        StringBuilder builder = new StringBuilder();
        builder.append("millis:%s".formatted(millis));

        return builder.toString();
    }


    @GetMapping("/externalBefore/{count}")
    public String externalBefore(@PathVariable int count) throws InterruptedException {
        var sendCount = 10_000 * count;

        longAdder.reset();
        DevKit.userResponseMessageOnFragmentInc = 0;

        var communication = CommunicationKit.getCommunication();
        communication.getTraceId();
        var cmd = SpeedCmd.of(SpeedCmd.requestBoolBefore);

        communication.callback(cmd, sendCount, response -> {
            NetServerSetting setting = GameManagerKit.setting;

            var requestCmd = SpeedCmd.of(SpeedCmd.requestBool);

            var message = CommunicationMessageKit.createCommunicationMessage();
            message.setCmdInfo(requestCmd);
            message.setNetId(CoreGlobalConfig.getNetId());
            message.bindingUserId(1);
            message.setExternalServerId(DevKit.externalId);

            FindServer findServer = setting.findServer();
            Server server = findServer.getServer(message);

            Publisher publisher = setting.publisher();
            String pubName = server.pubName();

            for (int i = 0; i < sendCount; i++) {
                publisher.publishMessage(pubName, message);
            }

            System.out.println("___sendCount:" + sendCount);
        });

        return "ok";
    }
}
