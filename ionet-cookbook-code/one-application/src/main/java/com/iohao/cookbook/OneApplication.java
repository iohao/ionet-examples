package com.iohao.cookbook;

import com.iohao.cookbook.common.AeronLifecycleManager;
import com.iohao.cookbook.common.extension.MyFlowContext;
import com.iohao.cookbook.common.extension.UserKit;
import com.iohao.cookbook.external.MyExternalServer;
import com.iohao.cookbook.hall.HallLogicServer;
import com.iohao.cookbook.internal.InternalLogicServer;
import com.iohao.cookbook.room.RoomLogicServer;
import com.iohao.net.framework.CoreGlobalConfig;
import com.iohao.net.app.RunOne;
import com.iohao.net.external.core.config.ExternalGlobalConfig;
import com.iohao.net.external.core.config.ExternalJoinEnum;
import com.iohao.net.server.LogicServer;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;

@Slf4j
@SuppressWarnings("all")
class OneApplication {
    static void main() {
        CoreGlobalConfig.setting.flowContextFactory = MyFlowContext::new;

        CoreGlobalConfig.setting.validator = true;
        CoreGlobalConfig.setting.parseDoc = true;
        CoreGlobalConfig.setting.print = true;

//        DataCodecManager.setDataCodec(new JsonDataCodec());

        // US or CHINA
        Locale.setDefault(Locale.US);
//        Locale.setDefault(Locale.CHINA);

        var aeron = new AeronLifecycleManager().getAeron();

        var externalServer = MyExternalServer.builder(
                ExternalGlobalConfig.externalPort
                , ExternalJoinEnum.WEBSOCKET
        ).build();

        new RunOne()
                .setAeron(aeron)
                .enableCenterServer()
                .setExternalServer(externalServer)
                .setLogicServerList(listLogic())
                .startup();
    }

    static List<LogicServer> listLogic() {
        return List.of(
                new HallLogicServer()
                , new RoomLogicServer()
                , new InternalLogicServer(5_000_000)
        );
    }

    static {
        UserKit.ofUserMessage(1378604058);
    }
}