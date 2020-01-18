package com.demo.elastic.helper;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Component("demo_ResLoaderHelper")
@NoArgsConstructor
public class ResLoaderHelper {

    @SneakyThrows(IOException.class)
    public List<String> loadResFileContentAsList(String path) {
        Resource res = new ClassPathResource(path);

        @Cleanup BufferedReader reader =
                new BufferedReader(new InputStreamReader(res.getInputStream()));

        return reader.lines().collect(toList());
    }

    @SneakyThrows(IOException.class)
    public String loadResFileContent(String path) {
        Resource res = new ClassPathResource(path);

        @Cleanup BufferedReader reader =
                new BufferedReader(new InputStreamReader(res.getInputStream()));

        return reader.lines().collect(joining());
    }
}
