package com.jihwan.section03.bidirection;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class BiDirectionTests {

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
    public void 양방향_연관관계_매핑_조회_테스트() {

        int menuCode = 10;
        int categoryCode = 10;
        //연관관계 주인  // 주인은 속해있는거 다 조회
        Menu realkey = entityManager.find(Menu.class, menuCode);
        // 가짜
        Category falseKey = entityManager.find(Category.class, categoryCode);
        //여기선 메뉴를 조회하지 않음 ?

        Assertions.assertEquals(menuCode,realkey.getMenuCode());
        Assertions.assertEquals(categoryCode,falseKey.getCategoryCode());

        System.out.println(realkey);
        System.out.println(falseKey);


    }

    @Test
    void 양방향_연관관계_주인_객체를_이용한_삽입_테스트() {

        Menu menu = new Menu();
        menu.setMenuCode(126);
        menu.setMenuName("연관관계 주인메뉴");
        menu.setMenuPrince(1000);
        menu.setOrderableStatus("Y");

        menu.setCategory(entityManager.find(Category.class,4)); // 주인이 아니기 때문에 카테고리만 조회
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(menu);
        entityTransaction.commit();

        Menu findMenu1 = entityManager.find(Menu.class, menu.getMenuCode());
        Assertions.assertEquals(menu.getMenuCode(), findMenu1.getMenuCode());
        System.out.println(findMenu1.getCategory());

    }

    @Test
    void 양방향_연관관계_주인이_아닌_객체를_이용한_삽입_테스트() {

        Category category = new Category();
        category.setCategoryCode(1005);
        category.setCategoryName("양뱡향 카테고리");
        category.setRefCategoryCode(null);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityManager.persist(category);
        entityTransaction.begin();
        entityTransaction.commit(); // 카테고리(menuList제외) 인설트만 작성됨

        Category findCategory1 = entityManager.find(Category.class, category.getCategoryCode());
        Assertions.assertEquals(category.getCategoryCode(), findCategory1.getCategoryCode());


    }

    @Test
    public void test() {
        // 연관관계의 주인
        Menu menu = entityManager.find(Menu.class, 10);
        // 연관관계의 주인이 아니다
        Category category = entityManager.find(Category.class, 9);

        List<Menu> menuList = category.getMenuList();
        System.out.println(menuList); //mappedBy 이때 메뉴 리스트  select 쿼리가 돈다

    }
}
