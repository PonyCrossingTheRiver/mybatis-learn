package com.wpp.dao;

import com.wpp.pojo.User;

import java.util.List;

/**
 * @author by wpp25
 * @Classname UserDao
 * @Description
 * @Date 2020/9/23 23:41
 */
public interface UserDao {

    List<User> selectList();

    User selectOne(User user);


    Integer insert(User user);

    Boolean update(User user);

    Boolean delete(User user);

}
