package com.user;

import java.util.Vector;

public class Test {

    public static void main(String[] args) {
        String name = "张san";
        int age = 18;
        String address = "济宁";
        String password = "666";

//        Insert.insert(name,age,address,password);

//        DeleteUser.deleteUser(14);

        ChangeUser.change(4,"张三",18,"日本", String.valueOf(12533));

        Vector<com.user.User> userArrayList = com.user.GetResult.GetUser();
        ShowVector.showVector(userArrayList);


    }

}
