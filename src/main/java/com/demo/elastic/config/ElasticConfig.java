package com.demo.elastic.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("elasticsearch")
@Getter
@Setter
public class ElasticConfig {

    private String host;
    private Integer port;
    private String scheme;
    private String indexName;
    private String resPathSetting;
    private String resPathMapping;
    private Integer fetchLimit;
}
