package com.iohao.cookbook.common;

public interface CallCmd {
    int cmd = CmdModule.callCmd;
    // ---------- call ----------
    int callEmpty = 0;
    int callInt = 1;
    int callBool = 2;
    int callLong = 3;
    int callString = 4;
    int callObject = 5;

    // ---------- call list ----------
    int callIntList = 6;
    int callBoolList = 7;
    int callLongList = 8;
    int callStringList = 9;
    int callObjectList = 10;
}
