package com.lazyloadingtest.plain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lazyloadingtest.plain.entity.PlainParent;


@Repository
public interface PlainParentRepository extends CrudRepository<PlainParent, Long> {
    
}
