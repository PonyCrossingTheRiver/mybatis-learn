package com.wpp.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author by wpp25
 * @Classname TkUser
 * @Description
 * @Date 2020/10/1 20:04
 */
@Table(name = "user")
public class TkUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;

    public TkUser(Integer id) {
        this.id = id;
    }

    public TkUser(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TkUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
