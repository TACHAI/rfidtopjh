package com.laishishui.rfidtopjh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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

/**
 * Swagger2配置类
 * @ClassName Swagger2
 * @Author https://github.com/TACHAI
 * @Email tc1206966083@gmail.com
 * @Date 2019-06-25
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerSettings.class)
public class Swagger2 {

    @Autowired
    private SwaggerSettings swaggerSettings;

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                // todo 生产取消swagger
                .enable(swaggerSettings.getEnable())
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.laishishui.rfidtopjh.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("tachai", "", "tc1206966083@gmail.com");
        return new ApiInfoBuilder()
                .title("更新排架号RESTful APIs")
                .description("更多关注http://www.laishishui.com")
                .termsOfServiceUrl("http://www.laishishui.com")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
