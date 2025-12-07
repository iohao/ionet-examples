package com.iohao.cookbook.hall.action;

import com.iohao.cookbook.common.BroadcastCmd;
import com.iohao.cookbook.common.message.BookMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import com.iohao.net.framework.communication.CommunicationKit;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.iohao.cookbook.common.BroadcastCmd.*;

@ActionController(BroadcastCmd.cmd)
public class BroadcastAction {
    AtomicInteger inc = new AtomicInteger();

    @ActionMethod(triggerBroadcastUser)
    private void triggerBroadcastUser(long userId) {
        var communication = CommunicationKit.getCommunication();

        // ---------- empty ----------
        communication.broadcastUser(userId, broadcastUserEmpty);

        // ---------- int ----------
        int dataInt = inc.getAndIncrement();
        communication.broadcastUser(userId, broadcastUserInt, dataInt);

        // ---------- boolean ----------
        boolean dataBool = inc.getAndIncrement() % 2 == 0;
        communication.broadcastUser(userId, broadcastUserBool, dataBool);

        // ---------- long ----------
        long dataLong = inc.getAndIncrement();
        communication.broadcastUser(userId, broadcastUserLong, dataLong);

        // ---------- string ----------
        String dataString = "ionet-" + inc.getAndIncrement();
        communication.broadcastUser(userId, broadcastUserString, dataString);

        // ---------- object ----------
        BookMessage dataObject = new BookMessage();
        dataObject.authorName = "ionet";
        dataObject.bookName = "book-" + inc.getAndIncrement();
        communication.broadcastUser(userId, broadcastUserObject, dataObject);

        // ---------- list int ----------
        List<Integer> dataListInt = List.of(inc.getAndIncrement(), inc.getAndIncrement());
        communication.broadcastUserListInt(userId, broadcastUserIntList, dataListInt);

        // ---------- list boolean ----------
        List<Boolean> dataListBool = List.of(
                inc.getAndIncrement() % 2 == 0,
                inc.getAndIncrement() % 2 == 0
        );

        communication.broadcastUserListBool(userId, broadcastUserBoolList, dataListBool);

        // ---------- list long ----------
        List<Long> dataListLong = List.of(
                (long) inc.getAndIncrement(),
                (long) inc.getAndIncrement()
        );

        communication.broadcastUserListLong(userId, broadcastUserLongList, dataListLong);

        // ---------- list string ----------
        List<String> dataListString = List.of(
                "ionet-" + inc.getAndIncrement(),
                "ionet-" + inc.getAndIncrement()
        );

        communication.broadcastUserListString(userId, broadcastUserStringList, dataListString);

        // ---------- list object ----------
        BookMessage message1 = new BookMessage();
        message1.authorName = "ionet";
        message1.bookName = "book-" + inc.getAndIncrement();

        BookMessage message2 = new BookMessage();
        message2.authorName = "ionet";
        message2.bookName = "book-" + inc.getAndIncrement();

        List<BookMessage> dataList = List.of(message1, message2);
        communication.broadcastUser(userId, broadcastUserObjectList, dataList);
    }

