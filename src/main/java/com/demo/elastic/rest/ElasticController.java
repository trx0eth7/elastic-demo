package com.demo.elastic.rest;

import com.demo.elastic.config.ElasticConfig;
import com.demo.elastic.helper.ElementHelper;
import com.demo.elastic.model.element.BaseElement;
import com.demo.elastic.repository.BaseElementRepository;
import com.demo.elastic.service.ElasticService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/elasticsearch")
@Slf4j
@AllArgsConstructor
public class ElasticController {

    private ElasticService elasticService;

    private ElasticConfig elasticConfig;

    private BaseElementRepository baseElementRepository;

    private ElementHelper elementHelper;

    @PostMapping(value = "/index/create")
    public ResponseEntity<Void> createIndex(){
        elasticService.createIndex();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/index/element")
    public ResponseEntity<Void> indexElement(@RequestParam UUID id){
        Optional<BaseElement> elOptional = baseElementRepository.findById(id);

        if(elOptional.isPresent()){
            BaseElement element = elOptional.get();

            elasticService.indexDocumentAsync(elementHelper.convertToDocument(element));

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @PostMapping(value = "/index/element/all")
    public ResponseEntity<Void> indexAllElement(){
        Pageable paginator = PageRequest.of(0, elasticConfig.getFetchLimit());
        Page<BaseElement> page;

        StopWatch benchmark = new StopWatch();

        benchmark.start("Indexing all elements");

        do {
            page = baseElementRepository.findAllByPagination(paginator);

            for (BaseElement el : page.getContent()) {
                elasticService.indexDocumentAsync(elementHelper.convertToDocument(el));
            }

            paginator = page.nextPageable();

        } while (page.hasNext());

        benchmark.stop();

        log.info("{} elements have been indexed", page.getTotalElements());

        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping(value = "index/element")
    public ResponseEntity<Void> deleteElement(@PathVariable UUID id){
        elasticService.deleteDocumentAsync(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
