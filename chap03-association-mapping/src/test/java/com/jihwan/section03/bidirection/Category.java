package com.jihwan.section03.bidirection;



/*
* 양방향 매핑에서는 어느 한쪽이 연관 관계의 주인이 되면 주인이 아닌 쪽에서는 속성을 지정해주어야한다.
* 이때, 연관 관계의 주인이 아닌 객체에 mappedBy를 써서 연관 관계의 주인 객페의 필드명을 매핑 시켜놓으면 양방향 관계를 적용할 수 있다.
* */




import javax.persistence.*;
import java.util.List;

@Entity(name = "bidirection_category")
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private Integer refCategoryCode;

    @OneToMany(mappedBy = "category") // mappedBy 설정 카테고리 조회시 제외하지만  List<Menu> menuList = category.getMenuList()
    private List<Menu> menuList;     // System.out.println(menuList) 이때 호출하는 시점에 조회한다.


    public Category() {
    }

    public Category(int categoryCode, String categoryName, Integer refCategoryCode, List<Menu> menuList) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
        this.menuList = menuList;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                ", menuList=" + menuList +
                '}';
    }
}
