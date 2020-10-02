package com.wpp.mapper;

import com.wpp.pojo.Role;
import org.apache.ibatis.annotations.Select;

/**
 * @author by wpp25
 * @Classname AnnotationRoleMapper
 * @Description
 * @Date 2020/9/30 0:03
 */
public interface AnnotationRoleMapper {

    @Select("select sr.id,sr.rolename,sr.roledesc from sys_role sr left join sys_user_role sur on sr.id = sur.roleid where sur.userid = #{uid}")
    Role findByUid(Integer uid);
}
