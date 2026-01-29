package com.iohao.cookbook.region;

import com.iohao.cookbook.common.HallCmd;
import com.iohao.cookbook.common.message.UserMessage;
import com.iohao.net.extension.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

import static com.iohao.cookbook.common.HallCmd.*;

@Slf4j
public class HallRegion extends AbstractInputCommandRegion {

    @Override
    public void connectionComplete() {
        this.executeCommand(HallCmd.loginVerify);
        log.info("loginBefore");
    }

    @Override
    public void initInputCommand() {
        this.setCmd(HallCmd.cmd, "Hall");

        ofCommand(loginVerify, "loginVerify").setRequestData(() -> {
            return ClientUserKit.loginName;
        }).callback(result -> {
            UserMessage message = result.getValue(UserMessage.class);
            log.info("loginSuccess: {}", message);
            this.setUserId(message.id);
        });

        ofCommand(hello, "hello").callback(result -> {
            log.info("{}", result.getString());
        });
    }
}
