package com.jihwan.study;

import javax.persistence.*;

@Entity(name = "study_product")
@Table(name = "stu_product")
@Access(AccessType.PROPERTY)
public class Product {


    @Column(name = "product_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productNum;

    @Column(name = "product_name" ,unique = true)
    private String productName;
    @Column(name = "product_price")
    private String productPrice;

    public Product() {
    }

    public Product(int productNum, String productName, int productPrice) {
        this.productNum = productNum;
        this.productName = productName;
        this.setProductPrice(productPrice);
    }

    @Id
    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }


    public void setProductPrice(int productPrice) {

        this.productPrice = productPrice+"원";
    }

    @Override
    public String toString() {
        return "Product{" +
                "productNum=" + productNum +
                ", productName='" + productName + '\'' +
                ", productPrice='" + productPrice + '\'' +
                '}';
    }
}
