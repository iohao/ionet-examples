package com.iohao.cookbook.common.message;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.ToString;

@ToString
@ProtobufClass
public class AuthorMessage {
    public String authorName;
}
