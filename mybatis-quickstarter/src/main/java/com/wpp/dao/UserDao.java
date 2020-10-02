package com.wpp.dao;

import com.wpp.pojo.User;

import java.util.List;

/**
 * @author by wpp25
 * @Classname UserDao
 * @Description
 * @Date 2020/9/28 21:56
 */
public interface UserDao {

    /**
     * 查找所有
     * @return
     */
    List<User> findAll();

    /**
     * 根据条件查询
     * @param user
     * @return
     */
    List<User> findByCondition(User user);

    /**
     * 根据id集合查询
     * @param list
     * @return
     */
    List<User> findByIdList(List<Integer> list);
}
