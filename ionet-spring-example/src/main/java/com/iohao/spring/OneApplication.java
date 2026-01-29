/*
 * ionet
 * Copyright (C) 2021 - present  渔民小镇 （262610965@qq.com、luoyizhu@gmail.com） . All Rights Reserved.
 * # iohao.com . 渔民小镇
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General  License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General  License for more details.
 *
 * You should have received a copy of the GNU Affero General  License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.iohao.spring;

import com.iohao.net.app.RunOne;
import com.iohao.net.extension.spring.ActionFactoryBeanForSpring;
import com.iohao.net.external.core.config.ExternalGlobalConfig;
import com.iohao.net.external.core.netty.ExternalMapper;
import com.iohao.net.server.LogicServer;
import com.iohao.spring.server.MyLogicServer;
import io.aeron.Aeron;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 *
 * @author 渔民小镇
 * @date 2025-10-04
 * @since 25.1
 */
@SpringBootApplication
@SuppressWarnings("all")
public class OneApplication {
    static void main(String[] args) {
        var context = SpringApplication.run(OneApplication.class, args);
        Aeron aeron = context.getBean(Aeron.class);

        int port = ExternalGlobalConfig.externalPort;
        var externalServer = ExternalMapper.builder(port).build();

        new RunOne()
                .setAeron(aeron)
                .enableCenterServer()
                .setExternalServer(externalServer)
                .setLogicServerList(listLogic())
                .startup();
    }

    @Bean
    public ActionFactoryBeanForSpring actionFactoryBean() {
        return new ActionFactoryBeanForSpring();
    }

    static List<LogicServer> listLogic() {
        return List.of(new MyLogicServer());
    }
}
