package com.wpp.mapper;

import com.wpp.pojo.AnnotationOrder;
import com.wpp.pojo.AnnotationUser;
import com.wpp.pojo.Order;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author by wpp25
 * @Classname AnnotationOrderMapper
 * @Description
 * @Date 2020/9/29 23:03
 */
public interface AnnotationOrderMapper {



    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderTime",column = "id"),
            @Result(property = "total",column = "id"),
            @Result(property = "annotationUser",column = "uid",javaType = AnnotationUser.class,
                    one = @One(select = "com.wpp.mapper.AnnotationUserMapper.findUserById"))
    })
    @Select("select o.id,o.orderTime,o.total,o.uid from orders o")
    List<AnnotationOrder> findOrderAndUser();

    @Select("select o.id,o.orderTime,o.total,o.uid from orders o where uid = #{uid}")
    Order findByUid(Integer uid);


}
