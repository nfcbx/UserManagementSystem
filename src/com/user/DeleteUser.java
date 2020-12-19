package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUser {
    public static void deleteUser(int index) {

        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from user where `index` = ?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///user_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            statement = connection.prepareStatement(sql);
            statement.setObject(1, index);
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            ChangeUser.closeConnectionAndStatement(connection, statement);
        }
    }
}
