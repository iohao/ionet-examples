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

    private static CmdInfo ofBroadcast() {
        return CmdInfo.of(cmd, inc.getAndIncrement());
    }

    // ---------- broadcastUser ----------
    CmdInfo broadcastUserEmpty = ofBroadcast();
    CmdInfo broadcastUserInt = ofBroadcast();
    CmdInfo broadcastUserBool = ofBroadcast();
    CmdInfo broadcastUserLong = ofBroadcast();
    CmdInfo broadcastUserString = ofBroadcast();
    CmdInfo broadcastUserObject = ofBroadcast();
    // ---------- broadcastUser List ----------
    CmdInfo broadcastUserIntList = ofBroadcast();
    CmdInfo broadcastUserBoolList = ofBroadcast();
    CmdInfo broadcastUserLongList = ofBroadcast();
    CmdInfo broadcastUserStringList = ofBroadcast();
    CmdInfo broadcastUserObjectList = ofBroadcast();

    // ---------- broadcastUsers ----------
    CmdInfo broadcastUsersEmpty = ofBroadcast();
    CmdInfo broadcastUsersInt = ofBroadcast();
    CmdInfo broadcastUsersBool = ofBroadcast();
    CmdInfo broadcastUsersLong = ofBroadcast();
    CmdInfo broadcastUsersString = ofBroadcast();
    CmdInfo broadcastUsersObject = ofBroadcast();
    // ---------- broadcastUsers List ----------
    CmdInfo broadcastUsersIntList = ofBroadcast();
    CmdInfo broadcastUsersBoolList = ofBroadcast();
    CmdInfo broadcastUsersLongList = ofBroadcast();
    CmdInfo broadcastUsersStringList = ofBroadcast();
    CmdInfo broadcastUsersObjectList = ofBroadcast();

    // ---------- broadcastMulticast ----------
    CmdInfo broadcastMulticastEmpty = ofBroadcast();
    CmdInfo broadcastMulticastInt = ofBroadcast();
    CmdInfo broadcastMulticastBool = ofBroadcast();
    CmdInfo broadcastMulticastLong = ofBroadcast();
    CmdInfo broadcastMulticastString = ofBroadcast();
    CmdInfo broadcastMulticastObject = ofBroadcast();
    // ---------- broadcastMulticast List ----------
    CmdInfo broadcastMulticastIntList = ofBroadcast();
    CmdInfo broadcastMulticastBoolList = ofBroadcast();
    CmdInfo broadcastMulticastLongList = ofBroadcast();
    CmdInfo broadcastMulticastStringList = ofBroadcast();
    CmdInfo broadcastMulticastObjectList = ofBroadcast();


}
