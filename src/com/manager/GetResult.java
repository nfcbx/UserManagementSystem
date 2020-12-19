package com.manager;

import java.sql.*;

public class GetResult {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;

        String sql = "select * from manager";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///user_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            statement = connection.createStatement();
            result = statement.executeQuery(sql);

            while (result.next()) {
                int id = result.getInt(1);
                String username = result.getString("username");
                String password = result.getString("password");
                System.out.println(id + "\t" + username + "\t" + password);
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

    }

}
