package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

public interface WsVerifyCmd {
    int cmd = CmdModule.wsVerifyCmd;

    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }
}
