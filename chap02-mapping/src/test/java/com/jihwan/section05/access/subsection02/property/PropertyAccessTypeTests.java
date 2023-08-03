package com.jihwan.section05.access.subsection02.property;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PropertyAccessTypeTests {
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

    /*
     * jpa 접근 방식
     * 필드 :
     * get :
     *
     * */

    @Test
    void 프로퍼티_접근_테스트() {
        Member newMember = new Member();
        newMember.setMemberNo(1);
        newMember.setMemberName("전지환");
        newMember.setNickName("지환");
        newMember.setAddress("김포");
        System.out.println("1");
        entityManager.persist(newMember); // 쓰기 지연 상태 sql 쿼리를 생성함  해당 시점에서 필드에 접근을 하게 된다.

        System.out.println("2");
        Member findMember = entityManager.find(Member.class, 1);
        System.out.println(3);
        System.out.println("new Member " + newMember);
        System.out.println("find Member " + findMember);
    }
}
