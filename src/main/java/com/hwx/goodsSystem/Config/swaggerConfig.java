package com.hwx.goodsSystem.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2
 */
@Configuration
@EnableSwagger2
public class swaggerConfig {
    @Bean
    public Docket webapiconfig(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi").apiInfo(webApiinfo())
                .select()
                .build();
    }

    private ApiInfo webApiinfo(){
        return  new ApiInfoBuilder()
                .title("rabbitmq接口文档")
                .description("本文档描述了Rabbitmq微服务接口的定义")
                .version("1.0")
                .contact(new Contact("黄万兴","http://hwxyyds.com","2524029056@qq.com"))
                .build();
    }
}
