package com.wpp.mapper;

import com.wpp.pojo.AnnotationUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.mybatis.caches.redis.RedisCache;

import java.util.List;

/**
 * @author by wpp25
 * @Classname AnnotationUserMapper
 * @Description
 * @Date 2020/9/29 23:12
 */
//@CacheNamespace(implementation = PerpetualCache.class)
@CacheNamespace(implementation = RedisCache.class) // 二级缓存
public interface AnnotationUserMapper {


    @Insert("insert into user(id,username) values(#{id},#{username})")
    void addUser(AnnotationUser user);

    @Update("update user set username=#{username} where id = #{id}")
    void updateUser(AnnotationUser updateTestAdd);

    @Delete("delete from user where id = #{id}")
    void deleteUserById(Integer id);

    @Select("select u.id,u.username from user u")
    List<AnnotationUser> selectUser();

    @Select(("select u.id,u.username from user u where id = #{id}"))
    AnnotationUser findUserById(Integer id);



    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "orderList",column = "id",javaType = List.class,
            many = @Many(select = "com.wpp.mapper.AnnotationOrderMapper.findByUid")),
    })
    @Select("select u.id,u.username from user u")
    List<AnnotationUser> findUserAndOrder();

    @Select("select u.id,u.username from user u")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "roleList",column = "id",javaType = List.class,
                    many = @Many(select = "com.wpp.mapper.AnnotationRoleMapper.findByUid"))
    })
    List<AnnotationUser> findUserAndRole();

    @Select("select u.id,u.username from user u")
    List<AnnotationUser> findAll();

}
