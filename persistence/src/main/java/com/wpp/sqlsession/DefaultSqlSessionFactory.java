package com.wpp.sqlsession;

import com.wpp.pojo.Configuration;

/**
 * @author by wpp25
 * @Classname DefaultSqlSessionFactory
 * @Description
 * @Date 2020/9/25 0:01
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
