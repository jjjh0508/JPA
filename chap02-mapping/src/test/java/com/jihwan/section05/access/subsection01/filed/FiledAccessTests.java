package com.jihwan.section05.access.subsection01.filed;


import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class FiledAccessTests {
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


    /*jpa는 엔티티에 접근할 때 필드에 직접 접근을 하는 방식과  get을 이용하여 접근하는 방식으로 구분된다.*/

    @Test
    void 필드_엑세스_테스트() {
        Member member = new Member();
        member.setMemberId("user");
        member.setMemberNo(1);
        member.setMemberPwd("pass");
        member.setMemberNick("길동");
        member.setPhone("010-2323-2323");
        member.setEmail("eeee@Ddddd");
        member.setEnrollDate(new Date());
        member.setAddress("경기도 김포");
        member.setStatus("Y");
        member.setMemberRole(RoleType.ADMIN);

        entityManager.persist(member); // 이 시점에서 접근
        Member findMember = entityManager.find(Member.class, 1);

        System.out.println(findMember);
    }
}
