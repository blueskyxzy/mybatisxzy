package com.xzy.test;

import com.xzy.bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * created by xzy on 2019/1/16
 */
public class testDatabase {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
        String name = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "root";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet set =null;
        try {
            Class.forName(name);// 指定连接类型
            conn = DriverManager.getConnection(url, username, password);// 获取连接
            statement = conn.prepareStatement("select * from user where id = 1");// 执行语句
            set = statement.executeQuery();

            User u=new User();
            //遍历结果集
            while(set.next()){
                u.setId(Long.parseLong(set.getString(1)));
                u.setName(set.getString(2));
                u.setPassword(set.getString(3));
            }
            System.out.println("result user:" + u.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            close(conn, set, statement);
        }



    }

    public static void close(Connection conn, ResultSet set, PreparedStatement pre) {
        try{
            if(set!=null){
                set.close();
            }if(pre!=null){
                pre.close();
            }if(conn!=null){
                conn.close();
            }
        }catch(Exception e2){
            e2.printStackTrace();
        }
    }
}
