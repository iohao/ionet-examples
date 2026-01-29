package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

public interface PressureCmd {
    int cmd = CmdModule.pressureCmd;

    int login = 1;
    int inc = 2;

    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }
}
