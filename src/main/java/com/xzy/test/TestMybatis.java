package com.xzy.test;

import com.xzy.bean.User;
import com.xzy.framworkofmybatis.MySqlSession;
import com.xzy.mapper.UserMapper;

/**
 * created by xzy on 2019/1/15
 */
public class TestMybatis {

    public static void main(String[] args){
        System.out.println("test mybatis");
        MySqlSession sqlSession=new MySqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1L);
        System.out.println(user);
    }
}
