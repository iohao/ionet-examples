package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

public interface OnExternalCmd {
    int cmd = CmdModule.onExternalCmd;

    int userIp = 1;
    int userOnlineCount = 2;

    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }
}
