package com.iohao.cookbook.common;

import com.iohao.net.framework.annotations.Enterprise;

public interface CmdModule {
    int internalCmd = 30;
    int hallCmd = 31;

    int requestCmd = 1;

    int flowContextCallCmd = 2;
    int callCmd = 3;

    int flowContextSendCmd = 4;
    int sendCmd = 5;

    int flowContextBroadcastCmd = 6;
    int broadcastCmd = 7;

    int jsr380Cmd = 8;
    int attachmentCmd = 9;
    int roomCmd = 10;

    int emailCmd = 11;
    int wsVerifyCmd = 12;

    int onExternalCmd = 13;
    int gameManagerCmd = 14;
    int pressureCmd = 15;

    @Enterprise
    int eventBusCmd = 20;
    @Enterprise
    int cacheCmd = 21;
    @Enterprise
    int bindingCmd = 22;
    @Enterprise
    int callCollectCmd = 23;
    @Enterprise
    int flowContextCallCollectCmd = 24;
    @Enterprise
    int speedCmd = 25;
}
