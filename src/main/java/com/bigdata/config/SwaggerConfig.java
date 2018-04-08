package com.bigdata.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Desciption Swagger配置
 * Create By  li.bo
 * CreateTime 2018/3/26 10:56
 * UpdateTime 2018/3/26 10:56
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        Predicate<RequestHandler> predicate = input -> true;
        return new Docket(DocumentationType.SWAGGER_2)
                .host("http://localhost:8088")
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(predicate)
                .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Patrick Leee的docker服务API文档")
                .version("1.0")
                .build();
    }
}
