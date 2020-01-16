package com.demo.elastic.repository;

import com.demo.elastic.model.element.BaseElement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BaseElementRepository extends JpaRepository<BaseElement, UUID> {

    List<BaseElement> findByName(String name);

    @Query(value = "select e from BaseElement e order by e.id")
    Page<BaseElement> findAllByPagination(Pageable pageable);
}
