package com.jihwan.section08.namedquery;


import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.jar.JarEntry;

public class NamedQueryTests {
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
    public void 동적_쿼리를_이용한_조회_테스트() {
        String serachName = "한우";
        int searchCategoryCode = 4;

        StringBuilder jpql = new StringBuilder("SELECT m FROM menu_section08 m ");
        if (serachName != null && !serachName.isEmpty() && searchCategoryCode > 0) {
            jpql.append("WHERE ");
            jpql.append("m.menuName LIKE '%' || :menuName || '%'");
            jpql.append("AND ");
            jpql.append("m.categoryCode = :categoryCode ");
        }else {
            if (serachName != null && !serachName.isEmpty()) {
                jpql.append("WHERE ");
                jpql.append("m.menuName LIKE '%' || :menuName || '%'");

            } else if (searchCategoryCode > 0) {
                jpql.append("WHERE ");
                jpql.append("m.categoryCode = :categoryCode ");
            }
        }

        TypedQuery<Menu> query = entityManager.createQuery(jpql.toString(), Menu.class);

        if (serachName != null && !serachName.isEmpty() && searchCategoryCode > 0) {
            query.setParameter("menuName",serachName)
                    .setParameter("categoryCode",searchCategoryCode);
        }else {
            if(serachName != null && !serachName.isEmpty()){
                query.setParameter("menuName",serachName);
            }else if(searchCategoryCode>0){
                query.setParameter("categoryCode",searchCategoryCode);
            }
        }

        List<Menu> menuList = query.getResultList();
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }


    @Test
    void 네임드쿼리를_이용한_조회_테스트() {

        List<Menu> menuList = entityManager.createNamedQuery("menu_section08.selectMenuList",Menu.class).getResultList();

        Assertions.assertNotNull(menuList);

        menuList.forEach(System.out::println);
    }
}
