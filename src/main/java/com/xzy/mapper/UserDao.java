package com.xzy.mapper;

import com.xzy.bean.User;

public interface UserDao {

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

}
