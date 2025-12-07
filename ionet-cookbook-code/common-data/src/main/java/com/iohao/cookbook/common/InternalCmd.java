package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

public interface InternalCmd {
    int cmd = CmdModule.internalCmd;

    int emptyAction = 0;
    int intAction = 1;
    int boolAction = 2;
    int longAction = 3;
    int stringAction = 4;
    int objectAction = 5;

    int intListAction = 6;
    int boolListAction = 7;
    int longListAction = 8;
    int stringListAction = 9;
    int objectListAction = 10;

    // -------- void --------
    int sendEmptyAction = 20;
    int sendIntAction = 21;
    int sendBoolAction = 22;
    int sendLongAction = 23;
    int sendStringAction = 24;
    int sendObjectAction = 25;

    int sendIntListAction = 26;
    int sendBoolListAction = 27;
    int sendLongListAction = 28;
    int sendStringListAction = 29;
    int sendObjectListAction = 30;


    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }

}
