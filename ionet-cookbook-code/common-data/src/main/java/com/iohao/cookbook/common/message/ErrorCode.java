package com.iohao.cookbook.common.message;

import com.iohao.net.framework.core.exception.ErrorInformation;
import com.iohao.net.common.kit.LocaleKit;
import lombok.Getter;

@Getter
public enum ErrorCode implements ErrorInformation {
    loginFailed("登录失败"),
    illegalOperation("非法操作"),
    roomNotExist("房间不存在"),
    roomSpaceSizeNotEnough("房间空间不足，人数已满"),
    userOnline("User Online");
    /** 消息码 */
    final int code;
    /** 消息模板 */
    final String message;

    ErrorCode(String message) {
        this.code = this.ordinal() + 1;

        if (LocaleKit.isChina()) {
            this.message = message;
        } else {
            this.message = this.name();
        }
    }
}
