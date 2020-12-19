package com.gui;

import com.user.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class UserGUI {

    final String regex = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
    boolean age_flag = false;

    Vector<User> userVector;
    String username = null;
    int age;
    String address = null;
    String password = null;
    int selectRow = -1;

    private DefaultTableModel user_default_table_model;

    private JPanel user;
    private JScrollPane table;
    private JPanel text;
    private JPanel button;
    private JButton AddButton;
    private JButton DeleteButton;
    private JButton ChangeButton;
    private JButton RestButton;
    private JPanel NamePanel;
    private JPanel AgePanel;
    private JPanel AddressPanel;
    private JPanel PasswordPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JLabel addressLabel;
    private JTextField addressTextField;
    private JLabel ageLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;
    private JTable userTable;

    public UserGUI() {
        AddButton.addActionListener(e -> {
            if (nameTextField.getText() == null || addressTextField.getText() == null || passwordTextField.getPassword() == null) {
                JOptionPane.showMessageDialog(null, "请补全上述字段！", "提示", JOptionPane.WARNING_MESSAGE);
            } else if (age_flag) {
                Insert.insert(username, age, address, password);
                Object[] objects = new Object[]{username, age, address};
                user_default_table_model.addRow(objects);
                resetTextFieldAndVar();
            } else {
                JOptionPane.showMessageDialog(null, "年龄必须在1~120岁之间！", "提示", JOptionPane.WARNING_MESSAGE);
            }
        });
        nameTextField.addCaretListener(e -> username = nameTextField.getText());
        ageTextField.addCaretListener(e -> {
            if (!ageTextField.getText().matches(regex)) {
                age_flag = false;
            } else {
                age_flag = true;
                age = Integer.parseInt(ageTextField.getText());
            }
        });
        addressTextField.addCaretListener(e -> address = addressTextField.getText());
        passwordTextField.addCaretListener(e -> password = String.valueOf(passwordTextField.getPassword()));
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                userVector = GetResult.GetUser();
                selectRow = userTable.getSelectedRow();
                System.out.println("选中的行数" + selectRow);
                username = (String) user_default_table_model.getValueAt(selectRow, 0);
                age = (int) user_default_table_model.getValueAt(selectRow, 1);
                address = (String) user_default_table_model.getValueAt(selectRow, 2);
                nameTextField.setText(username);
                ageTextField.setText(String.valueOf(age));
                addressTextField.setText(address);
                passwordTextField.setText(userVector.get(selectRow).password);
            }
        });
        DeleteButton.addActionListener(e -> {
            int index = userVector.get(selectRow).index;
            System.out.println("在数据库中的index值" + index);
            user_default_table_model.removeRow(selectRow);
            DeleteUser.deleteUser(index);
            resetTextFieldAndVar();
        });
        RestButton.addActionListener(e -> resetTextFieldAndVar());
        ChangeButton.addActionListener(e -> {
            int index = userVector.get(selectRow).index;
            username = nameTextField.getText();
            age = Integer.parseInt(ageTextField.getText());
            address = addressTextField.getText();
            password = String.valueOf(passwordTextField.getPassword());
            ChangeUser.change(index,username,age,address,password);
            user_default_table_model.setValueAt(username,selectRow,0);
            user_default_table_model.setValueAt(age,selectRow,1);
            user_default_table_model.setValueAt(address,selectRow,2);
        });
    }

    public static void user() {
        JFrame frame = new JFrame("用户管理");
        frame.setContentPane(new UserGUI().user);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    //重置textField和变量
    private void resetTextFieldAndVar() {
        nameTextField.setText("");
        ageTextField.setText("");
        addressTextField.setText("");
        passwordTextField.setText("");
        username = null;
        age = 0;
        address = null;
        password = null;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        String[] tableHeader = {"姓名", "年龄", "地址"};
        Vector<User> userArrayList = GetResult.GetUser();
        int numRows = userArrayList.size();
        System.out.println("列数" + numRows);
        Object[][] data = new Object[numRows][3];
        for (int i = 0; i < numRows; i++) {
            data[i][0] = userArrayList.get(i).name;
            data[i][1] = userArrayList.get(i).age;
            data[i][2] = userArrayList.get(i).address;
        }

        user_default_table_model = new DefaultTableModel(data, tableHeader);
        userTable = new JTable(user_default_table_model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置单选
        JTableHeader tab_header = userTable.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        userTable.setFont(new Font("微软雅黑", Font.PLAIN, 13));
    }
}