    @ActionMethod(triggerBroadcastUsers)
    private void triggerBroadcastUsers(List<Long> userIdList) {
        var communication = CommunicationKit.getCommunication();
        // ---------- empty ----------
        communication.broadcastUsers(userIdList, broadcastUsersEmpty);
        // ---------- int ----------
        int dataInt = inc.getAndIncrement();
        communication.broadcastUsers(userIdList, broadcastUsersInt, dataInt);

        // ---------- boolean ----------
        boolean dataBool = inc.getAndIncrement() % 2 == 0;
        communication.broadcastUsers(userIdList, broadcastUsersBool, dataBool);

        // ---------- long ----------
        long dataLong = inc.getAndIncrement();
        communication.broadcastUsers(userIdList, broadcastUsersLong, dataLong);

        // ---------- string ----------
        String dataString = "ionet-" + inc.getAndIncrement();
        communication.broadcastUsers(userIdList, broadcastUsersString, dataString);

        // ---------- object ----------
        BookMessage dataObject = new BookMessage();
        dataObject.authorName = "ionet";
        dataObject.bookName = "book-" + inc.getAndIncrement();
        communication.broadcastUsers(userIdList, broadcastUsersObject, dataObject);

        // ---------- list int ----------
        List<Integer> dataListInt = List.of(inc.getAndIncrement(), inc.getAndIncrement());
        communication.broadcastUsersListInt(userIdList, broadcastUsersIntList, dataListInt);

        // ---------- list boolean ----------
        List<Boolean> dataListBool = List.of(
                inc.getAndIncrement() % 2 == 0,
                inc.getAndIncrement() % 2 == 0
        );

        communication.broadcastUsersListBool(userIdList, broadcastUsersBoolList, dataListBool);

        // ---------- list long ----------
        List<Long> dataListLong = List.of(
                (long) inc.getAndIncrement(),
                (long) inc.getAndIncrement()
        );

        communication.broadcastUsersListLong(userIdList, broadcastUsersLongList, dataListLong);

        // ---------- list string ----------
        List<String> dataListString = List.of(
                "ionet-" + inc.getAndIncrement(),
                "ionet-" + inc.getAndIncrement()
        );

        communication.broadcastUsersListString(userIdList, broadcastUsersStringList, dataListString);

        // ---------- list object ----------
        BookMessage message1 = new BookMessage();
        message1.authorName = "ionet";
        message1.bookName = "book-" + inc.getAndIncrement();

        BookMessage message2 = new BookMessage();
        message2.authorName = "ionet";
        message2.bookName = "book-" + inc.getAndIncrement();

        List<BookMessage> dataList = List.of(message1, message2);
        communication.broadcastUsers(userIdList, broadcastUsersObjectList, dataList);
    }

    @ActionMethod(triggerBroadcastMulticast)
    private void triggerBroadcastMulticast() {
        var communication = CommunicationKit.getCommunication();
        // ---------- empty ----------
        communication.broadcastMulticast(broadcastMulticastEmpty);

        // ---------- int ----------
        int dataInt = inc.getAndIncrement();
        communication.broadcastMulticast(broadcastMulticastInt, dataInt);

        // ---------- boolean ----------
        boolean dataBool = inc.getAndIncrement() % 2 == 0;
        communication.broadcastMulticast(broadcastMulticastBool, dataBool);

        // ---------- long ----------
        long dataLong = inc.getAndIncrement();
        communication.broadcastMulticast(broadcastMulticastLong, dataLong);

        // ---------- string ----------
        String dataString = "ionet-" + inc.getAndIncrement();
        communication.broadcastMulticast(broadcastMulticastString, dataString);

        // ---------- object ----------
        BookMessage dataObject = new BookMessage();
        dataObject.authorName = "ionet";
        dataObject.bookName = "book-" + inc.getAndIncrement();
        communication.broadcastMulticast(broadcastMulticastObject, dataObject);

        // ---------- list int ----------
        List<Integer> dataListInt = List.of(inc.getAndIncrement(), inc.getAndIncrement());
        communication.broadcastMulticastListInt(broadcastMulticastIntList, dataListInt);

        // ---------- list boolean ----------
        List<Boolean> dataListBool = List.of(
                inc.getAndIncrement() % 2 == 0,
                inc.getAndIncrement() % 2 == 0
        );

        communication.broadcastMulticastListBool(broadcastMulticastBoolList, dataListBool);

        // ---------- list long ----------
        List<Long> dataListLong = List.of(
                (long) inc.getAndIncrement(),
                (long) inc.getAndIncrement()
        );

        communication.broadcastMulticastListLong(broadcastMulticastLongList, dataListLong);

        // ---------- list string ----------
        List<String> dataListString = List.of(
                "ionet-" + inc.getAndIncrement(),
                "ionet-" + inc.getAndIncrement()
        );

        communication.broadcastMulticastListString(broadcastMulticastStringList, dataListString);

        // ---------- list object ----------
        BookMessage message1 = new BookMessage();
        message1.authorName = "ionet";
        message1.bookName = "book-" + inc.getAndIncrement();

        BookMessage message2 = new BookMessage();
        message2.authorName = "ionet";
        message2.bookName = "book-" + inc.getAndIncrement();

        List<BookMessage> dataList = List.of(message1, message2);
        communication.broadcastMulticast(broadcastMulticastObjectList, dataList);
    }
}
