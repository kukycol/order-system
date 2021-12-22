package com.aplan.config.swagger;


import cn.hutool.core.collection.CollectionUtil;
import com.aplan.bean.value.Doc;
import com.aplan.bean.value.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

//此类为配置类
@Configuration
//开启swagger接口文档
@EnableSwagger2WebMvc
public class CustomSwaggerConfig {

    @Autowired
    private Doc doc;
    @Autowired
    private SwaggerConfig swaggerConfig;


    @Bean
    public Docket productApi() {
        //schema
        List<GrantType> grantTypes=new ArrayList<>();
        //密码模式

        ResourceOwnerPasswordCredentialsGrant resourceOwnerPasswordCredentialsGrant=new ResourceOwnerPasswordCredentialsGrant(swaggerConfig.getTokenUrl());
        grantTypes.add(resourceOwnerPasswordCredentialsGrant);

        OAuth oAuth=new OAuthBuilder().name("oauth2")
                .grantTypes(grantTypes).build();
        //context
        //scope方位
        List<AuthorizationScope> scopes=new ArrayList<>();
        scopes.add(new AuthorizationScope("read", "read  resources"));
        scopes.add(new AuthorizationScope("write", "write resources"));
        scopes.add(new AuthorizationScope("reads", "read all resources"));
        scopes.add(new AuthorizationScope("writes", "write all resources"));
        SecurityReference securityReference=new SecurityReference("oauth2",scopes.toArray(new AuthorizationScope[]{}));
        SecurityContext securityContext=new SecurityContext(CollectionUtil.newArrayList(securityReference),PathSelectors.ant("/**"));
        //schemas
        List<SecurityScheme> securitySchemes=CollectionUtil.newArrayList(oAuth);
        //securyContext
        List<SecurityContext> securityContexts=CollectionUtil.newArrayList(securityContext);
        return new Docket(DocumentationType.SWAGGER_2)
                //开启或关闭swagger文档
                .enable(swaggerConfig.isEnable())
                .select()
                //扫描指定的包下
                //多条件生成接口文档，com.aplan.controller包下且有类中有@ApiOperation注解
                .apis(RequestHandlerSelectors.basePackage(swaggerConfig.getBasePackage()))
//                .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securityContexts(securityContexts)
                .securitySchemes(securitySchemes)
                .apiInfo(apiInfo());

    }


    // api 信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(doc.getTitle())
                .description(doc.getDescription())
                .termsOfServiceUrl(doc.getTermsOfServiceUrl())
                .contact(new Contact(doc.getAuthor(), doc.getUrl(), doc.getEmail()))
                .license(doc.getLicense())
                .licenseUrl(doc.getLicenseUrl())
                .version(doc.getVersion())
                .build();
    }


}




