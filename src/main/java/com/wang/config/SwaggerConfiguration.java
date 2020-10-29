package com.wang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createSwaggerInfo(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(myApiInfo())
                .select()
                //swagger注解扫描的路径
                .apis(RequestHandlerSelectors.basePackage("com.wang")).paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
     * @return
     */
    private ApiInfo myApiInfo(){
        return new ApiInfoBuilder()
                // 页面标题
                .title("SpringBoot集成swagger2")
                // 创建人信息
                .contact(new Contact("候居",  "https://github.com/wanghe2?tab=repositories",  "2290370311@qq.com"))
                // 版本号
                .version("1.0")
                // 描述
                .description("该项目主要集成swagger,还准备集成spring data jpa 与spring boot security")
                .build();
    }
}
