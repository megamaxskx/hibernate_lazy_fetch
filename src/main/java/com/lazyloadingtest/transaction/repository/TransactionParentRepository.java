package com.lazyloadingtest.transaction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lazyloadingtest.plain.entity.PlainParent;

@Repository
@Transactional
public interface TransactionParentRepository extends CrudRepository<PlainParent, Long> {
    
}
