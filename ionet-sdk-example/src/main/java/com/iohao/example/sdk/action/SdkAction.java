/*
 * ionet
 * Copyright (C) 2021 - present  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General public License for more details.
 *
 * You should have received a copy of the GNU Affero General public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.iohao.example.sdk.action;


import com.iohao.example.sdk.data.*;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.doc.DocumentMethod;
import com.iohao.net.framework.core.flow.FlowContext;
import com.iohao.net.common.kit.RandomKit;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import net.datafaker.providers.base.Name;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * My SdkAction
 *
 * @author 渔民小镇
 * @date 2024-11-01
 */
@Slf4j
@ActionController(SdkCmd.cmd)
public final class SdkAction {
    static final Name name = new Faker(Locale.getDefault()).name();
    AtomicInteger inc = new AtomicInteger();

    /**
     * user login
     *
     * @param verifyMessage loginVerify
     * @param flowContext   flowContext
     * @return User info
     */
    @ActionMethod(SdkCmd.loginVerify)
    private UserMessage loginVerify(LoginVerifyMessage verifyMessage, FlowContext flowContext) {
        log.info("login ----- {}", verifyMessage);

        UserMessage userMessage = ofUserMessage(verifyMessage);

        // 绑定 userId，表示登录
        var success = flowContext.bindingUserId(userMessage.userId);
        ErrorCode.loginError.assertTrue(success);

        return userMessage;
    }

    /**
     * test int
     *
     * @param value value
     * @return int value
     */
    @ActionMethod(SdkCmd.intValue)
    private int intValue(int value) {
        return value + inc.getAndIncrement();
    }

    /**
     * test long
     *
     * @param value value
     * @return long value
     */
    @ActionMethod(SdkCmd.longValue)
    private long longValue(long value) {
        return value - inc.getAndIncrement();
    }

    /**
     * test boolean
     *
     * @param value value
     * @return boolean value
     */
    @ActionMethod(SdkCmd.boolValue)
    private boolean boolValue(boolean value) {
        return !value;
    }

    /**
     * test String
     *
     * @param value value
     * @return String value
     */
    @ActionMethod(SdkCmd.stringValue)
    private String stringValue(String value) {
        return value + inc.getAndIncrement();
    }

    /**
     * test Object；测试单个对象的接收与响应。
     *
     * @param loginVerifyMessage loginVerify；登录对象。
     * @return UserMessage；用户数据。
     */
    @ActionMethod(SdkCmd.value)
    private UserMessage value(LoginVerifyMessage loginVerifyMessage) {
        return ofUserMessage(loginVerifyMessage);
    }

    private UserMessage ofUserMessage(LoginVerifyMessage loginVerifyMessage) {
        long userId = Long.parseLong(loginVerifyMessage.jwt);
        return ofUserMessage(userId);
    }

    private UserMessage ofUserMessage(long userId) {
        var userMessage = new UserMessage();
        userMessage.userId = userId;
        userMessage.name = name.fullName();
        return userMessage;
    }

    /**
     * test int list
     *
     * @param value value
     * @return int list
     */
    @ActionMethod(SdkCmd.listInt)
    private List<Integer> listInt(List<Integer> value) {
        return value.stream()
                .map(i -> i + inc.getAndIncrement())
                .toList();
    }

    /**
     * test Long list
     *
     * @param value value
     * @return Long list
     */
    @ActionMethod(SdkCmd.listLong)
    private List<Long> listLong(List<Long> value) {
        return value.stream()
                .map(i -> i - inc.getAndIncrement())
                .toList();
    }

    /**
     * test Boolean list
     *
     * @param value value
     * @return Boolean list
     */
    @ActionMethod(SdkCmd.listBool)
    private List<Boolean> listBool(List<Boolean> value) {
        return value.stream()
                .map(i -> !i)
                .toList();
    }

    /**
     * test String list
     *
     * @param value value
     * @return String list
     */
    @ActionMethod(SdkCmd.listString)
    private List<String> listString(List<String> value) {
        return value.stream()
                .map(i -> i + inc.getAndIncrement())
                .toList();
    }

