package com.lazyloadingtest.entitygraph.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lazyloadingtest.entitygraph.entity.EntityGraphParent;

@Repository
public interface EntityGraphParentRepository extends CrudRepository<EntityGraphParent, Long> {

    @EntityGraph(value = "graph.Parent.children", type = EntityGraph.EntityGraphType.FETCH)
    public EntityGraphParent findByName(String name);
}
