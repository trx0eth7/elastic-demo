package com.demo.elastic.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("test.data")
@Getter
@Setter
public class DataGenerationConfig {
    private long entityCount = 1;
    private int rangeDate = 20;
    private String resPathNameDictionary;
}
