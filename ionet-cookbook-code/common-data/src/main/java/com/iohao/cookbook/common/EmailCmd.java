package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

public interface EmailCmd {
    int cmd = CmdModule.emailCmd;

    int getEmail = 1;

    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }
}
