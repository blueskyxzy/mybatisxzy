package com.xzy.bean;

import lombok.Data;

import java.util.Date;

/**
 * created by xzy on 2019/1/15
 */
@Data
public class User {

    private Long id;

    private String name;

    private String password;

    private String mobile;

    private Date createTime;

    private Date updateTime;

}
