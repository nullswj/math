package GUI;


import AccountManagement.AccountManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用户名登陆界面
 */
public class UsernameLanding extends JFrame
{
    /**
     * 用户登陆界面中间容器
     */
    private class Home extends JPanel
    {
        JLabel Title1 = new JLabel();
        JLabel usename = new JLabel();
        JLabel password = new JLabel();
        JTextField use = new JTextField();
        JPasswordField pass = new JPasswordField();
        JButton land = new JButton();

        private void setTitle1()
        {
            Title1.setText("欢迎登陆在线测试系统");
            Title1.setLocation(45,30);
            Title1.setSize(200,30);
            Title1.setFont(new Font("黑体",Font.ITALIC,18));
            Title1.setForeground(Color.BLACK);
        }

        /**
         * 设置用户名标签
         */
        private void setUsename()
        {
            usename.setText(" 用户名:");
            usename.setLocation(50,80);
            usename.setSize(50,30);
            usename.setBackground(Color.GRAY);
            usename.setOpaque(true);
            usename.setFont(new Font("黑体",Font.PLAIN,12));
            usename.setForeground(Color.cyan);
        }

        /**
         * 设置用户名输入文本框
         */
        private void setUse()
        {
            use.setText("");
            use.setLocation(100,80);
            use.setSize(120,30);
            use.setBackground(Color.GRAY);
            use.setFont(new Font("黑体",Font.PLAIN,12));
            use.setForeground(Color.cyan);
        }

        /**
         * 设置密码标签
         */
        private void setPassword()
        {
            password.setText("  密码: ");
            password.setLocation(50,130);
            password.setSize(50,30);
            password.setBackground(Color.GRAY);
            password.setOpaque(true);
            password.setFont(new Font("黑体",Font.PLAIN,12));
            password.setForeground(Color.cyan);
        }

        /**
         * 设置密码输入文本框
         */
        private void setPass()
        {
            pass.setText("");
            pass.setLocation(100,130);
            pass.setSize(120,30);
            pass.setBackground(Color.GRAY);
            pass.setFont(new Font("黑体",Font.PLAIN,12));
            pass.setForeground(Color.cyan);
        }

        /**
         * 设置登陆按钮
         */
        private void setLand()
        {
            land.setText("登陆");
            land.setLocation(80,180);
            land.setSize(130,30);
            land.setBackground(Color.GRAY);
            land.setFont(new Font("黑体",Font.PLAIN,12));
            land.setForeground(Color.cyan);
        }

        /**
         * 添加组件到用户登陆界面中间容器
         */
        private void addComponent()
        {
            this.add(Title1);
            this.add(usename);
            this.add(use);
            this.add(password);
            this.add(pass);
            land.addActionListener(denglu);
            this.add(land);
        }

        /**
         * 登录按钮事件
         */
        private ActionListener denglu = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //检测登陆信息是否输入正确
                if (AccountManager.AccountVerification(use.getText(),pass.getText()) == false)
                {
                    JOptionPane.showMessageDialog(null,"用户名或密码错误,或用户未注册","登陆",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if (AccountManager.AccountVerification(use.getText(),pass.getText()))
                {
                    JOptionPane.showMessageDialog(null,"登陆成功","登陆",JOptionPane.INFORMATION_MESSAGE);
                    Change change = new Change();
                    dispose();
                }
            }
        };

        /**
         * 初始化用户登陆界面中间容器
         */
        Home()
        {
            this.setLayout(null);
            setSize(270,240);
            setLocation(150,60);
            setBackground(Color.LIGHT_GRAY);

            setTitle1();
            setUsename();
            setUse();
            setPassword();
            setPass();
            setLand();
            addComponent();
        }
    }

    private Dimension Size = new Dimension(700,400);
    private ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/icon1.jpg"));
    private ImageIcon imageIcon1 = new ImageIcon(this.getClass().getResource("/images/bg1.jpg"));

    /**
     * 设置界面背景
     */
    private void addImage()
    {
        ImagePanel imagePanel = new ImagePanel(Size,imageIcon1.getImage());
        setContentPane(imagePanel);
    }

    /**
     * //初始化用户登录界面
     */
    public UsernameLanding()
    {
        setTitle("数学测试系统-SM-登陆");
        setLayout(null);
        setSize(Size);
        setIconImage(imageIcon.getImage());
        setLocation(600,300);
        addImage();
        setVisible(true);
        setLayout(null);
        Home home = new Home();
        this.add(home);
    }
}
