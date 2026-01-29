package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

public interface SendCmd {
    int cmd = CmdModule.sendCmd;
    // ---------- send ----------
    int sendEmpty = 0;
    int sendInt = 1;
    int sendBool = 2;
    int sendLong = 3;
    int sendString = 4;
    int sendObject = 5;

    // ---------- send list ----------
    int sendIntList = 6;
    int sendBoolList = 7;
    int sendLongList = 8;
    int sendStringList = 9;
    int sendObjectList = 10;


    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }
}
