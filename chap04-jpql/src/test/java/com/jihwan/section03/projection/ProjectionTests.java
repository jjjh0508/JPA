package com.jihwan.section03.projection;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class ProjectionTests {
    private static EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    void 단일_엔티티_프로젝션_테스트() {
        // 프로젝션 : 우리가 조회할 대상
        String jpql = "SELECT m FROM menu_section03 m";
        List<Menu> menuList  = entityManager.createQuery(jpql,Menu.class).getResultList();

        Assertions.assertNotNull(menuList);
        EntityTransaction entityTransaction  = entityManager.getTransaction();
        entityTransaction.begin();
        menuList.get(1).setMenuName("test");
        entityTransaction.commit();
        menuList.forEach(System.out::println);
    }
}
