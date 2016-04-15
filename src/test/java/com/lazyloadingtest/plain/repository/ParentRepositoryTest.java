package com.lazyloadingtest.plain.repository;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lazyloadingtest.DBConfig;
import com.lazyloadingtest.plain.entity.PlainChild;
import com.lazyloadingtest.plain.entity.PlainParent;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        DBConfig.class,
})
public class ParentRepositoryTest {

    @Autowired
    private PlainParentRepository repository;

    @Test
    public void testRepository() {
        PlainChild child1 = new PlainChild();
        child1.setName("first child");

        PlainChild child2 = new PlainChild();
        child2.setName("second child");

        PlainParent parent = new PlainParent();
        parent.setId(1l);
        HashSet<PlainChild> children = new HashSet<PlainChild>(Arrays.asList(child1, child2));
        parent.setChildren(children);

        repository.save(parent);
        PlainParent fromDB = repository.findOne(1L);

        assertEquals(2, fromDB.getChildren().size());
    }
}