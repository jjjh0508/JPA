package com.jihwan.section06.compositekey.subsection01.embadded;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmbeddedKeyTests {

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
    * 아래의 방식은 @GeneratedValue 어노테이션을 이용하여 키를 자동으로 생성하는 것이 불가능 하다.
    *@IdClass , @Embedded 두 가지 방식 모두 영속성 컨텍스트에서 관리하지 않는다.
    *
    * */
    @Test
    public void 임베디드_아이디_사용한_복합키_테이블_매핑_테스트() {
        Member  member = new Member();
        member.setMemberPk(new MemberPk(1,"user1"));
        member.setPhone("010-2222-4444");
        member.setAddress("김포");
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityTransaction.commit();


        Member findMember = entityManager.find(Member.class,member.getMemberPk());
        Assertions.assertEquals(member.getMemberPk(),findMember.getMemberPk());

    }
}
