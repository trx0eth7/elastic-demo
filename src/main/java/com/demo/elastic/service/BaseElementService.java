package com.demo.elastic.service;

import com.demo.elastic.model.element.BaseElement;
import com.demo.elastic.repository.BaseElementRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BaseElementService {

    private BaseElementRepository baseElementRepository;

    public List<BaseElement> retrieveAllBaseElement() {
        return baseElementRepository.findAll();
    }

    public List<BaseElement> retrieveABaseElementByName(String name) {
        return baseElementRepository.findByName(name);
    }
}
