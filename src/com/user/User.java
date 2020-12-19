package com.user;

public class User {

    public final int index;
    public final String name;
    public final int age;
    public final String address;
    public final String password;

    public User(int index, String name, int age, String address, String password) {
        this.index = index;
        this.name = name;
        this.age = age;
        this.address = address;
        this.password = password;
    }
}
