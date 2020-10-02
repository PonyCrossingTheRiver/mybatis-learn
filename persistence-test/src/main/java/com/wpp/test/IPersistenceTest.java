package com.wpp.test;

import com.wpp.dao.UserDao;
import com.wpp.io.Resources;
import com.wpp.pojo.User;
import com.wpp.sqlsession.SqlSession;
import com.wpp.sqlsession.SqlSessionFactory;
import com.wpp.sqlsession.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author by wpp25
 * @Classname IPersistenceTest
 * @Description
 * @Date 2020/9/26 0:21
 */
public class IPersistenceTest {

    private SqlSession sqlSession;

    @Before
    public void before() throws Exception {
        InputStream inputStream = Resources.getResourceAsSteam("sqlMapperConfig.xml");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test () throws Exception {

        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        User userDatabase = sqlSession.selectOne("com.wpp.dao.UserDao.selectOne", user);
        System.out.println(userDatabase);
        System.out.println("-----------");
        List<User> list = sqlSession.selectList("com.wpp.dao.UserDao.selectList");
        list.stream().forEach(System.out::print);
    }

    @Test
    public void mapperWaySelectTest() {
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(1);
        user.setUsername("张三");
        User user1 = mapper.selectOne(user);
        System.out.println(user1);
    }
    @Test
    public  void mapperWayInsertTest() {
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(13);
        user.setUsername("testAdd11");
        mapper.insert(user);
    }
    @Test
    public  void mapperWayUpdateTest() {
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(11);
        user.setUsername("testAdd33");
        mapper.update(user);
    }
    @Test
    public  void mapperWayDeleteTest() {
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
        user.setId(13);
        user.setUsername("testAdd11");
        mapper.delete(user);
    }
}
