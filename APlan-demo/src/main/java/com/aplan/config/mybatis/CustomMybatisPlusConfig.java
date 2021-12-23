package com.aplan.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.MybatisMapWrapperFactory;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//扫描项目mapper文件
public class CustomMybatisPlusConfig {

    /**
     * Mybatis Plus 拦截器
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //添加分页内部拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * 配置定制器
     *
     * @return
     */
    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return new ConfigurationCustomizer() {
            /**
             * Mybatis 配置
             * @param configuration
             */
            @Override
            public void customize(MybatisConfiguration configuration) {
                //设置对象包装工厂,非自动注入的sql语句，接收对象为map也将进行驼峰转换
                configuration.setObjectWrapperFactory(new MybatisMapWrapperFactory());
            }
        };
    }

}
