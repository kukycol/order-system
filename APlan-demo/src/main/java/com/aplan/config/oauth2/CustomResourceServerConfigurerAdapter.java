package com.aplan.config.oauth2;

import com.aplan.exception.rule.CustomAccessDeniedHandler;
import com.aplan.exception.token.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@Configuration
@Order(402)
public class CustomResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * Http安全
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //授权请求
                .authorizeRequests()
                //动态权限，权限都在数据表中配置
//                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
//                    @Override
//                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
//                        object.setSecurityMetadataSource(customFilterInvocationSecurityMetadataSource);
//                        object.setAccessDecisionManager(customAccessDecisionManager);
//                        return object;
//                    }
//                })
                //任何请求
                .anyRequest()
                //都需要认证
                .authenticated()
        ;
    }


    /**
     * 资源服务器安全配置器
     *
     * @param resources
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                //资源ID不匹配时，抛出异常；资源ID为空时，允许访问所有资源服务器
                .resourceId("oauth2-resource")
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(customAccessDeniedHandler)

        ;
    }


}
