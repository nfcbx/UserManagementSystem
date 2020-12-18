package com.user;

import java.util.Vector;

public class ShowVector {
    public static void showVector(Vector<com.user.User> userArrayList) {
        for (int i = 0; i < userArrayList.size(); i++) {
            System.out.println(userArrayList.get(i).index + "\t" + userArrayList.get(i).name + "\t" + userArrayList.get(i).age + "\t" + userArrayList.get(i).address + "\t" + userArrayList.get(i).password);
        }
    }
}
