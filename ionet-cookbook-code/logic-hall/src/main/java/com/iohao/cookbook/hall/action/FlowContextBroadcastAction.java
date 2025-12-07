package com.iohao.cookbook.hall.action;

import com.iohao.cookbook.common.FlowContextBroadcastCmd;
import com.iohao.cookbook.common.message.BookMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.core.flow.FlowContext;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.iohao.cookbook.common.FlowContextBroadcastCmd.*;

@ActionController(FlowContextBroadcastCmd.cmd)
public class FlowContextBroadcastAction {
    AtomicInteger inc = new AtomicInteger();

    @ActionMethod(triggerBroadcastMe)
    private void triggerBroadcastMe(FlowContext flowContext) {
        // ---------- empty ----------
        flowContext.broadcastMe(broadcastMeEmpty);

        // ---------- int ----------
        int dataInt = inc.getAndIncrement();
        flowContext.broadcastMe(broadcastMeInt, dataInt);

        // ---------- boolean ----------
        boolean dataBool = inc.getAndIncrement() % 2 == 0;
        flowContext.broadcastMe(broadcastMeBool, dataBool);

        // ---------- long ----------
        long dataLong = inc.getAndIncrement();
        flowContext.broadcastMe(broadcastMeLong, dataLong);

        // ---------- string ----------
        String dataString = "ionet-" + inc.getAndIncrement();
        flowContext.broadcastMe(broadcastMeString, dataString);

        // ---------- object ----------
        BookMessage dataObject = new BookMessage();
        dataObject.authorName = "ionet";
        dataObject.bookName = "book-" + inc.getAndIncrement();
        flowContext.broadcastMe(broadcastMeObject, dataObject);

        // ---------- list int ----------
        List<Integer> dataListInt = List.of(inc.getAndIncrement(), inc.getAndIncrement());
        flowContext.broadcastMeListInt(broadcastMeIntList, dataListInt);

        // ---------- list boolean ----------
        List<Boolean> dataListBool = List.of(
                inc.getAndIncrement() % 2 == 0,
                inc.getAndIncrement() % 2 == 0
        );

        flowContext.broadcastMeListBool(broadcastMeBoolList, dataListBool);

        // ---------- list long ----------
        List<Long> dataListLong = List.of(
                (long) inc.getAndIncrement(),
                (long) inc.getAndIncrement()
        );

        flowContext.broadcastMeListLong(broadcastMeLongList, dataListLong);

        // ---------- list string ----------
        List<String> dataListString = List.of(
                "ionet-" + inc.getAndIncrement(),
                "ionet-" + inc.getAndIncrement()
        );

        flowContext.broadcastMeListString(broadcastMeStringList, dataListString);

        // ---------- list object ----------
        BookMessage message1 = new BookMessage();
        message1.authorName = "ionet";
        message1.bookName = "book-" + inc.getAndIncrement();

        BookMessage message2 = new BookMessage();
        message2.authorName = "ionet";
        message2.bookName = "book-" + inc.getAndIncrement();

        List<BookMessage> dataList = List.of(message1, message2);
        flowContext.broadcastMe(broadcastMeObjectList, dataList);
    }

    @ActionMethod(triggerBroadcastUser)
    private void triggerBroadcastUser(long userId, FlowContext flowContext) {
        // ---------- empty ----------
        flowContext.broadcastUser(userId, broadcastUserEmpty);
        // ---------- int ----------
        int dataInt = inc.getAndIncrement();
        flowContext.broadcastUser(userId, broadcastUserInt, dataInt);

        // ---------- boolean ----------
        boolean dataBool = inc.getAndIncrement() % 2 == 0;
        flowContext.broadcastUser(userId, broadcastUserBool, dataBool);

        // ---------- long ----------
        long dataLong = inc.getAndIncrement();
        flowContext.broadcastUser(userId, broadcastUserLong, dataLong);

        // ---------- string ----------
        String dataString = "ionet-" + inc.getAndIncrement();
        flowContext.broadcastUser(userId, broadcastUserString, dataString);

        // ---------- object ----------
        BookMessage dataObject = new BookMessage();
        dataObject.authorName = "ionet";
        dataObject.bookName = "book-" + inc.getAndIncrement();
        flowContext.broadcastUser(userId, broadcastUserObject, dataObject);

        // ---------- list int ----------
        List<Integer> dataListInt = List.of(inc.getAndIncrement(), inc.getAndIncrement());
        flowContext.broadcastUserListInt(userId, broadcastUserIntList, dataListInt);

        // ---------- list boolean ----------
        List<Boolean> dataListBool = List.of(
                inc.getAndIncrement() % 2 == 0,
                inc.getAndIncrement() % 2 == 0
        );

        flowContext.broadcastUserListBool(userId, broadcastUserBoolList, dataListBool);

        // ---------- list long ----------
        List<Long> dataListLong = List.of(
                (long) inc.getAndIncrement(),
                (long) inc.getAndIncrement()
        );

        flowContext.broadcastUserListLong(userId, broadcastUserLongList, dataListLong);

        // ---------- list string ----------
        List<String> dataListString = List.of(
                "ionet-" + inc.getAndIncrement(),
                "ionet-" + inc.getAndIncrement()
        );

        flowContext.broadcastUserListString(userId, broadcastUserStringList, dataListString);

        // ---------- list object ----------
        BookMessage message1 = new BookMessage();
        message1.authorName = "ionet";
        message1.bookName = "book-" + inc.getAndIncrement();

        BookMessage message2 = new BookMessage();
        message2.authorName = "ionet";
        message2.bookName = "book-" + inc.getAndIncrement();

        List<BookMessage> dataList = List.of(message1, message2);
        flowContext.broadcastUser(userId, broadcastUserObjectList, dataList);
    }

