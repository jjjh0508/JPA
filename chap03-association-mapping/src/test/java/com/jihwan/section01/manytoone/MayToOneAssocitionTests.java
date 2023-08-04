package com.jihwan.section01.manytoone;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class MayToOneAssocitionTests {
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
    public void 다대일_연관관계_객체_그래프_탐색을_이용한_조회_테스트() {
        int menuCode =15;
        //메뉴 15번을 조회
        //조회 컬럼 기준은 객체 필드


        MenuAndCategory findMenu = entityManager.find(MenuAndCategory.class, menuCode);
        //조회한 메뉴에서 카테고리를 가져옴
        Category menuCategory = findMenu.getCategory();
        //카테고리가 널인지
        Assertions.assertNotNull(menuCategory);
        System.out.println(menuCategory);



        /*
        * 관계형 데이터 베이스
        * 아래의 내용은 tbl_menu.category_code에 값은 tbl_category.category_code에서 값을 참조 해야한다는 의미이다.
        * tbl_category.cateogry_code <- tbl_menu.categoryCode
        * fk 제약 조건을 설정하여 join 통해 다른 필드를 참조 할 수 있다.
        *
        * 객체지향 관점
        * 위의 관계형 데이터베이스 처럼 category_code의 값을 안다고 해서 값을 쉽게 찾을 수 있지 않다.
        * 이러한 문제(영상참조)가 두 가지 방식에서 패러다임의 대한 불일치가 발생되는 것인데(리스트로 몇만건을 조회할 경우)
        * 이를 해결 하고자 참좉 설정을 할 수 있다.
        *
        * */
    }

    /*jpql 은 java Persistence Query Longuage의 약자로 , 객체 지향 쿼리 언어 중 하나이다.
    * 객체 지향 모델에 맞게 작성된 쿼리를 통해 , 엔티티를 객체를 대상으로 검색, 검색 결과를 토대로 객체를 조작할 수 있다.
    * join문법이 sql과 다소 차이가 있으나 직접 쿼리를 작성할 수 있는 문법을 제공한다.
    * 주의할 점은 from 절에 기술할 테이블에는 반드시 엔티티 명이 작성되어야한다.
    *
    *
    *
    *  */



    @Test
    public void 다대일_연관관계_객체지향쿼리_사용한_카테고리_이름_조회_테스트() {
        //해당 엔티티를 참고한다. 이떄 @Column의 설정된 name은 관계 없음
        String jpql = "SELECT c.categoryName " +
                "FROM menu_and_category m " +
                "JOIN m.category c WHERE m.menuCode =15";
        String category = entityManager.createQuery(jpql,String.class).getSingleResult();

        Assertions.assertNotNull(category);
        System.out.println(category);

    }


    @Test
    public void 다대일_연관관계_객체_삽입_테스트() {
        MenuAndCategory menuAndCategory = new MenuAndCategory();
        menuAndCategory.setMenuCode(999);
        menuAndCategory.setMenuName("순대국밥");
        menuAndCategory.setMenuPrice(10000);
        menuAndCategory.setOrderableStatus("Y");

        Category category = new Category();
        category.setCategoryCode(3333);
        category.setCategoryName("신규카테");
        category.setRefCategoryCode(null);

        menuAndCategory.setCategory(category);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(menuAndCategory);
        //카테고리를 먼저 등록해주고 메뉴 등록해준다.
        //ManyToOne
        entityTransaction.commit();

        MenuAndCategory findMenuAndCategory = entityManager.find(MenuAndCategory.class, 999);
        Assertions.assertEquals(999, findMenuAndCategory.getMenuCode());
        Assertions.assertEquals(3333,findMenuAndCategory.getCategory().getCategoryCode());
    }

}
