package com.demo.elastic;

import com.demo.elastic.service.DataGeneratorService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ElasticDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(ElasticDemoApplication.class, args);

        DataGeneratorService generatorService = context.getBean(DataGeneratorService.class);
        generatorService.generateTestData();
    }

    @Configuration
    @ConfigurationProperties("data.generation")
    @Getter
    @Setter
    public class DataGenerationConfig {
        private long entityCount = 1;
        private int rangeDate = 20;
        private String resPathNameDictionary;
    }
}
