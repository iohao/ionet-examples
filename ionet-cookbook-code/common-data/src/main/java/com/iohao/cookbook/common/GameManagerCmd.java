package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

public interface GameManagerCmd {
    int cmd = CmdModule.gameManagerCmd;

    int recharge = 1;
    int notice = 2;
    int test = 3;

    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }
}
