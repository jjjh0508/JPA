package com.jihwan.section03.projection;

import javax.persistence.*;
import java.util.List;

@Entity(name = "bidirection_category")
@Table(name = "tbl_category")
public class BiDriectionCategory {

    @Id
    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "ref_category_code")
    private int refCategoryCode;

    @OneToMany(mappedBy = "category")
    private List<BiDriectionMenu> menuList;


    public BiDriectionCategory() {
    }

    public BiDriectionCategory(int categoryCode, String categoryName, int refCategoryCode, List<BiDriectionMenu> menuList) {
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

    public int getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(int refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public List<BiDriectionMenu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<BiDriectionMenu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public String toString() {
        return "BiDriectionCategory{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode + '}';
    }
}
