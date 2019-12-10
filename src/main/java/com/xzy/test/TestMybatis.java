package com.xzy.test;

import com.xzy.bean.User;
import com.xzy.framworkofmybatis.MySqlSession;
import com.xzy.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * created by xzy on 2019/1/15
 */
public class TestMybatis {

    public static void main(String[] args) throws IOException {
        // 测试我的mybatis
        System.out.println("test mybatis");
        try {
            MySqlSession sqlSession = new MySqlSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.getUserById(1L);
            System.out.println(user.toString());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("catch an exception:" + e);
        }


        System.out.println("--------------------------");


        // 测试mybatis
        //创建sessionFactory对象
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().
                build(Resources.getResourceAsStream("mybatis-config.xml"));
        //获取session对象
        SqlSession session = sf.openSession();
        //创建实体对象
        User user = new User();
        user.setName("boy");
        user.setPassword("123");
        user.setMobile("18656400001");
        //保存数据到数据库中
        session.insert("com.xzy.mapper.UserDao.insertSelective", user);
        //提交事务,这个是必须要的,否则即使sql发了也保存不到数据库中
        session.commit();
        //关闭资源
        session.close();
    }
}
