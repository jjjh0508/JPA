package com.jihwan.section05.groupfunction;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.List;

public class GroupFunctionTests {
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
    void 특정_카테고리의_등록된_메뉴_수_조회() {

        int categoryCodeParameter = 4;

        String jpql = "SELECT COUNT(m.menuPrice) FROM menu_section05 m WHERE m.categoryCode = :categoryCode";
        long conutOfMenu = entityManager.createQuery(jpql, Long.class).setParameter("categoryCode", categoryCodeParameter).getSingleResult();

        Assertions.assertTrue(conutOfMenu>=0);
        System.out.println(conutOfMenu);

    }


    @Test
    void count를_제외한_다른_그룹함수의_조회결과가_없는_경우_테스트() {
        int categoryCodeParameter = 2;

        String jpql = "SELECT SUM(m.menuPrice) FROM menu_section05 m WHERE m.categoryCode = :categoryCode";
        Assertions.assertThrows(NullPointerException.class,()->{

            //에러체크
            long sumOfPrice = entityManager.createQuery(jpql,Long.class).setParameter("categoryCode",categoryCodeParameter).getSingleResult();
            System.out.println(sumOfPrice);
        });

        //에러가 아닌 null 체크
        Assertions.assertDoesNotThrow(()->{
            Long sumOfPrice = entityManager.createQuery(jpql,Long.class)
                    .setParameter("categoryCode",categoryCodeParameter).getSingleResult();
            System.out.println(sumOfPrice);
        });
    }


    @Test
    void groupby절과_having절을_사용한_조회_테스트() {
        long minPrice = 50000L;

        String jpql = "SELECT m.categoryCode, SUM(m.menuPrice) FROM menu_section05 m GROUP BY m.categoryCode HAVING SUM(m.menuPrice) >= :minPrice";

        List<Object[]> sumPriceOfCategoryList = entityManager.createQuery(jpql,Object[].class).setParameter("minPrice",minPrice).getResultList();

        Assertions.assertNotNull(sumPriceOfCategoryList);

        sumPriceOfCategoryList.forEach(row->{
            Arrays.stream(row).forEach(System.out::println);
        });
    }
}
