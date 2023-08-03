package com.jihwan.section03.primarykey.tables;

import com.jihwan.section03.primarykey.tables.Member;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.List;

public class SequnceTableMappingTests {
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
    void 식별자_매핑_테스트() {
        Member member = new Member();
        member.setMemberId("user");
        member.setMemberPwd("pass");
        member.setMemberNick("길동");
        member.setPhone("010-2323-2323");
        member.setEmail("eeee@Ddddd");
        member.setEnrollDate(new Date());
        member.setAddress("경기도 김포");
        member.setStatus("Y");
        member.setMemberRole("ROLE_MEMBER");


        Member member1 = new Member();
        member1.setMemberId("user1");
        member1.setMemberPwd("pass1");
        member1.setMemberNick("길똥");
        member1.setPhone("010-2323-2424");
        member1.setEmail("e1111@Ddddd");
        member1.setEnrollDate(new Date());
        member1.setAddress("경기도 김포");
        member1.setStatus("Y");
        member1.setMemberRole("ROLE_MEMBER");

        System.out.println(member);
        System.out.println(member1);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityManager.persist(member1);
        entityTransaction.commit();

        String jpql = "SELECT A.memberNo from member_section03_subsection02 A";

        List<Integer> memberNoList = entityManager.createQuery(jpql,Integer.class).getResultList();

        memberNoList.forEach(System.out::println);


    }
}
