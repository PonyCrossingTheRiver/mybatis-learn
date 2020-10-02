package com.wpp.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wpp.mapper.*;
import com.wpp.pojo.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author by wpp25
 * @Classname MybatisTest
 * @Description
 * @Date 2020/9/28 23:14
 */
public class MybatisTest {

    private SqlSession sqlSession;
    private OrderMapper orderMapper;
    private UserMapper userMapper;

    private AnnotationUserMapper annotationUserMapper;
    private AnnotationOrderMapper annotationOrderMapper;

    @Before
    public void readyTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapperConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = build.openSession();
        orderMapper = sqlSession.getMapper(OrderMapper.class);
        userMapper = sqlSession.getMapper(UserMapper.class);
        annotationUserMapper = sqlSession.getMapper(AnnotationUserMapper.class);
        annotationOrderMapper = sqlSession.getMapper(AnnotationOrderMapper.class);
    }
    @After
    public void complete() {
        sqlSession.close();
    }

    /**
     *  1对1
     * @throws IOException
     */
    @Test
    public void test1() throws IOException {
        List<Order> orderList = orderMapper.findAll();
        orderList.forEach(System.out::println);
    }

    /**
     * 1对多
     */
    @Test
    public void test2() throws IOException {
        List<IUser> userList = userMapper.findAll();
        userList.forEach(System.out::println);
    }

    /**
     * 多对多
     */
    @Test
    public void test3() throws IOException {
       List<IUserRole> userRoleList =  userMapper.findAllUserAndRole();
        userRoleList.forEach(System.out::println);
    }

    /************************注解测试***********************/

    @Test
    public void test4AddUser() throws IOException {
        annotationUserMapper.addUser(new AnnotationUser(7,"testAdd"));
        sqlSession.commit();
    }

    @Test
    public void test5UpdateUser() throws IOException {
        annotationUserMapper.updateUser(new AnnotationUser(7,"updateTestAdd"));
        sqlSession.commit();
    }

    @Test
    public void test6DeleteUser() throws IOException {
        annotationUserMapper.deleteUserById(7);
        sqlSession.commit();
    }
    @Test
    public void test7SelectOneUser() throws IOException{
        annotationUserMapper.selectUser().forEach(System.out::println);
    }


    /************************注解测试 多表查询 ***********************/

    @Test
    public void test8_one2one() throws IOException {
        List<AnnotationOrder> list = annotationOrderMapper.findOrderAndUser();
        list.forEach(System.out::println);
    }

    @Test
    public void test9_one2Many() throws IOException {
       List<AnnotationUser> list = annotationUserMapper.findUserAndOrder();
        list.forEach(System.out::println);
    }

    @Test
    public void test10_many2many() throws IOException {
        List<AnnotationUser>  list = annotationUserMapper.findUserAndRole();
        list.forEach(System.out::println);
    }

    @Test
    public void tkMapperTest() throws IOException {
        TkMapperTest tkMapperTest = sqlSession.getMapper(TkMapperTest.class);
        TkUser tkUser = tkMapperTest.selectOne(new TkUser(1));
        System.out.println(tkUser);

        Example example = new Example(TkUser.class);
        example.createCriteria().andEqualTo("id",1);
        List<TkUser> tkUsers = tkMapperTest.selectByExample(example);
        tkUsers.forEach(System.out::println);
    }

    @Test
    public void pageTest() throws IOException {
        PageHelper.startPage(1,2);
        List<AnnotationUser> list = annotationUserMapper.findAll();
        list.forEach(System.out::println);

        PageInfo<AnnotationUser> iUserPageInfo = new PageInfo<>(list);
        System.out.printf("总条数： %d %n",iUserPageInfo.getTotal());
        System.out.printf("总页数： %d %n",iUserPageInfo.getSize());
        System.out.printf("当前页:  %d %n",iUserPageInfo.getPageNum());
        System.out.printf("每页条数: %d %n",iUserPageInfo.getPageSize());

    }
}
