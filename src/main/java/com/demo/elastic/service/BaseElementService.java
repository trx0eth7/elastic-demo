package com.demo.elastic.service;

import com.demo.elastic.model.element.BaseElement;
import com.demo.elastic.repository.BaseElementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Slf4j
public class BaseElementService {

    private BaseElementRepository baseElementRepository;

    @Inject
    public BaseElementService(@NotNull BaseElementRepository baseElementRepository) {
        this.baseElementRepository = baseElementRepository;
    }

    public List<BaseElement> retrieveAllBaseElement() {
        return baseElementRepository.findAll();
    }

    public List<BaseElement> retrieveABaseElementByName(String name) {
        return baseElementRepository.findByName(name);
    }
}
