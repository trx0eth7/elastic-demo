package com.demo.elastic.rest;

import com.demo.elastic.model.element.BaseElement;
import com.demo.elastic.service.BaseElementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/v1/element")
@Slf4j
public class BaseElementController {

    private BaseElementService baseElementService;

    @Inject
    public BaseElementController(@NotNull BaseElementService baseElementService) {
        this.baseElementService = baseElementService;
    }

    @GetMapping(value = "/all")
    public List<BaseElement> getAllElement(){
       return baseElementService.retrieveAllBaseElement();
    }

    @GetMapping(path = "{name}")
    public List<BaseElement> getElementByName(@PathVariable("name") String name){
        return baseElementService.retrieveABaseElementByName(name);
    }

}
