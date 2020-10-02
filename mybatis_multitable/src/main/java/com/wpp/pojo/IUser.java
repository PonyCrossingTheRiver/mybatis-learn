package com.wpp.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author by wpp25
 * @Classname User
 * @Description  1对多
 * @Date 2020/9/28 23:40
 */
public class IUser implements Serializable {
    private Integer id;
    private String username;

    private List<Order> orderList;

    @Override
    public String toString() {
        return "IUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", orderList=" + orderList +
                '}';
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
