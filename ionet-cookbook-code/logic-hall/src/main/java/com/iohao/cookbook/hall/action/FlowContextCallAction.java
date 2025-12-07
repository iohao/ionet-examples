package com.iohao.cookbook.hall.action;

import com.iohao.cookbook.common.InternalCmd;
import com.iohao.cookbook.common.FlowContextCallCmd;
import com.iohao.cookbook.common.message.AuthorMessage;
import com.iohao.cookbook.common.message.BookMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.flow.FlowContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * via FlowContext
 *
 * @author 渔民小镇
 * @date 2025-10-05
 * @since 25.1
 */
@Slf4j
@ActionController(FlowContextCallCmd.cmd)
public class FlowContextCallAction {
    @ActionMethod(FlowContextCallCmd.callEmpty)
    private int callEmpty(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.emptyAction);

        // Asynchronous callback
        flowContext.callback(cmdInfo, response -> {
            log.info("{}", response.getInt());
        });

        // Synchronous call
        var response = flowContext.call(cmdInfo);
        return response.getInt();
    }

    @ActionMethod(FlowContextCallCmd.callInt)
    private int callInt(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.intAction);
        int data = 1;

        // Asynchronous callback
        flowContext.callback(cmdInfo, data, response -> {
            log.info("{}", response.getInt());
        });

        // Synchronous call
        var response = flowContext.call(cmdInfo, data);
        return response.getInt();
    }

    @ActionMethod(FlowContextCallCmd.callBool)
    private boolean callBool(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.boolAction);
        boolean data = true;

        // Asynchronous callback
        flowContext.callback(cmdInfo, data, response -> {
            log.info("{}", response.getBoolean());
        });

        // Synchronous call
        var response = flowContext.call(cmdInfo, data);
        return response.getBoolean();
    }

    @ActionMethod(FlowContextCallCmd.callLong)
    private long callLong(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.longAction);
        long data = 1L;

        // Asynchronous callback
        flowContext.callback(cmdInfo, data, response -> {
            log.info("{}", response.getLong());
        });

        // Synchronous call
        var response = flowContext.call(cmdInfo, data);
        return response.getLong();
    }

    @ActionMethod(FlowContextCallCmd.callString)
    private String callString(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.stringAction);
        String data = "hello";

        // Asynchronous callback
        flowContext.callback(cmdInfo, data, response -> {
            log.info("{}", response.getString());
        });

        // Synchronous call
        var response = flowContext.call(cmdInfo, data);
        return response.getString();
    }

    @ActionMethod(FlowContextCallCmd.callObject)
    private BookMessage callObject(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.objectAction);
        var data = new AuthorMessage();

        // Asynchronous callback
        flowContext.callback(cmdInfo, data, response -> {
            log.info("{}", response.getValue(BookMessage.class));
        });

        // Synchronous call
        var response = flowContext.call(cmdInfo, data);
        return response.getValue(BookMessage.class);
    }

    @ActionMethod(FlowContextCallCmd.callIntList)
    private List<Integer> callIntList(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.intListAction);
        List<Integer> dataList = List.of(1, 2);

        // Asynchronous callback
        flowContext.callbackListInt(cmdInfo, dataList, response -> {
            log.info("{}", response.listInt());
        });

        // Synchronous call
        var response = flowContext.callListInt(cmdInfo, dataList);
        return response.listInt();
    }

    @ActionMethod(FlowContextCallCmd.callBoolList)
    private List<Boolean> callBoolList(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.boolListAction);
        List<Boolean> dataList = List.of(true, false);

        // Asynchronous callback
        flowContext.callbackListBool(cmdInfo, dataList, response -> {
            log.info("{}", response.listBoolean());
        });

        // Synchronous call
        var response = flowContext.callListBool(cmdInfo, dataList);
        return response.listBoolean();
    }

    @ActionMethod(FlowContextCallCmd.callLongList)
    private List<Long> callLongList(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.longListAction);
        List<Long> dataList = List.of(1L, 2L);

        // Asynchronous callback
        flowContext.callbackListLong(cmdInfo, dataList, response -> {
            log.info("{}", response.listLong());
        });

        // Synchronous call
        var response = flowContext.callListLong(cmdInfo, dataList);
        return response.listLong();
    }

    @ActionMethod(FlowContextCallCmd.callStringList)
    private List<String> callStringList(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.stringListAction);
        List<String> dataList = List.of("hello", "ionet");

        // Asynchronous callback
        flowContext.callbackListString(cmdInfo, dataList, response -> {
            log.info("{}", response.listString());
        });

        // Synchronous call
        var response = flowContext.callListString(cmdInfo, dataList);
        return response.listString();
    }

    @ActionMethod(FlowContextCallCmd.callObjectList)
    private List<BookMessage> callObjectList(FlowContext flowContext) {
        // The two below are equivalent, one is synchronous and the other is asynchronous.
        var cmdInfo = InternalCmd.of(InternalCmd.objectListAction);

        var author1 = new AuthorMessage();
        author1.authorName = "David Myers";

        var author2 = new AuthorMessage();
        author2.authorName = "Gustave Le Bon";

        List<AuthorMessage> dataList = List.of(author1, author2);

        // Asynchronous callback
        flowContext.callback(cmdInfo, dataList, response -> {
            log.info("{}", response.listValue(BookMessage.class));
        });

        // Synchronous call
        var response = flowContext.call(cmdInfo, dataList);
        return response.listValue(BookMessage.class);
    }
}
