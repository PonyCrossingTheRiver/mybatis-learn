package com.wpp.mapper;

import com.wpp.pojo.IUser;
import com.wpp.pojo.IUserRole;

import java.util.List;

/**
 * @author by wpp25
 * @Classname UserMapper
 * @Description
 * @Date 2020/9/28 23:29
 */
public interface UserMapper {
    /**
     * 1对多
     * @return
     */
    List<IUser> findAll();

    /**
     * 多对多
     * @return
     */
    List<IUserRole> findAllUserAndRole();

}
