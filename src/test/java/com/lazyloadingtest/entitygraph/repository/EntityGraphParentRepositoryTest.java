package com.lazyloadingtest.entitygraph.repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lazyloadingtest.DBConfig;
import com.lazyloadingtest.entitygraph.entity.EntityGraphChild;
import com.lazyloadingtest.entitygraph.entity.EntityGraphParent;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        DBConfig.class,
})
public class EntityGraphParentRepositoryTest {

    @Autowired
    private EntityGraphParentRepository repository;

    @Test
    public void testRepository() {
        EntityGraphChild child1 = new EntityGraphChild();
        child1.setName("first child");

        EntityGraphChild child2 = new EntityGraphChild();
        child2.setName("second child");

        EntityGraphParent parent = new EntityGraphParent();
        parent.setId(1l);
        parent.setName("ParentGraph");
        HashSet<EntityGraphChild> children = new HashSet<EntityGraphChild>(Arrays.asList(child1, child2));
        parent.setChildren(children);

        repository.save(parent);

        System.out.println("--- Before querying repo");
        EntityGraphParent fromDB = repository.findByName("ParentGraph");
        System.out.println("--- After querying repo");
        assertEquals(2, fromDB.getChildren().size());
        System.out.println("--- Test finished");
    }

}