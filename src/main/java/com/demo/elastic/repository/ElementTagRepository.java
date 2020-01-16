package com.demo.elastic.repository;

import com.demo.elastic.model.element.ElementTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ElementTagRepository extends JpaRepository<ElementTag, UUID> {
}
