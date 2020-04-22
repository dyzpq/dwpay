package com.dw.pay.paymanageapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaogang
 * @date 2018/5/23 10:48
 */
@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurerAdapter {
    @Value("${swagger.show}")
    private boolean swaggerShow;
    @Bean
    public Docket createRestApi(){
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        ParameterBuilder access_token = parameterBuilder.name("ACCESS_TOKEN");
        access_token.required(false);
        access_token.description("用户是否登录标识");
        access_token.parameterType("header");
        access_token.modelRef(new ModelRef("string"));
        Parameter token = access_token.build();


        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo());
        ApiSelectorBuilder apiSelectorBuilder = docket.select();
        apiSelectorBuilder.apis(RequestHandlerSelectors.basePackage("com.dw.pay.paymanageapi.web.api"));
        //设置为上面basePackage包下面的哪些class生成接口信息
        apiSelectorBuilder.paths(PathSelectors.any());
        docket = apiSelectorBuilder.build();

        List<Parameter> operationParameters = new ArrayList<>();
        operationParameters.add(token);
        docket.enable(swaggerShow);
        docket.globalOperationParameters(operationParameters);
        return docket;
    }

    private ApiInfo apiInfo(){
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("dw支付后台接口");
        apiInfoBuilder.description("通过swagger可以查看接口的具体信息，以及测试接口");
        apiInfoBuilder.termsOfServiceUrl("http://www.baidu.com/");
        Contact contact = new Contact("dw","","");
        apiInfoBuilder.contact(contact);
        apiInfoBuilder.version("1.0");
        ApiInfo apiInfo = apiInfoBuilder.build();
        return apiInfo;
    }

    //添加ResourceHandler
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }




}
