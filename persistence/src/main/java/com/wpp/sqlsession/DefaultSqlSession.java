package com.wpp.sqlsession;


import com.wpp.pojo.Configuration;
import com.wpp.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author by wpp25
 * @Classname DefaultSqlSession
 * @Description
 * @Date 2020/9/24 23:58
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        executor = new SimpleExecutor();
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<Object> list = executor.query(configuration, mappedStatement, params);

        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) throws Exception {
        List<Object> list = selectList(statementId, params);
        if (list.size() == 1) {
            return (T) list.get(0);
        } else {
            throw new RuntimeException("query is empty or query result too many");
        }
    }

    @Override
    public Integer insert(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return executor.insert(configuration,mappedStatement,params);
    }

    @Override
    public Boolean delete(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return executor.updateOrDelete(configuration,mappedStatement,params);
    }

    @Override
    public Boolean update(String statementId, Object... params) throws Exception {
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        return executor.updateOrDelete(configuration,mappedStatement,params);
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 准备参数 1：statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名

                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();
                String statementId = new StringBuilder(className).append(".").append(methodName).toString();


                // 准备参数2：params args
                if (methodName.contains("select") || methodName.contains("find")) {
                    Type genericReturnType = method.getGenericReturnType();
                    // 判断是否进行了 泛型参数化
                    if (genericReturnType instanceof ParameterizedType) {
                        List<Object> list = selectList(statementId, args);
                        return list;
                    }
                    return selectOne(statementId,args);
                } else if (methodName.contains("insert") || methodName.contains("add")) {
                    return insert(statementId,args);
                } else if (methodName.contains("update")) {
                    return update(statementId,args);
                } else if (methodName.contains("delete")) {
                    return delete(statementId,args);
                }

                throw new IllegalArgumentException("please use standard naming");
            }
        });
        return (T) proxyInstance;
    }
}
