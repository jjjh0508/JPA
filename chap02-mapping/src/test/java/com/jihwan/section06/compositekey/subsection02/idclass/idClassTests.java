package com.jihwan.section06.compositekey.subsection02.idclass;


import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class idClassTests {
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
    * 1. @Embeddable  : 복합키를 정의한 클래스에 설정한 뒤 @EmbeddedId 를 이용하여 복합키 클래스를 매핑한다.
    * 2. @IdClass : 복합키를 필드로 정의한 클래스를 이용하여 엔티티 클래스에 복합키에 해당하는 필드에 @Id를 매핑한다.
    *
    * 두가지 방식에는 문법상의 차이가 존재하지만 기능적으로 큰차이는 존재하지 않는다.
    * 다만 @Embeddable를 이용하는 방법이 보다 객체지향 적이라도 할 수 있으며
    * @IdClass를 이용한 방법은 관계형 데이터베이스에 가깝다고 불 수 있다.
    *
    *
    * */

    @Test
    void 반성하는_아이디클래스_사용한_복합키_테이블_매핑_테스트() {
        // given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("반성좀 할게요");
        member.setPhone("010-2222-4444");
        member.setAddress("김포");

        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);
        entityTransaction.commit();

        //then
        Member findMember = entityManager.find(Member.class, new MemberPk(1,"반성좀 할게요"));
        Assertions.assertEquals(member,findMember);

    }
}
