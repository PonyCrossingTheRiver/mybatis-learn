package com.wpp.test;

import com.wpp.mapper.AnnotationUserMapper;
import com.wpp.pojo.AnnotationUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author by wpp25
 * @Classname CacheTest
 * @Description
 * @Date 2020/9/30 20:21
 */
public class CacheTest {

    private AnnotationUserMapper annotationUserMapper;
    private SqlSession sqlSession;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = sqlSessionFactory.openSession();
        annotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);

    }

    @Test
    public void firstCacheTest() throws IOException {
        AnnotationUser user = annotationUserMapper.findUserById(2);
        System.out.println(user);
        sqlSession.clearCache();
        AnnotationUser userById = annotationUserMapper.findUserById(2);
        System.out.println(userById);
        System.out.println(user == userById);

    }

    @Test
    public void secondCacheTest() throws IOException {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        AnnotationUserMapper mapper1 = sqlSession1.getMapper(AnnotationUserMapper.class);
        AnnotationUserMapper mapper2 = sqlSession2.getMapper(AnnotationUserMapper.class);
        AnnotationUserMapper mapper3 = sqlSession3.getMapper(AnnotationUserMapper.class);

        AnnotationUser userById1 = mapper1.findUserById(2);
        System.out.println(userById1);
        sqlSession1.close();

        mapper3.updateUser(new AnnotationUser(2,"testSecondCache"));
        sqlSession3.commit();

        AnnotationUser userById2 = mapper2.findUserById(2);
        System.out.println(userById2);
        System.out.println(userById1 == userById2);
    }
}
