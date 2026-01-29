package com.iohao.cookbook.region;

import com.iohao.cookbook.common.FlowContextBroadcastCmd;
import com.iohao.cookbook.common.message.BookMessage;
import com.iohao.net.extension.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.iohao.cookbook.common.FlowContextBroadcastCmd.*;

@Slf4j
public class FlowContextBroadcastRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        this.setCmd(FlowContextBroadcastCmd.cmd, "FlowContextBroadcast");

        ofCommand(triggerBroadcastMe, "triggerBroadcastMe");

        ofCommand(triggerBroadcastUser, "triggerBroadcastUser").setRequestData(this::getUserId);

        ofCommand(triggerBroadcastUsers, "triggerBroadcastUsers").setRequestData(() -> {
            return List.of( this.getUserId(), 2L);
        });

        ofCommand(triggerBroadcastMulticast, "triggerBroadcastMulticast");

        // ---------- broadcastMe ----------
        extractedBroadcastMe();

        // ---------- broadcastUser ----------
        extractedBroadcastUser();

        // ---------- broadcastUsers ----------
        extractedBroadcastUsers();

        // ---------- BroadcastMulticast ----------
        extractedBroadcastMulticast();
    }

    private void extractedBroadcastMulticast() {
        ofListen(result -> {
            log.info("broadcastMulticastEmpty");
        }, broadcastMulticastEmpty, "broadcastMulticastEmpty");

        ofListen(result -> {
            log.info("{}", result.getInt());
        }, broadcastMulticastInt, "broadcastMulticastInt");

        ofListen(result -> {
            log.info("{}", result.getBoolean());
        }, broadcastMulticastBool, "broadcastMulticastBool");

        ofListen(result -> {
            log.info("{}", result.getLong());
        }, broadcastMulticastLong, "broadcastMulticastLong");

        ofListen(result -> {
            log.info("{}", result.getString());
        }, broadcastMulticastString, "broadcastMulticastString");

        ofListen(result -> {
            log.info("{}", result.getValue(BookMessage.class));
        }, broadcastMulticastObject, "broadcastMulticastObject");

        // ---------- broadcastMulticast list ----------
        ofListen(result -> {
            log.info("{}", result.listInt());
        }, broadcastMulticastIntList, "broadcastMulticastIntList");

        ofListen(result -> {
            log.info("{}", result.listBoolean());
        }, broadcastMulticastBoolList, "broadcastMulticastBoolList");

        ofListen(result -> {
            log.info("{}", result.listLong());
        }, broadcastMulticastLongList, "broadcastMulticastLongList");

        ofListen(result -> {
            log.info("{}", result.listString());
        }, broadcastMulticastStringList, "broadcastMulticastStringList");

        ofListen(result -> {
            log.info("{}", result.listValue(BookMessage.class));
        }, broadcastMulticastObjectList, "broadcastMulticastObjectList");
    }

    private void extractedBroadcastUsers() {
        ofListen(result -> {
            log.info("broadcastUsersEmpty");
        }, broadcastUsersEmpty, "broadcastUsersEmpty");

        ofListen(result -> {
            log.info("{}", result.getInt());
        }, broadcastUsersInt, "broadcastUsersInt");

        ofListen(result -> {
            log.info("{}", result.getBoolean());
        }, broadcastUsersBool, "broadcastUsersBool");

        ofListen(result -> {
            log.info("{}", result.getLong());
        }, broadcastUsersLong, "broadcastUsersLong");

        ofListen(result -> {
            log.info("{}", result.getString());
        }, broadcastUsersString, "broadcastUsersString");

        ofListen(result -> {
            log.info("{}", result.getValue(BookMessage.class));
        }, broadcastUsersObject, "broadcastUsersObject");

        // ---------- broadcastUsers list ----------
        ofListen(result -> {
            log.info("{}", result.listInt());
        }, broadcastUsersIntList, "broadcastUsersIntList");

        ofListen(result -> {
            log.info("{}", result.listBoolean());
        }, broadcastUsersBoolList, "broadcastUsersBoolList");

        ofListen(result -> {
            log.info("{}", result.listLong());
        }, broadcastUsersLongList, "broadcastUsersLongList");

        ofListen(result -> {
            log.info("{}", result.listString());
        }, broadcastUsersStringList, "broadcastUsersStringList");

        ofListen(result -> {
            log.info("{}", result.listValue(BookMessage.class));
        }, broadcastUsersObjectList, "broadcastUsersObjectList");
    }

    private void extractedBroadcastUser() {
        ofListen(result -> {
            log.info("broadcastUserEmpty");
        }, broadcastUserEmpty, "broadcastUserEmpty");

        ofListen(result -> {
            log.info("{}", result.getInt());
        }, broadcastUserInt, "broadcastUserInt");

        ofListen(result -> {
            log.info("{}", result.getBoolean());
        }, broadcastUserBool, "broadcastUserBool");

        ofListen(result -> {
            log.info("{}", result.getLong());
        }, broadcastUserLong, "broadcastUserLong");

        ofListen(result -> {
            log.info("{}", result.getString());
        }, broadcastUserString, "broadcastUserString");

        ofListen(result -> {
            log.info("{}", result.getValue(BookMessage.class));
        }, broadcastUserObject, "broadcastUserObject");

        // ---------- broadcastUser list ----------
        ofListen(result -> {
            log.info("{}", result.listInt());
        }, broadcastUserIntList, "broadcastUserIntList");

        ofListen(result -> {
            log.info("{}", result.listBoolean());
        }, broadcastUserBoolList, "broadcastUserBoolList");

        ofListen(result -> {
            log.info("{}", result.listLong());
        }, broadcastUserLongList, "broadcastUserLongList");

        ofListen(result -> {
            log.info("{}", result.listString());
        }, broadcastUserStringList, "broadcastUserStringList");

        ofListen(result -> {
            log.info("{}", result.listValue(BookMessage.class));
        }, broadcastUserObjectList, "broadcastUserObjectList");
    }

    private void extractedBroadcastMe() {
        ofListen(result -> {
            log.info("broadcastMeEmpty");
        }, broadcastMeEmpty, "broadcastMeEmpty");

        ofListen(result -> {
            log.info("{}", result.getInt());
        }, broadcastMeInt, "broadcastMeInt");

        ofListen(result -> {
            log.info("{}", result.getBoolean());
        }, broadcastMeBool, "broadcastMeBool");

        ofListen(result -> {
            log.info("{}", result.getLong());
        }, broadcastMeLong, "broadcastMeLong");

        ofListen(result -> {
            log.info("{}", result.getString());
        }, broadcastMeString, "broadcastMeString");

        ofListen(result -> {
            log.info("{}", result.getValue(BookMessage.class));
        }, broadcastMeObject, "broadcastMeObject");

        // ---------- broadcastMe list ----------
        ofListen(result -> {
            log.info("{}", result.listInt());
        }, broadcastMeIntList, "broadcastMeIntList");

        ofListen(result -> {
            log.info("{}", result.listBoolean());
        }, broadcastMeBoolList, "broadcastMeBoolList");

        ofListen(result -> {
            log.info("{}", result.listLong());
        }, broadcastMeLongList, "broadcastMeLongList");

        ofListen(result -> {
            log.info("{}", result.listString());
        }, broadcastMeStringList, "broadcastMeStringList");

        ofListen(result -> {
            log.info("{}", result.listValue(BookMessage.class));
        }, broadcastMeObjectList, "broadcastMeObjectList");
    }
}
