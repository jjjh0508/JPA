package com.jihwan.section03.bidirection;

import javax.persistence.*;

@Entity(name = "bidirection_menu")
@Table(name = "tbl_menu")
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrince;


    @ManyToOne
    @JoinColumn(name = "category_code")
    private Category category;
    /*
    * ManyToOne
    * 1(현재클래스):n(참조되는 컬럼)의 경우
    * 메뉴랑 카테고리
    * n  : 1 ?
    *
    * OneToMany
    * 메뉴와 카테고리
    * n : 1
    * */


    @Column(name = "orderable_status")
    private String orderableStatus;


    public Menu() {
    }

    public Menu(int menuCode, String menuName, int menuPrince, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrince = menuPrince;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getMenuPrince() {
        return menuPrince;
    }

    public void setMenuPrince(int menuPrince) {
        this.menuPrince = menuPrince;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }

//    @Override
//    public String toString() {
//        return "Menu{" +
//                "menuCode=" + menuCode +
//                ", menuName='" + menuName + '\'' +
//                ", menuPrince=" + menuPrince +
//                ", category=" + category +
//                ", orderableStatus='" + orderableStatus + '\'' +
//                '}';
//    }
}
