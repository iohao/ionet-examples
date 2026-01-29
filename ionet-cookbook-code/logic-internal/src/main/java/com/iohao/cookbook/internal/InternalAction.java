package com.iohao.cookbook.internal;

import com.iohao.cookbook.common.InternalCmd;
import com.iohao.cookbook.common.message.AuthorMessage;
import com.iohao.cookbook.common.message.BookMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ActionController(InternalCmd.cmd)
public class InternalAction {
    AtomicInteger inc = new AtomicInteger(1);

    @ActionMethod(InternalCmd.emptyAction)
    private int emptyAction() {
        return inc.getAndIncrement();
    }

    @ActionMethod(InternalCmd.intAction)
    private int intAction(int value) {
        return value + inc.getAndIncrement();
    }

    @ActionMethod(InternalCmd.boolAction)
    private boolean boolAction(boolean value) {
        return inc.getAndIncrement() % 2 == 0;
    }

    @ActionMethod(InternalCmd.longAction)
    private long longAction(long value) {
        return value + inc.getAndIncrement();
    }

    @ActionMethod(InternalCmd.stringAction)
    private String stringAction(String value) {
        return value + inc.getAndIncrement();
    }

    @ActionMethod(InternalCmd.objectAction)
    private BookMessage objectAction(AuthorMessage author) {
        var book = new BookMessage();
        book.bookName = "Social Psychology - " + inc.getAndIncrement();
        book.authorName = "David Myers";
        return book;
    }

    @ActionMethod(InternalCmd.intListAction)
    private List<Integer> intListAction(List<Integer> valueList) {
        List<Integer> list = new ArrayList<>();
        for (var value : valueList) {
            list.add(value + inc.getAndIncrement());
        }

        return list;
    }

    @ActionMethod(InternalCmd.boolListAction)
    private List<Boolean> boolListAction(List<Boolean> valueList) {
        List<Boolean> list = new ArrayList<>();
        for (var value : valueList) {
            list.add(!value);
        }

        return list;
    }

    @ActionMethod(InternalCmd.longListAction)
    private List<Long> longListAction(List<Long> valueList) {
        List<Long> list = new ArrayList<>();
        for (var value : valueList) {
            list.add(value + inc.getAndIncrement());
        }

        return list;
    }

    @ActionMethod(InternalCmd.stringListAction)
    private List<String> stringListAction(List<String> valueList) {
        List<String> list = new ArrayList<>();
        for (var value : valueList) {
            list.add(value + inc.getAndIncrement());
        }

        return list;
    }

    @ActionMethod(InternalCmd.objectListAction)
    private List<BookMessage> objectListAction(List<AuthorMessage> authorList) {
        var book1 = new BookMessage();
        book1.bookName = "Social Psychology - " + inc.getAndIncrement();
        book1.authorName = "David Myers";

        var book2 = new BookMessage();
        book2.bookName = "SThe Crowd： A Study of the Popular Mind - " + inc.getAndIncrement();
        book2.authorName = "Gustave Le Bon";
        return List.of(book1, book2);
    }

    // ----------- send -----------

    @ActionMethod(InternalCmd.sendIntAction)
    private void sendIntAction(int value) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendBoolAction)
    private void sendBoolAction(boolean value) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendLongAction)
    private void sendLongAction(long value) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendStringAction)
    private void sendStringAction(String value) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendObjectAction)
    private void sendObjectAction(AuthorMessage author) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendIntListAction)
    private void sendIntListAction(List<Integer> valueList) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendBoolListAction)
    private void sendBoolListAction(List<Boolean> valueList) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendLongListAction)
    private void sendLongListAction(List<Long> valueList) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendStringListAction)
    private void sendStringListAction(List<String> valueList) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendObjectListAction)
    private void sendObjectListAction(List<AuthorMessage> authorList) {
        log.info("{}", inc.getAndIncrement());
    }

    @ActionMethod(InternalCmd.sendEmptyAction)
    private void sendEmptyAction() {
        log.info("{}", inc.getAndIncrement());
    }
}
