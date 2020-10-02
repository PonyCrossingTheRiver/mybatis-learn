package com.wpp.sqlsession;

import com.wpp.conifg.XMLConfigBuilder;
import com.wpp.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author by wpp25
 * @Classname SqlSessionFactoryBuilder
 * @Description
 * @Date 2020/9/24 20:50
 */
public class SqlSessionFactoryBuilder {


    public static SqlSessionFactory build(InputStream inputStream) throws DocumentException, PropertyVetoException {
        // 1、使用dom4j 解析配置文件，将解析出来的内容封装到Configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);

        // 创建sqlSessionFactory对象： 工厂类 用来生产sqlSession会话 对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return defaultSqlSessionFactory;
    }

}
