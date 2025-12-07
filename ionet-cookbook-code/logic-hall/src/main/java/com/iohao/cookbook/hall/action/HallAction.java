package com.iohao.cookbook.hall.action;

import com.iohao.cookbook.common.HallCmd;
import com.iohao.cookbook.common.extension.UserKit;
import com.iohao.cookbook.common.extension.VirtualThread;
import com.iohao.cookbook.common.message.ErrorCode;
import com.iohao.cookbook.common.message.UserMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.communication.CommunicationKit;
import com.iohao.net.framework.core.flow.FlowContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ActionController(HallCmd.cmd)
public class HallAction {

    @VirtualThread
    @ActionMethod(HallCmd.loginVerify)
    private UserMessage loginVerify(String jwt, FlowContext flowContext) {
        long userId = Math.abs(jwt.hashCode());

        boolean success = flowContext.bindingUserId(userId);
        ErrorCode.loginFailed.assertTrue(success);

//        extracted(userId);

        return UserKit.ofUserMessage(userId);
    }

    @ActionMethod(HallCmd.hello)
    private String hello(FlowContext flowContext) {
        return "hello " + flowContext.getUserId();
    }

    private static void extracted(long userId) {
        boolean existUser = CommunicationKit.existUser(userId);
        log.info("existUser {}", existUser);
    }

    @ActionMethod(HallCmd.internalOffline)
    private void internalOffline(FlowContext flowContext) {
        long userId = flowContext.getUserId();
        log.info("UserOffline {}", userId);
    }
}
