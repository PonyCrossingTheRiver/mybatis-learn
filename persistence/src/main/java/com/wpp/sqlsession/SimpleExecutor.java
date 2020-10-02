package com.wpp.sqlsession;

import com.wpp.conifg.BoundSql;
import com.wpp.pojo.Configuration;
import com.wpp.pojo.MappedStatement;
import com.wpp.utils.GenericTokenParser;
import com.wpp.utils.ParameterMapping;
import com.wpp.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by wpp25
 * @Classname SimpleExecutor
 * @Description
 * @Date 2020/9/25 0:25
 */
public class SimpleExecutor implements Executor {

    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        PreparedStatement preparedStatement = getPreparedStatement(configuration, mappedStatement, params);
        // 执行sql
        ResultSet resultSet = preparedStatement.executeQuery();
        String resultType = mappedStatement.getResultType();
        Class<?> resultTypeClass = getClassType(resultType);
        List<Object> resultList = new ArrayList<>();

        // 封装结果集对象
        while (resultSet.next()) {
            Object resultTypeObject = resultTypeClass.newInstance();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);

                //使用反射或者内省，根据数据库表和实体的对应关系，完成封装
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(resultTypeObject,value);
            }
            resultList.add(resultTypeObject);

        }

        return (List<E>) resultList;
    }

    private PreparedStatement getPreparedStatement(Configuration configuration, MappedStatement mappedStatement, Object... param) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        // 1、注册驱动 获取连接
        Connection connection = configuration.getDataSource().getConnection();

        // 2.  eg ： 获取sql语句 : select * from user where id = #{id} and username = #{username}
        //转换sql语句： select * from user where id = ? and username = ? ，转换的过程中，还需要对#{}里面的值进行解析存储
        String sql = mappedStatement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        // 3.获取预处理对象：preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());

        // 4. 设置参数
        //获取到了参数的全路径
        String paramterType = mappedStatement.getParamterType();

        Class<?> paramterTypeClass = getClassType(paramterType);

        List<ParameterMapping> parameterMappingList = boundSql.getParameterMappingList();
        for (int i = 0; i < parameterMappingList.size(); i++) {
            // 参数值
            String content = parameterMappingList.get(i).getContent();
            Field declaredField = paramterTypeClass.getDeclaredField(content);
            declaredField.setAccessible(true);

            Object o = declaredField.get(param[0]);
            preparedStatement.setObject(i + 1,o);
        }
        return preparedStatement;
    }

    private Class<?> getClassType(String classPath) throws ClassNotFoundException {
        if (classPath != null) {
            Class<?> aClass = Class.forName(classPath);
            return aClass;
        }
        return null;
    }

    /**
     * 完成对sql的解析
     * 将#{} 用？ 替换
     * 解析出#{} 里面的属性值进行存储
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", tokenHandler);
        // 解析出来的sql
        String sqlText = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();
        BoundSql boundSql = new BoundSql(sqlText, parameterMappings);
        return boundSql;
    }


    @Override
    public Integer insert(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        PreparedStatement preparedStatement = getPreparedStatement(configuration, mappedStatement, params);
        int i = preparedStatement.executeUpdate();
        preparedStatement.close();
        return i;
    }
    @Override
    public Boolean updateOrDelete(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception {
        PreparedStatement preparedStatement = getPreparedStatement(configuration, mappedStatement, params);
        boolean execute = preparedStatement.execute();
        return execute;
    }


}
