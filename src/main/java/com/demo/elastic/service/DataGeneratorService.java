package com.demo.elastic.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Slf4j
public class DataGeneratorService {

    @PersistenceContext
    private EntityManager em;

    public void generateTestData() {
        
    }
}
