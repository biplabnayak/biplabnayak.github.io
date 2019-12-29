package com.biplab.poc.jpa.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

/**
 * @author Biplab Nayak [Created on 6/27/2017].
 */
@Component
public class AddressDao {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired

    EntityManagerFactory entityManagerFactory;

    @PostConstruct
    public void bootstrap() {
        System.out.println();
    }

}
