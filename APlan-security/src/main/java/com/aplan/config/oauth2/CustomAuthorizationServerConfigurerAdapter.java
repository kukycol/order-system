package com.aplan.config.oauth2;

import com.aplan.exception.login.CustomWebResponseExceptionTranslator;
import com.aplan.exception.token.CustomAuthenticationEntryPoint;
import com.aplan.service.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@Order(404)
public class CustomAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {

    //身份验证管理器
    @Autowired
    private AuthenticationManager authenticationManager;
    //自定义用户详细信息服务
    @Autowired
    private CustomUserDetailsService userDetailsService;
    //Redis 连接工厂
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    //数据源
    @Autowired
    private DataSource dataSource;
    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    @Bean
    public TokenStore tokenStore() {
        //使用redis存储token
        return new RedisTokenStore(redisConnectionFactory);
        //使用mysql存储token
//        return new JdbcTokenStore(dataSource);
    }

    private AuthorizationServerTokenServices tokenServices() {
        OverrideTokenServices tokenServices = new OverrideTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setAlwaysCreateToken(true);
        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(clientDetailsService);
        return tokenServices;
    }

    /**
     * 授权服务器端点配置器
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //认证管理
        endpoints.authenticationManager(authenticationManager);
        // token 存储
        endpoints.tokenStore(tokenStore());
        // token 逻辑
        endpoints.tokenServices(tokenServices());
        //没有配置这个，不能正常使用刷新token接口
        endpoints.userDetailsService(userDetailsService);
        //允许令牌（token）的请求方式,只能使用post表单形式获取token
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.POST,HttpMethod.GET);
        //异常翻译器
        endpoints.exceptionTranslator(customWebResponseExceptionTranslator);
    }


    /**
     * 授权服务器安全配置器
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .realm("oauth2-resources")
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                //允许客户端进行表单身份验证
                .allowFormAuthenticationForClients()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }


    /**
     * 客户端详细信息服务配置
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //动态配置授权方式，使用数据表存储授权信息
        clients.jdbc(dataSource);
    }


}