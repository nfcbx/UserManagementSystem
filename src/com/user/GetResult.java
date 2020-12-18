package com.user;

import java.sql.*;
import java.util.Vector;

public class GetResult {

    public static Vector<com.user.User> GetUser() {

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        String sql = "select * from user";

        Vector<com.user.User> userArrayList = new Vector<>();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///user_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                int index = result.getInt(1);
                String name = result.getString(2);
                int age = result.getInt(3);
                String address = result.getString(4);
                String password = result.getString(5);
                com.user.User user = new com.user.User(index,name, age, address, password);
                userArrayList.add(user);
            }


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return userArrayList;
    }
}
