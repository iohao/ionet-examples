package com.iohao.cookbook.region;

import com.iohao.cookbook.common.RequestCmd;
import com.iohao.cookbook.common.message.AuthorMessage;
import com.iohao.cookbook.common.message.BookMessage;
import com.iohao.net.extension.client.AbstractInputCommandRegion;
import lombok.extern.slf4j.Slf4j;

import static com.iohao.cookbook.common.RequestCmd.*;

import java.util.List;

@Slf4j
public class RequestRegion extends AbstractInputCommandRegion {
    @Override
    public void initInputCommand() {
        this.setCmd(RequestCmd.cmd, "Request");
        ofCommand(emptyAction, "emptyAction");

        ofCommand(intAction, "intAction").setRequestData(() -> {
            // request parameter
            return 1;
        }).callback(result -> {
            log.info("{}", result.getInt());
        });

        ofCommand(boolAction, "boolAction").setRequestData(() -> {
            // request parameter
            return true;
        }).callback(result -> {
            log.info("{}", result.getBoolean());
        });

        ofCommand(longAction, "longAction").setRequestData(() -> {
            // request parameter
            return 1L;
        }).callback(result -> {
            log.info("{}", result.getLong());
        });

        ofCommand(stringAction, "stringAction").setRequestData(() -> {
            // request parameter
            return "ionet";
        }).callback(result -> {
            log.info("{}", result.getString());
        });

        ofCommand(objectAction, "objectAction").setRequestData(() -> {
            // request parameter
            var message = new AuthorMessage();
            message.authorName = "David Myers";
            return message;
        }).callback(result -> {
            log.info("{}", result.getValue(BookMessage.class));
        });

        // --------------- list ---------------
        ofCommand(intListAction, "intListAction").setRequestData(() -> {
            // request parameter
            return List.of(1, 2, 3);
        }).callback(result -> {
            log.info("{}", result.listInt());
        });

        ofCommand(boolListAction, "boolListAction").setRequestData(() -> {
            // request parameter
            return List.of(true, false, true);
        }).callback(result -> {
            log.info("{}", result.listBoolean());
        });

        ofCommand(longListAction, "longListAction").setRequestData(() -> {
            // request parameter
            return List.of(1L, 2L, 3L);
        }).callback(result -> {
            log.info("{}", result.listLong());
        });

        ofCommand(stringListAction, "stringListAction").setRequestData(() -> {
            // request parameter
            return List.of("Michael Jackson", "Daniel Kahneman", "ionet");
        }).callback(result -> {
            log.info("{}", result.listString());
        });

        ofCommand(objectListAction, "objectListAction").setRequestData(() -> {
            // request parameter
            var message1 = new AuthorMessage();
            message1.authorName = "Michael Jackson";

            var message2 = new AuthorMessage();
            message2.authorName = "David Myers";
            return List.of(message1, message2);
        }).callback(result -> {
            List<BookMessage> bookMessageList = result.listValue(BookMessage.class);
            log.info("{}", bookMessageList);
        });
    }
}
