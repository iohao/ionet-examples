package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

import java.util.concurrent.atomic.AtomicInteger;

public interface BroadcastCmd {
    int cmd = CmdModule.broadcastCmd;
    // ---------- broadcastUser ----------
    int triggerBroadcastUser = 1;
    // ---------- broadcastUsers ----------
    int triggerBroadcastUsers = 2;
    // ---------- broadcastMulticast ----------
    int triggerBroadcastMulticast = 3;

    static CmdInfo of(int subCmd) {
        return CmdInfo.of(cmd, subCmd);
    }

    AtomicInteger inc = new AtomicInteger(10);

    private static CmdInfo ofBroadcastCmd() {
        return CmdInfo.of(cmd, inc.getAndIncrement());
    }

    // ---------- broadcastUser ----------
    CmdInfo broadcastUserEmpty = ofBroadcastCmd();
    CmdInfo broadcastUserInt = ofBroadcastCmd();
    CmdInfo broadcastUserBool = ofBroadcastCmd();
    CmdInfo broadcastUserLong = ofBroadcastCmd();
    CmdInfo broadcastUserString = ofBroadcastCmd();
    CmdInfo broadcastUserObject = ofBroadcastCmd();
    // ---------- broadcastUser List ----------
    CmdInfo broadcastUserIntList = ofBroadcastCmd();
    CmdInfo broadcastUserBoolList = ofBroadcastCmd();
    CmdInfo broadcastUserLongList = ofBroadcastCmd();
    CmdInfo broadcastUserStringList = ofBroadcastCmd();
    CmdInfo broadcastUserObjectList = ofBroadcastCmd();

    // ---------- broadcastUsers ----------
    CmdInfo broadcastUsersEmpty = ofBroadcastCmd();
    CmdInfo broadcastUsersInt = ofBroadcastCmd();
    CmdInfo broadcastUsersBool = ofBroadcastCmd();
    CmdInfo broadcastUsersLong = ofBroadcastCmd();
    CmdInfo broadcastUsersString = ofBroadcastCmd();
    CmdInfo broadcastUsersObject = ofBroadcastCmd();
    // ---------- broadcastUsers List ----------
    CmdInfo broadcastUsersIntList = ofBroadcastCmd();
    CmdInfo broadcastUsersBoolList = ofBroadcastCmd();
    CmdInfo broadcastUsersLongList = ofBroadcastCmd();
    CmdInfo broadcastUsersStringList = ofBroadcastCmd();
    CmdInfo broadcastUsersObjectList = ofBroadcastCmd();

    // ---------- broadcastMulticast ----------
    CmdInfo broadcastMulticastEmpty = ofBroadcastCmd();
    CmdInfo broadcastMulticastInt = ofBroadcastCmd();
    CmdInfo broadcastMulticastBool = ofBroadcastCmd();
    CmdInfo broadcastMulticastLong = ofBroadcastCmd();
    CmdInfo broadcastMulticastString = ofBroadcastCmd();
    CmdInfo broadcastMulticastObject = ofBroadcastCmd();
    // ---------- broadcastMulticast List ----------
    CmdInfo broadcastMulticastIntList = ofBroadcastCmd();
    CmdInfo broadcastMulticastBoolList = ofBroadcastCmd();
    CmdInfo broadcastMulticastLongList = ofBroadcastCmd();
    CmdInfo broadcastMulticastStringList = ofBroadcastCmd();
    CmdInfo broadcastMulticastObjectList = ofBroadcastCmd();


}
