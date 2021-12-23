package com.aplan.bean.value;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "knife4j.config")
public class SwaggerConfig {

    private String tokenUrl;
    private String basePackage;
    private boolean enable;

}
