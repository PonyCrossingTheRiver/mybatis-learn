package com.wpp.test;

import com.wpp.dao.UserDao;
import com.wpp.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * @author by wpp25
 * @Classname MybatisTest
 * @Description
 * @Date 2020/9/28 21:59
 */
public class MybatisTest {

    private SqlSession sqlSession;

    private UserDao userDao;

    @Before
    public void beforeTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 默认开启一个事务 但是不会提交 若 参数为true 则 不用commit了
        sqlSession = sqlSessionFactory.openSession();
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void afterTest() throws Exception{
        sqlSession.close();
    }



    @Test
    public void test1() throws Exception {
        List<User> userList = sqlSession.selectList("com.wpp.dao.UserDao.findAll");
        userList.forEach(System.out::println);
    }

    @Test
    public void test2() throws Exception {
        sqlSession.insert("com.wpp.dao.UserDao.saveUser",new User(5,"tom"));
        test1();
        sqlSession.commit();
    }
    @Test
    public void test3() throws Exception {
        sqlSession.update("com.wpp.dao.UserDao.updateOne",new User(5,"tom1"));
        test1();
        sqlSession.commit();
    }

    @Test
    public void test4() throws Exception {
        sqlSession.delete("com.wpp.dao.UserDao.deleteById",5);
        test1();
        sqlSession.commit();
    }
    /************************************* 映射 dao操作************************/
    @Test
    public void test6() throws Exception {
        List<User> userList = userDao.findByCondition(new User(1,"张三"));
        userList.forEach(System.out::println);

    }
    @Test
    public void test7() throws Exception {
        List<User> userList = userDao.findByIdList(Arrays.asList(1,2,6));
        userList.forEach(System.out::println);
    }
}
