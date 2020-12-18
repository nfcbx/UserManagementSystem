package com.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
    向数据库中插入
    参数：username， password
 */
public class InsertPrepare {

    public static void insertPrepare (String username, String password){

        Connection conn = null;
        PreparedStatement stmt = null;

        String sql = "insert into manager values(null, ?, ?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql:///user_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");

            stmt = conn.prepareStatement(sql);

            stmt.setObject(1,username);
            stmt.setObject(2,password);

            int count = stmt.executeUpdate();

            if (count > 0) {
                System.out.println(count);
                System.out.println("Susses");
            } else
                System.out.println("False!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

    }

}