    @ActionMethod(triggerBroadcastUsers)
    private void triggerBroadcastUsers(List<Long> userIdList, FlowContext flowContext) {
        // ---------- empty ----------
        flowContext.broadcastUsers(userIdList, broadcastUsersEmpty);
        // ---------- int ----------
        int dataInt = inc.getAndIncrement();
        flowContext.broadcastUsers(userIdList, broadcastUsersInt, dataInt);

        // ---------- boolean ----------
        boolean dataBool = inc.getAndIncrement() % 2 == 0;
        flowContext.broadcastUsers(userIdList, broadcastUsersBool, dataBool);

        // ---------- long ----------
        long dataLong = inc.getAndIncrement();
        flowContext.broadcastUsers(userIdList, broadcastUsersLong, dataLong);

        // ---------- string ----------
        String dataString = "ionet-" + inc.getAndIncrement();
        flowContext.broadcastUsers(userIdList, broadcastUsersString, dataString);

        // ---------- object ----------
        BookMessage dataObject = new BookMessage();
        dataObject.authorName = "ionet";
        dataObject.bookName = "book-" + inc.getAndIncrement();
        flowContext.broadcastUsers(userIdList, broadcastUsersObject, dataObject);

        // ---------- list int ----------
        List<Integer> dataListInt = List.of(inc.getAndIncrement(), inc.getAndIncrement());
        flowContext.broadcastUsersListInt(userIdList, broadcastUsersIntList, dataListInt);

        // ---------- list boolean ----------
        List<Boolean> dataListBool = List.of(
                inc.getAndIncrement() % 2 == 0,
                inc.getAndIncrement() % 2 == 0
        );

        flowContext.broadcastUsersListBool(userIdList, broadcastUsersBoolList, dataListBool);

        // ---------- list long ----------

        List<Long> dataListLong = List.of(
                (long) inc.getAndIncrement(),
                (long) inc.getAndIncrement()
        );

        flowContext.broadcastUsersListLong(userIdList, broadcastUsersLongList, dataListLong);

        // ---------- list string ----------
        List<String> dataListString = List.of(
                "ionet-" + inc.getAndIncrement(),
                "ionet-" + inc.getAndIncrement()
        );

        flowContext.broadcastUsersListString(userIdList, broadcastUsersStringList, dataListString);

        // ---------- list object ----------
        BookMessage message1 = new BookMessage();
        message1.authorName = "ionet";
        message1.bookName = "book-" + inc.getAndIncrement();

        BookMessage message2 = new BookMessage();
        message2.authorName = "ionet";
        message2.bookName = "book-" + inc.getAndIncrement();

        List<BookMessage> dataList = List.of(message1, message2);
        flowContext.broadcastUsers(userIdList, broadcastUsersObjectList, dataList);
    }

    @ActionMethod(triggerBroadcastMulticast)
    private void triggerBroadcastMulticast(FlowContext flowContext) {
        // ---------- empty ----------
        flowContext.broadcastMulticast(broadcastMulticastEmpty);

        // ---------- int ----------
        int dataInt = inc.getAndIncrement();
        flowContext.broadcastMulticast(broadcastMulticastInt, dataInt);

        // ---------- boolean ----------
        boolean dataBool = inc.getAndIncrement() % 2 == 0;
        flowContext.broadcastMulticast(broadcastMulticastBool, dataBool);

        // ---------- long ----------
        long dataLong = inc.getAndIncrement();
        flowContext.broadcastMulticast(broadcastMulticastLong, dataLong);

        // ---------- string ----------
        String dataString = "ionet-" + inc.getAndIncrement();
        flowContext.broadcastMulticast(broadcastMulticastString, dataString);

        // ---------- object ----------
        BookMessage dataObject = new BookMessage();
        dataObject.authorName = "ionet";
        dataObject.bookName = "book-" + inc.getAndIncrement();
        flowContext.broadcastMulticast(broadcastMulticastObject, dataObject);

        // ---------- list int ----------
        List<Integer> dataListInt = List.of(inc.getAndIncrement(), inc.getAndIncrement());
        flowContext.broadcastMulticastListInt(broadcastMulticastIntList, dataListInt);

        // ---------- list boolean ----------
        List<Boolean> dataListBool = List.of(
                inc.getAndIncrement() % 2 == 0,
                inc.getAndIncrement() % 2 == 0
        );

        flowContext.broadcastMulticastListBool(broadcastMulticastBoolList, dataListBool);

        // ---------- list long ----------
        List<Long> dataListLong = List.of(
                (long) inc.getAndIncrement(),
                (long) inc.getAndIncrement()
        );

        flowContext.broadcastMulticastListLong(broadcastMulticastLongList, dataListLong);

        // ---------- list string ----------
        List<String> dataListString = List.of(
                "ionet-" + inc.getAndIncrement(),
                "ionet-" + inc.getAndIncrement()
        );

        flowContext.broadcastMulticastListString(broadcastMulticastStringList, dataListString);

        // ---------- list object ----------
        BookMessage message1 = new BookMessage();
        message1.authorName = "ionet";
        message1.bookName = "book-" + inc.getAndIncrement();

        BookMessage message2 = new BookMessage();
        message2.authorName = "ionet";
        message2.bookName = "book-" + inc.getAndIncrement();

        List<BookMessage> dataList = List.of(message1, message2);
        flowContext.broadcastMulticast(broadcastMulticastObjectList, dataList);
    }
}
