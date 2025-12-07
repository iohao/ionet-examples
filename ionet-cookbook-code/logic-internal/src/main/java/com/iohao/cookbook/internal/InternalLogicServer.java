package com.iohao.cookbook.internal;

import com.iohao.cookbook.common.extension.MyLogicServerKit;
import com.iohao.net.framework.core.BarSkeletonBuilder;
import com.iohao.net.framework.protocol.ServerBuilder;
import com.iohao.net.server.LogicServer;

public record InternalLogicServer(int serverId) implements LogicServer {

    @Override
    public void settingBarSkeletonBuilder(BarSkeletonBuilder builder) {
        MyLogicServerKit.defaultSetting(builder, InternalAction.class);
    }

    @Override
    public void settingServerBuilder(ServerBuilder builder) {
        builder.setName("InternalLogicServer").setId(serverId);
    }
}
