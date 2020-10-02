package com.wpp.pojo;

import java.io.Serializable;

/**
 * @author by wpp25
 * @Classname Order
 * @Description  订单和 用户 一对一
 * @Date 2020/9/28 23:39
 */
public class Order implements Serializable {
    private Integer id;
    private String orderTime;
    private Double total;

    private IUser IUser;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTime='" + orderTime + '\'' +
                ", total=" + total +
                ", IUser=" + IUser +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public IUser getIUser() {
        return IUser;
    }

    public void setIUser(IUser IUser) {
        this.IUser = IUser;
    }
}
