package com.iohao.cookbook.hall;

import com.iohao.cookbook.common.extension.MyLogicServerKit;
import com.iohao.cookbook.hall.action.HallAction;
import com.iohao.net.framework.core.BarSkeletonBuilder;
import com.iohao.net.framework.protocol.ServerBuilder;
import com.iohao.net.server.LogicServer;

public class HallLogicServer implements LogicServer {
    @Override
    public void settingBarSkeletonBuilder(BarSkeletonBuilder builder) {
        MyLogicServerKit.defaultSetting(builder, HallAction.class);
    }

    @Override
    public void settingServerBuilder(ServerBuilder builder) {
        builder.setName("HallLogicServer");
    }
}
