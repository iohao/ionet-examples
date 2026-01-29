package com.iohao.cookbook.common;

public interface ErrorCodeCmd {
    int cmd = CmdModule.errorCodeCmd;
    // ---------- call ----------
    int index = 0;
    /** 测试 assertXxxThrows 方法 */
    int throwsExample = 1;
}
