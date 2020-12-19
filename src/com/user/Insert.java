package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {
    public static void insert(String name, int age, String address, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "insert into user values(null, ?, ?, ?, ?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql:///user_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1,name);
            stmt.setObject(2,age);
            stmt.setObject(3,address);
            stmt.setObject(4,password);
            stmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            ChangeUser.closeConnectionAndStatement(conn, stmt);
        }
    }
}
