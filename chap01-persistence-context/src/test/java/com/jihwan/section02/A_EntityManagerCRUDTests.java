package com.jihwan.section02;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.Member;

public class A_EntityManagerCRUDTests {

    private static EntityManagerFactory managerFactory;

    private static EntityManager entityManager;

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
    public void 메뉴코드로_메뉴_조회_테스트() {
        int menuCode = 2;

        Menu foundMenu = entityManager.find(Menu.class,menuCode);

        Assertions.assertNotNull(foundMenu);
        Assertions.assertEquals(menuCode,foundMenu.getMenuCode());
        System.out.println("foundMenu : "+foundMenu);
    }


    @Test
    void 새로운_메뉴_추가_테스트() {
        Menu menu = new Menu(); //비영속성


        menu.setMenuName("제로 사이다");
        menu.setMenuPrice(1000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();


        try {
            entityManager.persist(menu); //영속성
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }

    }


    @Test
    void 메뉴_이름_수정_테스트() {
        //최초 상태
        Menu menu = entityManager.find(Menu.class, 23);
        System.out.println("menu : " + menu);

        int price = 8400;
        String name = "민영 사이다";

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        //메뉴 이름이 변경된다.(변경 대상: 인스턴스)
        menu.setMenuName(name);

        //
        menu.setMenuPrice(price);



        try {
            //데이터 베이스에 업데이트 쿼리를 실행 후 저장함
            entityTransaction.commit();

        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }

        Assertions.assertEquals(menu, entityManager.find(Menu.class,23));

    }

    @Test
    void 메뉴_삭제_테스트() {
        Menu menu = entityManager.find(Menu.class, 24);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();


        try {
            //delete 영속성에서 제거
            entityManager.remove(menu);
            //
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
        Menu removemenu = entityManager.find(Menu.class, 24);
        Assertions.assertEquals(null,removemenu);
    }
}
