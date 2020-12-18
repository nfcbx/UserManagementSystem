package com.manager;

import javax.swing.*;

public class Test {

    public static void main(String[] args) {

        String username = "admin";
        String password = "admin";

        if (com.manager.IsLogin.isLogin(username, password)) {
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败！");
        }

//        LoginGUI s = new LoginGUI();
//        s.asdf();

        String regex = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
        String isNum = "k";
        if( !isNum.matches(regex) ){
            JOptionPane.showMessageDialog(null, "年龄必须在1~120岁之间！", "提示", 2);

        }else {
            System.out.println("你输入的是有效数字");
        }

    }

}
