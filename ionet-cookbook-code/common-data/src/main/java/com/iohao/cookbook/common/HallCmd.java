package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

public interface HallCmd {
    int cmd = CmdModule.hallCmd;

    int loginVerify = 1;
    int internalOffline = 2;
    int hello = 3;

    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }
}
