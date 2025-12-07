package com.iohao.cookbook;

import com.iohao.cookbook.region.*;
import com.iohao.net.extension.client.InputCommandRegion;
import com.iohao.net.extension.client.join.ClientRunOne;
import com.iohao.net.extension.client.kit.ClientUserConfigs;
import com.iohao.net.external.core.config.ExternalJoinEnum;

import java.util.List;
import java.util.Locale;

class OneClient {
    static void main() {
        // US or CHINA
//        Locale.setDefault(Locale.US);
        Locale.setDefault(Locale.CHINA);
        // closeLog. cn: 关闭模拟请求相关日志
        ClientUserConfigs.closeLog();

        new ClientRunOne()
                .setInputCommandRegions(listInputCommandRegion())
                .startup();
    }

    static List<InputCommandRegion> listInputCommandRegion() {
        return List.of(
                new HallRegion()
                , new RequestRegion()
//                , new AttachmentRegion()
//                , new RoomRegion()
//                , new Jsr380Region()
//                , new FlowContextCallRegion()
//                , new BroadcastRegion()
//                , new FlowContextBroadcastRegion()
//                , new FlowContextSendRegion()
//                , new CallRegion()
//                , new SendRegion()
        );
    }
}
