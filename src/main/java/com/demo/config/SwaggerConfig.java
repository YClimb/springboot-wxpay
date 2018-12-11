package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Desc:swagger api 的配置
 * <p>
 * 访问:http://ip:port/swagger-ui.html
 * [localhsot:8081/swagger-ui.html 可能有登录拦截,需要先登录系统后在访问该地址]
 * </p>
 * <p>
 * Created by yclimb on 2017/4/19.
 */
@Conditional(SwaggerCondition.class)
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * /api.* [访问的路径匹配,如:SwaggerApiController.java @RequestMapping("/api/v1") 符合该路径匹配]
     *
     * @return Docket
     */
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 确保以下配置的信息可用，否则不能访问
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("系统接口文档")
                .description("系统接口文档")
                .license("Apache license")
                .version("2.0")
                .build();
    }
}