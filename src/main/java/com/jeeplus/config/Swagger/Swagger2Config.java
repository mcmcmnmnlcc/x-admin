package com.jeeplus.config.Swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//注解开启 swagger2 功能
@EnableSwagger2
//注解标示,这是一个配置类,@Configuation注解包含了@Component注解
//可以不用在使用@Component注解标记这是个bean了,
@Configuration
public class Swagger2Config {
    @Bean
    public Docket createRestApi() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("项目接口文档")
                .description("项目的接口文档，符合RESTful API。")
                .version("1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jeeplus")) //以扫描包的方式
                .paths(PathSelectors.any())
                .build();
    }

}
