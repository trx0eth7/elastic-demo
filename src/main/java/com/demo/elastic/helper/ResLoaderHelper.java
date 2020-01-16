package com.demo.elastic.helper;

import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

@Component("demo_ResLoaderHelper")
@NoArgsConstructor
public class ResLoaderHelper {

    public List<String> loadResFileContentAsList(String path) {
        Resource res = new ClassPathResource(path);

        List<String> content = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(res.getInputStream()))) {
            content = reader.lines().collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public String loadResFileContent(String path) {
        Resource res = new ClassPathResource(path);

        String content = "";

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(res.getInputStream()))) {
            content = reader.lines().collect(joining());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }
}
