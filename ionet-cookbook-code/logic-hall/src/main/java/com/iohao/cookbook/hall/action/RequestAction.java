package com.iohao.cookbook.hall.action;

import com.iohao.cookbook.common.RequestCmd;
import com.iohao.cookbook.common.message.AuthorMessage;
import com.iohao.cookbook.common.message.BookMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ActionController(RequestCmd.cmd)
public class RequestAction {
    AtomicInteger inc = new AtomicInteger(1);

    @ActionMethod(RequestCmd.emptyAction)
    private void emptyAction() {
        inc.getAndIncrement();
    }

    @ActionMethod(RequestCmd.intAction)
    private int intAction(int value) {
        return value + inc.getAndIncrement();
    }

    @ActionMethod(RequestCmd.boolAction)
    private boolean boolAction(boolean value) {
        return inc.getAndIncrement() % 2 == 0;
    }

    @ActionMethod(RequestCmd.longAction)
    private long longAction(long value) {
        return value + inc.getAndIncrement();
    }

    @ActionMethod(RequestCmd.stringAction)
    private String stringAction(String value) {
        return value + inc.getAndIncrement();
    }

    @ActionMethod(RequestCmd.objectAction)
    private BookMessage objectAction(AuthorMessage author) {
        var book = new BookMessage();
        book.bookName = "Social Psychology";
        book.authorName = author.authorName;
        return book;
    }

    @ActionMethod(RequestCmd.intListAction)
    private List<Integer> intListAction(List<Integer> valueList) {
        List<Integer> list = new ArrayList<>();
        for (var value : valueList) {
            list.add(value + inc.getAndIncrement());
        }

        return list;
    }

    @ActionMethod(RequestCmd.boolListAction)
    private List<Boolean> boolListAction(List<Boolean> valueList) {
        List<Boolean> list = new ArrayList<>();
        for (var value : valueList) {
            list.add(!value);
        }

        return list;
    }

    @ActionMethod(RequestCmd.longListAction)
    private List<Long> longListAction(List<Long> valueList) {
        List<Long> list = new ArrayList<>();
        for (var value : valueList) {
            list.add(value + inc.getAndIncrement());
        }

        return list;
    }

    @ActionMethod(RequestCmd.stringListAction)
    private List<String> stringListAction(List<String> valueList) {
        List<String> list = new ArrayList<>();
        for (var value : valueList) {
            list.add(value + inc.getAndIncrement());
        }

        return list;
    }

    @ActionMethod(RequestCmd.objectListAction)
    private List<BookMessage> objectListAction(List<AuthorMessage> authorList) {
        var book1 = new BookMessage();
        book1.bookName = "Social Psychology";
        book1.authorName = "David Myers";

        var book2 = new BookMessage();
        book2.bookName = "SThe Crowd： A Study of the Popular Mind";
        book2.authorName = "Gustave Le Bon";
        return List.of(book1, book2);
    }
}
