package com.iohao.cookbook.region;

import com.iohao.cookbook.common.CallCmd;
import com.iohao.cookbook.common.message.BookMessage;
import com.iohao.net.extension.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

import static com.iohao.cookbook.common.CallCmd.*;

@Slf4j
public class CallRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        this.setCmd(CallCmd.cmd, "CallCmd");

        ofCommand(callEmpty, "callEmpty").callback(result -> {
            log.info("{}", result.getInt());
        });

        ofCommand(callInt, "callInt").callback(result -> {
            log.info("{}", result.getInt());
        });

        ofCommand(callBool, "callBool").callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(callLong, "callLong").callback(result -> {
            log.info("{}", result.getLong());
        });

        ofCommand(callString, "callString").callback(result -> {
            log.info("{}", result.getString());
        });

        ofCommand(callObject, "callObject").callback(result -> {
            log.info("{}", result.getValue(BookMessage.class));
        });

        // --------------- list ---------------
        ofCommand(callIntList, "callIntList").callback(result -> {
            log.info("{}", result.listInt());
        });

        ofCommand(callBoolList, "callBoolList").callback(result -> {
            log.info("{}", result.listBoolean());
        });

        ofCommand(callLongList, "callLongList").callback(result -> {
            log.info("{}", result.listLong());
        });

        ofCommand(callStringList, "callStringList").callback(result -> {
            log.info("{}", result.listString());
        });

        ofCommand(callObjectList, "callObjectList").callback(result -> {
            log.info("{}", result.listValue(BookMessage.class));
        });
    }

}
