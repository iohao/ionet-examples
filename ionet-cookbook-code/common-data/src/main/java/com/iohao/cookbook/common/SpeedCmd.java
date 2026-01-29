package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

public interface SpeedCmd {
    int cmd = CmdModule.speedCmd;

    int requestVoid = 1;
    int requestVoidBefore = 2;

    int requestBool = 21;
    int requestBoolBefore = 22;

    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }
}
