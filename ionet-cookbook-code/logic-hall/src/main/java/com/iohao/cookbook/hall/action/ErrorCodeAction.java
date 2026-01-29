/*
 * ionet
 * Copyright (C) 2021 - present  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.iohao.cookbook.hall.action;

import com.iohao.cookbook.common.ErrorCodeCmd;
import com.iohao.cookbook.common.message.ErrorCode;
import com.iohao.cookbook.common.message.ErrorCodeMessage;
import com.iohao.cookbook.common.message.ErrorCodeThrowsMessage;
import com.iohao.net.framework.annotations.ActionController;
import com.iohao.net.framework.annotations.ActionMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * test ErrorCode
 *
 * @author 渔民小镇
 * @date 2026-01-29
 * @since 25.3
 */
@ActionController(ErrorCodeCmd.cmd)
public final class ErrorCodeAction {
    private static final Logger log = LoggerFactory.getLogger(ErrorCodeAction.class);

    @ActionMethod(ErrorCodeCmd.index)
    public void index(ErrorCodeMessage message) {

        // ===== ErrorInformation Interface Methods Examples =====

        // 1. getCode() / getMessage() - Get error code and message
        int code = ErrorCode.illegalOperation.getCode();
        String msg = ErrorCode.illegalOperation.getMessage();
        log.info("ErrorCode.illegalOperation -> code: {}, message: {}", code, msg);

        // 2. require(T value) - Validates value is not null/empty, throws BizException on failure
        // Validates that name must have a value
        String name = ErrorCode.illegalOperation.require(message.name);
        log.info("require example -> name: {}", name);

        // 3. assertTrue(boolean condition) - Throws exception when condition is false
        // Validates that operation must succeed
        ErrorCode.illegalOperation.assertTrue(message.success);
        log.info("assertTrue example -> success is true");

        // 4. assertTrueThrows(boolean condition) - Throws exception when condition is true
        // Throws exception when success is true (used for expected failure scenarios)
        // ErrorCode.illegalOperation.assertTrueThrows(message.success);

        // 5. assertNonNull(Object obj) - Throws exception when object is null
        // Validates that data must exist
        ErrorCode.illegalOperation.assertNonNull(message.data);
        log.info("assertNonNull example -> data is not null");

        // 6. assertNullThrows(Object obj) - Throws exception when object is not null
        // Throws exception when nullableData has value (used for expected null scenarios)
        // ErrorCode.illegalOperation.assertNullThrows(message.nullableData);

    }

    /**
     * Demonstrates the usage of assertXxxThrows series methods.
     * <p>
     * assertXxxThrows methods throw an exception when the condition is met (expected value).
     * Commonly used for parameter validation and "expected failure" scenarios.
     *
     * @param message test message
     */
    @ActionMethod(ErrorCodeCmd.throwsExample)
    public void throwsExample(ErrorCodeThrowsMessage message) {

        // ===== assertXxxThrows Series Methods Examples =====

        // 1. assertTrueThrows(boolean condition) - throws exception when condition is true
        // Expected failure scenario: when shouldFail is true, indicating a test that should fail
        ErrorCode.illegalOperation.assertTrueThrows(message.shouldFail);
        log.info("assertTrueThrows example -> shouldFail is false, no exception thrown");

        // 2. assertNullThrows(Object obj) - throws exception when object is not null
        // Expected null scenario: when existingData has value, it means data already exists, which is not expected
        ErrorCode.illegalOperation.assertNullThrows(message.existingData);
        log.info("assertNullThrows example -> existingData is null, no exception thrown");
    }
}
