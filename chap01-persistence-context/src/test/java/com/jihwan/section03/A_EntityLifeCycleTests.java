package com.jihwan.section03;

import com.google.protobuf.ValueOrBuilder;
import com.jihwan.section03.Menu;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class A_EntityLifeCycleTests {


    private static EntityManagerFactory managerFactory;

    private  EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        System.out.println("init Factory");
        managerFactory = Persistence.createEntityManagerFactory("jpatest");


    }

    @BeforeEach
    void initManager() {
        entityManager = managerFactory.createEntityManager();
    }



    @AfterAll
    public static void closeFactory() {
        System.out.println("clise Factory");
        managerFactory.close();


    }

    @AfterEach
    void closeManager() {
        entityManager.close();
    }


    @Test
    void 비영속성_테스트() {
        Menu foundMenu = entityManager.find(Menu.class, 11);

        Menu newMenu = new Menu();
        newMenu.setMenuCode(foundMenu.getMenuCode());
        newMenu.setMenuName(foundMenu.getMenuName());
        newMenu.setMenuPrice(foundMenu.getMenuPrice());
        newMenu.setCategoryCode(foundMenu.getCategoryCode());
        newMenu.setOrderableStatus(foundMenu.getOrderableStatus());


        boolean result = foundMenu == newMenu; // 주소값이 다르다.
        // 클래스 == 동등 비교시 주소값으로 비교한다.


        Assertions.assertFalse(result);
    }

    @Test
    public void 영속성_연속_조회_테스트() {
        //주소 값이 같다
        //
        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 11);

        boolean isTrue = (foundMenu1 == foundMenu2);

        Assertions.assertTrue(isTrue);
    }

    @Test
    void 영속성_객체_추가_테스트() {

        Menu menuToRegist = new Menu();

        menuToRegist.setMenuCode(500);
        menuToRegist.setMenuName("수박죽");
        menuToRegist.setMenuPrice(10000);
        menuToRegist.setCategoryCode(1);
        menuToRegist.setOrderableStatus("Y");

        entityManager.persist(menuToRegist);

        Menu foundMenu = entityManager.find(Menu.class, 500);

        boolean isTrue = (menuToRegist == foundMenu);
        Assertions.assertTrue(isTrue);

    }

    @Test
    void 영속성_객체_추가_변경_테스트() {

        Menu menuToRegist = new Menu();

        menuToRegist.setMenuCode(500);
        menuToRegist.setMenuName("수박죽");
        menuToRegist.setMenuPrice(10000);
        menuToRegist.setCategoryCode(1);
        menuToRegist.setOrderableStatus("Y");

        entityManager.persist(menuToRegist);

        Menu foundMenu = entityManager.find(Menu.class, 500);

        Assertions.assertEquals(menuToRegist,foundMenu);

    }

    @Test
    void 준영속성_detach_테스트() {
        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 12);
        // 참조를 중단함
        //준 영속은 delete 시키지 않는다
        // remove는 커밋시 delete 시킨다
        entityManager.detach(foundMenu2);
        foundMenu1.setMenuPrice(5000); //영속
        foundMenu2.setMenuPrice(5000); // 준영속

        //다시 영속으로 변환
        entityManager.persist(foundMenu2);


        //true
        Assertions.assertEquals(5000,entityManager.find(Menu.class,11).getMenuPrice());

        //false
        Assertions.assertEquals(5000,entityManager.find(Menu.class,12).getMenuPrice());
    }


    @Test
    public void 준영속_clear_테스트() {
        Menu foundMenu1 = entityManager.find(Menu.class, 11);
        Menu foundMenu2 = entityManager.find(Menu.class, 12);
        // 참조를 중단함
        //준 영속은 delete 시키지 않는다
        // remove는 커밋시 delete 시킨다


        //persits를 초기화
        entityManager.clear();
        //엔터티를 값을 변경함
        foundMenu1.setMenuPrice(5000); // 준영속
        foundMenu2.setMenuPrice(5000); // 준영속

        //다시 영속으로 변환
//

        //참으로 하곳자 하는 경우 다시 영속화
//        entityManager.persist(foundMenu1);
//        entityManager.persist(foundMenu2);





        Assertions.assertEquals(5000,entityManager.find(Menu.class,11).getMenuPrice());
        Assertions.assertEquals(5000,entityManager.find(Menu.class,12).getMenuPrice());
    }

    @Test
    void 삭제_remove_테스트() {
        Menu found = entityManager.find(Menu.class, 1);
        entityManager.remove(found);

        //쿼리 실행 안함  -   존재하지 않는 값이라고 인식하고 실행안함
        Menu reFound = entityManager.find(Menu.class, 1);

        Assertions.assertEquals(1,found.getMenuCode());
        Assertions.assertEquals(null,reFound);
    }


    @Test
    void 병합_merge_수정_테스트() {
        Menu menuToDetch = entityManager.find(Menu.class, 2);
        entityManager.detach(menuToDetch);
        menuToDetch.setMenuName("달콤 꿀밤");
        Menu refound = entityManager.find(Menu.class, 2);

        System.out.println("menuToDetch"+menuToDetch.hashCode());
        System.out.println("refound : "+refound.hashCode());
        // 영속성에 등록된 곳에 덮음
        entityManager.merge(menuToDetch);

        Menu mergeMenu = entityManager.find(Menu.class, 2);
        System.out.println(mergeMenu.hashCode());
        System.out.println("mergeMenu value" + mergeMenu );
        Assertions.assertEquals("달콤 꿀밤",mergeMenu.getMenuName());

    }


    @Test
    void 병합_merge_삽입_테스트() {
        Menu menuToDetach = entityManager.find(Menu.class, 2);
        //준 영속
        entityManager.detach(menuToDetach);
        //바꿔주고
        menuToDetach.setMenuCode(999);

        menuToDetach.setMenuName("수박죽");

        // 999이기 때문에 병합 대상이 없음 그럼 삽입됨
        entityManager.merge(menuToDetach);
        //DB에 인설트 됨
        //근데 커밋 안해서 컨텍스트에 있는 값 가져옴 ? 그래서 다른 해시코드

        Menu merMenu = entityManager.find(Menu.class, 999);
        Assertions.assertEquals("수박죽",merMenu.getMenuName());


    }
}
