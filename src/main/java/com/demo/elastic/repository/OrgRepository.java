package com.demo.elastic.repository;

import com.demo.elastic.model.org.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrgRepository extends JpaRepository<Org, UUID> {
}
