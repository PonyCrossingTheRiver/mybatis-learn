package com.wpp.sqlsession;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * @author by wpp25
 * @Classname SqlSession
 * @Description
 * @Date 2020/9/24 23:29
 */
public interface SqlSession {


    /**
     * 查询所有
     * @param statementId
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    <E> List<E> selectList(String statementId, Object... params) throws Exception;

    /**
     * 查询一个
     * @param statementId
     * @param params
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T selectOne(String statementId, Object... params) throws Exception;

    /**
     *
     * @param statementId
     * @param params
     * @return
     * @throws Exception
     */
    Integer insert(String statementId,Object...params) throws Exception;

    /**
     *
     * @param statementId
     * @param params
     * @return
     * @throws Exception
     */
    Boolean delete(String statementId, Object...params) throws Exception;

    /**
     *
     * @param statementId
     * @param params
     * @return
     * @throws Exception
     */
    Boolean update(String statementId,Object...params) throws Exception;


    /**
     * Dao 接口的代理实现类
     * @param mapperClass
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<?> mapperClass);
}
