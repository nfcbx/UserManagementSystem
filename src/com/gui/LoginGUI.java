package com.gui;

import com.manager.IsLogin;

import javax.swing.*;

public class LoginGUI {
    String username = null;
    String password = null;
    private JButton LoginButton;
    private JButton ExitButton;
    public JPanel managerLogin;
    private JPanel LoginText;
    private JPanel UsernamePanel;
    private JPanel UsernameLabelPanel;
    private JPanel UsernameTextPanel;
    private JLabel UsernameLabel;
    private JTextField UsernameText;
    private JPanel PasswordPanel;
    private JPasswordField PasswordText;
    private JPanel PasswordTextPanel;
    private JPanel LoginButtonPanel;
    private JPanel ExitButtonPanel;
    static final JFrame frame = new JFrame("管理员登录");

    public LoginGUI() {
        ExitButton.addActionListener(e -> System.exit(0));
        UsernameText.addCaretListener(e -> username = UsernameText.getText());
        PasswordText.addCaretListener(e -> password = String.valueOf(PasswordText.getPassword()));
        LoginButton.addActionListener(e -> {
            System.out.println(username);
            System.out.println(password);
            if ((username != null && password != null) && ((username.length() != 0) && (password.length() != 0))) {
                if (IsLogin.isLogin(username, password)) {
                    JOptionPane.showInternalMessageDialog(null, "登录成功");
                    frame.dispose();
                    UserGUI.user();
                } else {
                    JOptionPane.showInternalMessageDialog(null, "用户名或密码错误", "False", JOptionPane.ERROR_MESSAGE);
                }
            } else if ((username == null && password == null) || (username.length() == 0 && password == null) || (username.length() == 0 && password.length() == 0)) {
                JOptionPane.showInternalMessageDialog(null, "请输入用户名和密码！", "False", JOptionPane.ERROR_MESSAGE);
            } else if (username == null || username.length() == 0) {
                JOptionPane.showInternalMessageDialog(null, "请输入用户名！", "False", JOptionPane.ERROR_MESSAGE);
            } else if (password == null || password.length() == 0)
                JOptionPane.showInternalMessageDialog(null, "请输入密码！", "False", JOptionPane.ERROR_MESSAGE);
        });
    }

    public static void login() {
        frame.setContentPane(new LoginGUI().managerLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 250);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
