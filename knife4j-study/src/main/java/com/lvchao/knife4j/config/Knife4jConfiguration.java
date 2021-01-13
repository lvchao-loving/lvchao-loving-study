package com.lvchao.knife4j.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author <a href="mailto:xiaoymin@foxmail.com">xiaoymin@foxmail.com</a>
 * 2020/11/07 9:26
 * @since:knife4j-spring-boot-fast-demo 1.0
 */
@Configuration
/**
 * 选择 2.0.8 版本的 knife4j-spring-boot-starter 使用下面的注解
 */
@EnableSwagger2WebMvc

/**
 * 选择 2.0.3 版本的 knife4j-spring-boot-starter 使用下面的注解
 * 实有人口平台使用的版本号
 */
/*@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)*/
public class Knife4jConfiguration {
    @Value("${spring.application.name}")
    private String title;

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        Docket docket=new Docket(DocumentationType.SWAGGER_2)
                // api详细信息描述
                .apiInfo(apiInfo())
                //分组名称
                .groupName("1.X版本")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.lvchao"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(title + "服务")
                .description("服务提供者")
                .version("1.0")
                .termsOfServiceUrl("http://www.lvchao.com/")
                .contact(new Contact("吕超","","532180467@qq.com"))
                .licenseUrl("http://www.lvchao.com/")
                .build();
    }
}