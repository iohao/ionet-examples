package com.iohao.cookbook.common;

import com.iohao.net.framework.core.CmdInfo;

import java.util.concurrent.atomic.AtomicInteger;

public interface FlowContextBroadcastCmd {
    int cmd = CmdModule.flowContextBroadcastCmd;

    // ---------- broadcastMe ----------
    int triggerBroadcastMe = 1;
    int triggerBroadcastUser = 2;
    int triggerBroadcastUsers = 3;
    int triggerBroadcastMulticast = 4;

    AtomicInteger broadcastIndex = new AtomicInteger(10);

    private static CmdInfo ofBroadcastCmd() {
        return CmdInfo.of(cmd, broadcastIndex.getAndIncrement());
    }

    // ---------- broadcastMe ----------
    CmdInfo broadcastMeEmpty = ofBroadcastCmd();
    CmdInfo broadcastMeInt = ofBroadcastCmd();
    CmdInfo broadcastMeBool = ofBroadcastCmd();
    CmdInfo broadcastMeLong = ofBroadcastCmd();
    CmdInfo broadcastMeString = ofBroadcastCmd();
    CmdInfo broadcastMeObject = ofBroadcastCmd();
    // ---------- broadcastMe List ----------
    CmdInfo broadcastMeIntList = ofBroadcastCmd();
    CmdInfo broadcastMeBoolList = ofBroadcastCmd();
    CmdInfo broadcastMeLongList = ofBroadcastCmd();
    CmdInfo broadcastMeStringList = ofBroadcastCmd();
    CmdInfo broadcastMeObjectList = ofBroadcastCmd();

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
