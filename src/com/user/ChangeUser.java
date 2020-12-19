package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeUser {
    public static void change(int index, String name, int age, String address, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "update user set name = ? , age = ? , address = ? , password = ? where `index` = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///user_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, name);
            preparedStatement.setObject(2, age);
            preparedStatement.setObject(3, address);
            preparedStatement.setObject(4, password);
            preparedStatement.setObject(5, index);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnectionAndStatement(connection, preparedStatement);
        }
    }

    static void closeConnectionAndStatement(Connection connection, PreparedStatement preparedStatement) {
        GetResult.closeConnectionAndStatement(connection, preparedStatement);
    }
}

