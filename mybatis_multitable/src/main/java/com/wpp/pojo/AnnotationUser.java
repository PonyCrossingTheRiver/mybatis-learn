package com.wpp.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author by wpp25
 * @Classname AnnotationUser
 * @Description
 * @Date 2020/9/29 23:05
 */
public class AnnotationUser implements Serializable {
    private Integer id;
    private String username;

    public AnnotationUser(Integer id) {
        this.id = id;
    }

    public AnnotationUser(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    private List<Order> orderList;
    private List<Role> roleList;

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

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "AnnotationUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", orderList=" + orderList +
                ", roleList=" + roleList +
                '}';
    }
}
