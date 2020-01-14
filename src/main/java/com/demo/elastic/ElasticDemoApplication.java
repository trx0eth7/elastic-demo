package com.demo.elastic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ElasticDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(ElasticDemoApplication.class, args);

//        DataGeneratorService generatorService = context.getBean(DataGeneratorService.class);
//        generatorService.generateTestData();
    }
}
