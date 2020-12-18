package com.gui;

import com.user.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class UserGUI {

    String regex = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";
    boolean ageflag = false;

    Vector<User> userTable;
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
    private JTextField nametextField;
    private JTextField agetextField;
    private JLabel addressLabel;
    private JTextField addresstextField;
    private JLabel ageLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordtextField;
    private JTable usertable;

    public UserGUI() {
        AddButton.addActionListener(e -> {
            if (nametextField.getText() == null || addresstextField.getText() == null || passwordtextField.getPassword() == null) {
                JOptionPane.showMessageDialog(null, "请补全上述字段！", "提示", 2);
            } else if (ageflag) {
                Insert.insert(username, age, address, password);
                Object[] objects = new Object[]{username, age, address};
                user_default_table_model.addRow(objects);
                resetTextFieldAndVar();
            } else {
                JOptionPane.showMessageDialog(null, "年龄必须在1~120岁之间！", "提示", 2);
            }
        });
        nametextField.addCaretListener(e -> username = nametextField.getText());
        agetextField.addCaretListener(e -> {
            if (!agetextField.getText().matches(regex)) {
                ageflag = false;
            } else {
                ageflag = true;
                age = Integer.parseInt(agetextField.getText());
            }
        });
        addresstextField.addCaretListener(e -> address = addresstextField.getText());
        passwordtextField.addCaretListener(e -> password = String.valueOf(passwordtextField.getPassword()));
        usertable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                userTable = GetResult.GetUser();
                selectRow = usertable.getSelectedRow();
                System.out.println("选中的行数" + selectRow);
                username = (String) user_default_table_model.getValueAt(selectRow, 0);
                age = (int) user_default_table_model.getValueAt(selectRow, 1);
                address = (String) user_default_table_model.getValueAt(selectRow, 2);
                nametextField.setText(username);
                agetextField.setText(String.valueOf(age));
                addresstextField.setText(address);
                passwordtextField.setText(userTable.get(selectRow).password);
            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = userTable.get(selectRow).index;
                System.out.println("在数据库中的index值" + index);
                user_default_table_model.removeRow(selectRow);
                DeleteUser.deleteUser(index);
                resetTextFieldAndVar();
            }
        });
        RestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTextFieldAndVar();
            }
        });
        ChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = userTable.get(selectRow).index;
                System.out.println("在数据库中的index值" + index);
                username = nametextField.getText();
                age = Integer.parseInt(agetextField.getText());
                address = addresstextField.getText();
                password = String.valueOf(passwordtextField.getPassword());
                ChangeUser.change(index,username,age,address,password);
                user_default_table_model.setValueAt(username,selectRow,0);
                user_default_table_model.setValueAt(age,selectRow,1);
                user_default_table_model.setValueAt(address,selectRow,2);
            }
        });
    }

    public static void main(String[] args) {
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
        nametextField.setText("");
        agetextField.setText("");
        addresstextField.setText("");
        passwordtextField.setText("");
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
        usertable = new JTable(user_default_table_model) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        usertable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//设置单选
        JTableHeader tab_header = usertable.getTableHeader();                    //获取表头
        tab_header.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        tab_header.setPreferredSize(new Dimension(tab_header.getWidth(), 30));    //修改表头的高度
        usertable.setFont(new Font("微软雅黑", Font.PLAIN, 13));
    }
}
