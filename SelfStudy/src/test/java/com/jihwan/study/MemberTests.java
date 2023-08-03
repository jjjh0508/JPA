package com.jihwan.study;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MemberTests {

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
    @Order(1)
//    @DisplayName("회원 테이블 등록및 테스트")
    void 회원_생성_테스트() {
        //given
        Member member = new Member();
        member.setName("전지환");
        member.setNickName("지환");
        member.setAddressNum("10068");
        member.setAddress("경기도 김포");
        member.setDetailAddress("우리집 아파트");
        member.setRoleType(RoleType.MEMBER);
        member.setCreateDate(new Date());
        member.setStatus("Y");

        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(member);

        try {
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            e.printStackTrace();
        }
        //then
        Member findMember = entityManager.find(Member.class, 1);
        Assertions.assertEquals(member.getMemberNo(), findMember.getMemberNo());

    }


    @Test
    @Order(2)
    void 회원_조회_테스트() {
        Member findMember = entityManager.find(Member.class, 1);


        Assertions.assertNotNull(findMember);
    }

    @Test
    @Order(3)
    void 회원_삭제_테스트() {
        //given
        Member Member = entityManager.find(Member.class, 1);
        //when
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(Member);
        entityTransaction.commit();
        //then
        Member findMember = entityManager.find(Member.class, 1);

        Assertions.assertNull(findMember);
    }

}
