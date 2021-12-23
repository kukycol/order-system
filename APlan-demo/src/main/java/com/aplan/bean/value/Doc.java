package com.aplan.bean.value;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "knife4j.doc")
public class Doc {

    private String title;
    private String description;
    private String termsOfServiceUrl;
    private String version;
    private String licenseUrl;
    private String license;
    private String author;
    private String url;
    private String email;

}
