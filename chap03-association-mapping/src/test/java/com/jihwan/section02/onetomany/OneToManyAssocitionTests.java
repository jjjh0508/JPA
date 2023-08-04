package com.jihwan.section02.onetomany;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class OneToManyAssocitionTests {
   private static EntityManagerFactory managerFactory;
   private EntityManager entityManager;


    @BeforeAll
    public static void initFactory() {
        managerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = managerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        managerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }


    @Test
    public void 일대다_연관관계_객체_그래프_탐색을_이용한_조회_테스트() {

        int categoryCode = 10;
        CategoryAndMenu categoryAndMenu =entityManager.find(CategoryAndMenu.class,categoryCode);

        Assertions.assertNotNull(categoryAndMenu);
        System.out.println(categoryAndMenu);
        /*
        * DB조회
        * 카테고리 코드 + 메뉴 리스트
        *
        * */
    }


    @Test
    void 일대다_연관관계_객체_삽입_테스트() {

        CategoryAndMenu categoryAndMenu = new CategoryAndMenu();
        categoryAndMenu.setCategoryCode(888);
        categoryAndMenu.setCategoryName("일대다 추가 카테고리");
        categoryAndMenu.setRefCategoryCode(null);

        List<Menu> menuList = new ArrayList<>();
        Menu menu = new Menu();
        menu.setMenuCode(777);
        menu.setMenuName("일대다 아이스크림");
        menu.setMenuPrince(150000);
        menu.setOrderableStatus("Y");
        menu.setCategoryCode(categoryAndMenu.getCategoryCode());

        menuList.add(menu);

        categoryAndMenu.setMenuList(menuList);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(categoryAndMenu);
        entityTransaction.commit();

        CategoryAndMenu findCategoryAndMenu = entityManager.find(CategoryAndMenu.class, 888);
        System.out.println(findCategoryAndMenu);
    }
}
