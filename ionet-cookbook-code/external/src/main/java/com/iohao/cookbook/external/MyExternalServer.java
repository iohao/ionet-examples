package com.iohao.cookbook.external;

import com.iohao.cookbook.common.CmdModule;
import com.iohao.cookbook.common.HallCmd;
import com.iohao.cookbook.common.InternalCmd;
import com.iohao.cookbook.common.PressureCmd;
import com.iohao.cookbook.common.extension.MyIdleHook;
import com.iohao.cookbook.common.extension.MyUserHook;
import com.iohao.cookbook.common.extension.UserIpOnExternal;
import com.iohao.cookbook.common.extension.UserOnlineCountOnExternal;
import com.iohao.net.common.kit.PresentKit;
import com.iohao.net.external.core.ExternalServerBuilder;
import com.iohao.net.external.core.config.ExternalGlobalConfig;
import com.iohao.net.external.core.config.ExternalJoinEnum;
import com.iohao.net.external.core.hook.internal.IdleProcessSettingBuilder;
import com.iohao.net.external.core.net.external.OnExternalManager;
import com.iohao.net.external.core.netty.ExternalMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MyExternalServer {

    public ExternalServerBuilder builder(int port, ExternalJoinEnum joinEnum) {
        // External server configuration
        extractedConfig();

        var builder = ExternalMapper.builder(port, joinEnum);

        // https://iohao.github.io/ionet/docs/manual/user_hook
        // UserHook interface, triggered when a user goes online or offline
        builder.setUserHook(new MyUserHook());

        // https://iohao.github.io/ionet/docs/manual/idle
        var idleProcessSettingBuilder = new IdleProcessSettingBuilder()
                .setIdleHook(new MyIdleHook())
                .setIdleTime(30);

//        builder.setIdleProcessSettingBuilder(idleProcessSettingBuilder);

        return builder;
    }

    private void extractedConfig() {
        // extension OnExternal
        OnExternalManager.register(new UserIpOnExternal());
        OnExternalManager.register(new UserOnlineCountOnExternal());
        // Route access control
        extractedAccess();

        PresentKit.ifPresent(ExternalGlobalConfig.externalCmdCache, externalCmdCache -> {
            // cache config
            externalCmdCache.addCmd(CmdModule.cacheCmd);
        });
    }

    private void extractedAccess() {
        var accessAuthenticationHook = ExternalGlobalConfig.accessAuthenticationHook;
        // Indicates that business methods can only be accessed after logging in
        accessAuthenticationHook.setVerifyIdentity(true);
        // Add - business methods (actions) that can be accessed without login (authentication)
        accessAuthenticationHook.addIgnoreAuthCmd(HallCmd.cmd, HallCmd.loginVerify);
        accessAuthenticationHook.addIgnoreAuthCmd(PressureCmd.cmd, PressureCmd.login);
        // Add - control for rejecting player access
        accessAuthenticationHook.addRejectionCmd(HallCmd.cmd, HallCmd.internalOffline);
        accessAuthenticationHook.addRejectionCmd(InternalCmd.cmd);
    }
}
