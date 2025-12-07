package com.iohao.cookbook.common.extension;

import com.iohao.net.framework.core.exception.ActionErrorEnum;
import com.iohao.net.external.core.net.external.OnExternal;
import com.iohao.net.external.core.net.external.OnExternalContext;
import com.iohao.net.external.core.session.UserSession;

public class UserIpOnExternal implements OnExternal {
    @Override
    public void process(byte[] payload, int payloadLength, OnExternalContext context) {
        UserSession userSession = context.getUserSession();
        ActionErrorEnum.dataNotExist.assertNonNull(userSession);
        // get userIp
        String ip = userSession.getIp();
        context.response().setPayload(ip);
    }

    @Override
    public int getTemplateId() {
        return MyOnExternalTemplateId.userIp;
    }
}
