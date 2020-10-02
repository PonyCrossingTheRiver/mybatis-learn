package com.wpp.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author by wpp25
 * @Classname IUserRole
 * @Description
 * @Date 2020/9/29 0:17
 */
public class IUserRole implements Serializable {
    private Integer id;
    private String username;

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

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "IUserRole{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roleList=" + roleList +
                '}';
    }
}
