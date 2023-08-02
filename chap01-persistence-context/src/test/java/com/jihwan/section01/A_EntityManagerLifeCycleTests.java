package com.jihwan.section01;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class A_EntityManagerLifeCycleTests {

    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactoty(){
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void 엔터티_매니저_팩토리와_엔터티_매니저_생명주기_확인() {
        System.out.println("entityManagerFactory.hashcode : "+entityManagerFactory.hashCode());
        System.out.println("entityManager.hashcode : "+entityManager.hashCode());

    }

    @Test
    void 엔터티_매니저_팩토리와_엔터티_매니저_생명주기_확인2() {
        System.out.println("entityManagerFactory.hashcode : " +entityManagerFactory.hashCode());
        System.out.println("entityManager.hashcode : "+entityManager.hashCode());
    }

    @AfterAll
    public static void closeFactoty() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }
}
