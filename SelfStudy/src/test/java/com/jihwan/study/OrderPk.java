package com.jihwan.study;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderPk  implements Serializable {

    @Column(name = "order_num")
    private int orderNum;

    @Column(name = "product_num")
    private int productNum;

    public OrderPk() {
    }

    public OrderPk(int orderNum, int productNum) {
        this.orderNum = orderNum;
        this.productNum = productNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    @Override
    public String toString() {
        return "OrderPk{" +
                "orderNum=" + orderNum +
                ", productNum=" + productNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPk orderPk = (OrderPk) o;
        return orderNum == orderPk.orderNum && productNum == orderPk.productNum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNum, productNum);
    }
}
