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
package com.iohao.spring;

import com.iohao.net.extension.codegen.GDScriptDocumentGenerate;
import com.iohao.net.external.core.config.ExternalGlobalConfig;
import com.iohao.net.framework.CoreGlobalConfig;
import com.iohao.net.framework.core.ActionCommand;
import com.iohao.net.framework.core.ActionFactoryBean;
import com.iohao.net.framework.core.BarSkeleton;
import com.iohao.net.framework.core.DependencyInjectionPart;
import com.iohao.net.framework.core.doc.DocumentAccessAuthentication;
import com.iohao.net.framework.core.doc.DocumentHelper;
import io.aeron.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 *
 * @author 渔民小镇
 * @date 2025-12-10
 * @since 25.1
 */
public final class GenerateTest {
    static String rootPath = "/Users/join/gitme/test-sdk/";

    static void main() {
        supportSpringAction();

        // i18n: CHINA or US
        Locale.setDefault(Locale.US);
        CoreGlobalConfig.setting.parseDoc = true;

        // Load the business framework of each gameLogicServer
        // cn: 加载游戏逻辑服的业务框架
        OneApplication.listLogic().forEach(logicServer -> {
            var builder = BarSkeleton.builder();
            logicServer.settingBarSkeletonBuilder(builder);
            builder.build();
        });

        generateCodeGDScriptGodot();
        // Generate document
        DocumentHelper.generateDocument();
    }

    private static void generateCodeGDScriptGodot() {
        var documentGenerate = new GDScriptDocumentGenerate();
        // By default, it will be generated in the target/code directory
        // cn: 设置代码生成所存放的路径，如果不做任何设置，将会生成在 target/code 目录中
        String path = rootPath + "ioGameSdkGDScriptExampleGodot/gen/code";
        documentGenerate.setPath(path);

        DocumentHelper.addDocumentGenerate(documentGenerate);
    }

    private static void supportSpringAction() {
        // 支持 Spring 构造注入。
        // supportSpringAction.
        DependencyInjectionPart dependencyInjectionPart = DependencyInjectionPart.me();
        dependencyInjectionPart.injection = true;
        dependencyInjectionPart.annotationClass = Component.class;
        dependencyInjectionPart.actionFactoryBean = new ActionFactoryBean<>() {
            @Override
            public Object getBean(ActionCommand actionCommand) {
                return new Object();
            }

            @Override
            public Object getBean(Class<?> actionControllerClazz) {
                return new Object();
            }
        };
    }
}