    /**
     * test Object list
     *
     * @param value LoginVerifyMessage list
     * @return UserMessage list
     */
    @ActionMethod(SdkCmd.listValue)
    private List<UserMessage> listValue(List<LoginVerifyMessage> value) {
        return value.stream()
                .map(this::ofUserMessage)
                .toList();
    }

    /**
     * test error code
     *
     * @param value If the value is equal to 2, an error will be thrown
     * @return int
     */
    @ActionMethod(SdkCmd.testError)
    private int testError(int value) {
        ErrorCode.testError.assertTrueThrows(value == 2);
        return value;
    }

    /**
     * test broadcast
     *
     * @param flowContext flowContext
     */
    @ActionMethod(SdkCmd.triggerBroadcast)
    private void triggerBroadcast(FlowContext flowContext) {
        BulletMessage bulletMessage = new BulletMessage();
        bulletMessage.bulletId = inc.getAndIncrement();
        flowContext.broadcastMe(SdkCmd.broadcastBulletMessage, bulletMessage);

        // emptyValue
        flowContext.broadcastMe(SdkCmd.broadcastEmpty);

        // -------------- single value --------------
        int dataInt = inc.getAndIncrement();
        flowContext.broadcastMe(SdkCmd.broadcastInt, dataInt);

        long dataLong = inc.getAndIncrement();
        flowContext.broadcastMe(SdkCmd.broadcastLong, dataLong);

        boolean dataBool = RandomKit.randomBoolean();
        flowContext.broadcastMe(SdkCmd.broadcastBool, dataBool);

        String dataString = inc.getAndIncrement() + "";
        flowContext.broadcastMe(SdkCmd.broadcastString, dataString);

        UserMessage dataObject = ofUserMessage(inc.getAndIncrement());
        flowContext.broadcastMe(SdkCmd.broadcastValue, dataObject);

        // -------------- list value --------------
        List<Integer> dataIntList = List.of(inc.getAndIncrement(), inc.getAndIncrement());
        flowContext.broadcastMeListInt(SdkCmd.broadcastListIntValue, dataIntList);

        List<Long> dataLongList = List.of((long) inc.getAndIncrement(), (long) inc.getAndIncrement());
        flowContext.broadcastMeListLong(SdkCmd.broadcastListLong, dataLongList);

        List<Boolean> dataBoolList = List.of(RandomKit.randomBoolean(), RandomKit.randomBoolean());
        flowContext.broadcastMeListBool(SdkCmd.broadcastListBool, dataBoolList);

        List<String> dataStringList = List.of(inc.getAndIncrement() + "-a", inc.getAndIncrement() + "-b");
        flowContext.broadcastMeListString(SdkCmd.broadcastListString, dataStringList);

        var dataObjectList = IntStream.range(1, 3).mapToObj(this::ofUserMessage).toList();
        flowContext.broadcastMe(SdkCmd.broadcastListValue, dataObjectList);
    }

    /**
     * noParam method test. 没有参数的方法测试
     *
     * @return counter
     */
    @ActionMethod(SdkCmd.noParam)
    private int noParam() {
        return inc.getAndIncrement();
    }

    /**
     * noReturn method test. 没有返回值的方法测试
     *
     * @param name name
     */
    @ActionMethod(SdkCmd.noReturn)
    @DocumentMethod("noReturnMethod")
    private void noReturn(String name) {
        inc.getAndIncrement();
    }

    @ActionMethod(SdkCmd.internalAddMoney)
    private int internalAddMoney(int money) {
        return RandomKit.randomInt(money);
    }

    @ActionMethod(SdkCmd.bulletMessage)
    private BulletMessage bulletMessage(BulletMessage message) {

        BulletMessage newMessage = new BulletMessage();
        newMessage.name = message.name;
        newMessage.bulletId = inc.getAndIncrement();
        return newMessage;
    }
}