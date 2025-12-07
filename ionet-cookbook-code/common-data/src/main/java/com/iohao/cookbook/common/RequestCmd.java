package com.iohao.cookbook.common;

public interface RequestCmd {
    int cmd = CmdModule.requestCmd;
    // ---------- request ----------
    int emptyAction = 0;
    int intAction = 1;
    int boolAction = 2;
    int longAction = 3;
    int stringAction = 4;
    int objectAction = 5;

    // ---------- request list ----------
    int intListAction = 6;
    int boolListAction = 7;
    int longListAction = 8;
    int stringListAction = 9;
    int objectListAction = 10;

}
