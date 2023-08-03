package com.jihwan.section01.entity;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class EntityMappingTests {

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
    public void 테이블_만들기_테스트() {
        Member one = entityManager.find(Member.class, 1);
        System.out.println(one);
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass");
        member.setNickname("홍길동");
        member.setAddress("경기도 김포");
        member.setEnrollDate(new Date());
        member.setStatus("Y");

        entityManager.persist(member); // 쓰기 지연상태

//        EntityTransaction entityTransaction =entityManager.getTransaction();
//        entityTransaction.begin();
//

        Member fouMember = entityManager.find(Member.class, member.getMemberNo());

        Assertions.assertEquals(member.getMemberNo(), fouMember.getMemberNo());

    }


}
