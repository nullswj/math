package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 密码确认界面
 */
public class PasswordCheck extends JFrame
{
    JLabel label = new JLabel();
    JPasswordField textField = new JPasswordField();
    JButton button = new JButton();

    String account = "";
    String password = "";
    String phone = "";

    private ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/icon1.jpg"));

    /**
     * //设置确认密码标签
     */
    private void setLabel()
    {
        label.setText("请再次输入密码");
        label.setLocation(45,30);
        label.setSize(150,30);
        label.setFont(new Font("黑体",Font.PLAIN,16));
    }

    /**
     * 设置输入密码文本框
     */
    private void setTextField()
    {
        textField.setText("");
        textField.setLocation(45,70);
        textField.setSize(130,30);
        textField.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置确认按钮
     */
    private void setButton()
    {
        button.setText("确定");
        button.setLocation(100,110);
        button.setSize(70,30);
        button.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 添加组件到密码确认界面中
     */
    private void addComponent()
    {
        this.add(label);
        this.add(textField);
        button.addActionListener(queding);
        this.add(button);
    }

    /**
     * 确认按钮事件
     */
    private ActionListener queding = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //检测两次密码是否一致
            if(textField.getText().equals(password) == false)
            {
                JOptionPane.showMessageDialog(null,"两次密码不一致！","合法性检查",JOptionPane.ERROR_MESSAGE);
                dispose();
            }
            else
            {
                AccountManagement.AccountManager.Regist(account,password,phone);                //写入注册信息
                JOptionPane.showMessageDialog(null,"注册成功！","注册",JOptionPane.INFORMATION_MESSAGE);
                Welcome.register.dispose();     //关闭注册界面
                Change change = new Change();   //弹出出题界面
                dispose();
            }
        }
    };

    //初始化确认密码界面
    PasswordCheck(String account,String password, String phone)
    {
        this.setLayout(null);
        setTitle("密码确认");
        setSize(250,200);
        setIconImage(imageIcon.getImage());
        setLocation(700,300);
        setLabel();
        setTextField();
        setButton();
        addComponent();
        setVisible(true);

        this.account = account;
        this.password = password;
        this.phone = phone;
    }
}
