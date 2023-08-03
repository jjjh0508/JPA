package com.jihwan.study;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "study_order_mapping")
@Table(name = "stu_order_mapping")
public class OrderMapping {

    @EmbeddedId
    private OrderPk orderPk;

    public OrderMapping() {
    }

    public OrderPk getOrderPk() {
        return orderPk;
    }

    public void setOrderPk(OrderPk orderPk) {
        this.orderPk = orderPk;
    }

    @Override
    public String toString() {
        return "OrderMapping{" +
                "orderPk=" + orderPk +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMapping that = (OrderMapping) o;
        return Objects.equals(orderPk, that.orderPk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderPk);
    }
}
