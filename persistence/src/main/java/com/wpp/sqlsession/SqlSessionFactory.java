package com.wpp.sqlsession;

/**
 * @author by wpp25
 * @Classname SqlSessionFactory
 * @Description
 * @Date 2020/9/24 20:51
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
