package com.lazyloadingtest.springtemplate.repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.lazyloadingtest.DBConfig;
import com.lazyloadingtest.plain.entity.PlainChild;
import com.lazyloadingtest.plain.entity.PlainParent;
import com.lazyloadingtest.plain.repository.PlainParentRepository;


import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        DBConfig.class,
})
public class SpringTransactionTemplateTest {

    @Autowired
    private PlainParentRepository repository;

    @Autowired
    private JpaTransactionManager transactionManager;

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

        TransactionTemplate txTemplate = new TransactionTemplate();
        txTemplate.setTransactionManager(transactionManager);

       Set<PlainChild> fromDB = txTemplate.execute(new TransactionCallback<Set<PlainChild>>() {
            public Set<PlainChild> doInTransaction(TransactionStatus transactionStatus) {
                PlainParent fromDB = repository.findOne(1L);
                return fromDB.getChildren();
            }
        });

        assertEquals(2, fromDB.size());
    }

}
