package com.wpp.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.lang.annotation.Annotation;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author by wpp25
 * @Classname MySqlPagingPlugin
 * @Description
 *  Intercepts 注意看这个大花括号，也就这说这里可以定义多个@Signature对多个地方拦截，都用这
 * 个拦截器
 * 这
 *
 *  Signature (type = StatementHandler .class, / / 这是指拦截哪个接口
 *      method = " prepare " ， / / 这个接口内的哪个方法名 ， 不要拼错了
 *      args = { Connection.class, Integer .class }),//// 这是拦截的方法的入参，按顺
 * 序写到这，不要多也不要少，如果方法重载，可是要通过方法名和入参来确定唯一的
 * })
 * @Date 2020/9/30 21:21
 */
@Intercepts({
        @Signature(type = StatementHandler.class,method = "prepare",args = {Connection.class,Integer.class})
})
public class MySqlPagingPlugin implements Interceptor {


    /**
     *  拦截方法：只要被拦截的目标对象的目标方法被执行 每次都会执行intercept方法
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("对原有方法进行拦截增强。。。");

        return invocation.proceed();
    }

    /**
     * 主要为了把当前的拦截器生成代理存到拦截器链中
     * @param o
     * @return
     */
    @Override
    public Object plugin(Object o) {
        Object wrap = Plugin.wrap(o, this);
        return wrap;
    }

    /**
     * 获取配置文件的参数
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("获取到配置文件的参数配置: " + properties);

    }
}
