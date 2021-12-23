package com.aplan;

import com.aplan.utils.IPHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.net.UnknownHostException;

// mybatis-plus 包扫描
@MapperScan("com.aplan.mapper")
@SpringBootApplication
public class Oauth2Application {
    static Logger logger = LoggerFactory.getLogger(Oauth2Application.class);


    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(Oauth2Application.class, args);
        Environment env = application.getEnvironment();

        logger.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local   Doc: \thttp://localhost:{}/doc.html\n\t" +
                        "IPV4    Doc: \thttp://{}:{}/doc.html\n\t" +
                        "Local Druid: \thttp://localhost:{}/druid\n\t" +
                        "IPV4  Druid: \thttp://{}:{}/druid\n\t" +
                        "Local      : \thttp://localhost:9527/\n\t" +
                        "IPV4       : \thttp://{}:9527/\n\t" +
                        "External   : \thttp://{}:{}\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                IPHelper.getInet4Address(),
                env.getProperty("server.port"),
                env.getProperty("server.port"),
                IPHelper.getInet4Address(),
                env.getProperty("server.port"),
                IPHelper.getInet4Address(),
                IPHelper.getInet4Address(),
                env.getProperty("server.port")
        );

    }


    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "|{}[]"));
        return factory;
    }

}