package com.wpp.mapper;

import com.wpp.pojo.Order;

import java.util.List;

/**
 * @author by wpp25
 * @Classname OrderMapper
 * @Description
 * @Date 2020/9/28 23:44
 */
public interface OrderMapper {

    /**
     * 查询所有订单
     * @return
     */
    List<Order> findAll();

}
