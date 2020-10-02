package com.wpp.sqlsession;

import com.wpp.pojo.Configuration;
import com.wpp.pojo.MappedStatement;

import java.util.List;

/**
 * @author by wpp25
 * @Classname Executor
 * @Description
 * @Date 2020/9/25 0:23
 */
public interface Executor {

    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    Integer insert(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    Boolean updateOrDelete(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

}
