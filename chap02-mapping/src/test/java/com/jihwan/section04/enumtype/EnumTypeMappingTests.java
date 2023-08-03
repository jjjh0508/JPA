package com.jihwan.section04.enumtype;


import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class EnumTypeMappingTests {

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
    void enum타입_매핑_테스트() {
        Member member = new Member();
        member.setMemberId("user");
        member.setMemberPwd("pass");
        member.setMemberNick("길동");
        member.setPhone("010-2323-2323");
        member.setEmail("eeee@Ddddd");
        member.setEnrollDate(new Date());
        member.setAddress("경기도 김포");
        member.setStatus("Y");
        member.setMemberRole(RoleType.ADMIN);


        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityTransaction.commit();






    }

}
