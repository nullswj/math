package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 首页界面
 */
public class Welcome extends JFrame
{

    //注册界面
    public static Register register;

    /**
     * 中间容器：系统登陆
     */
    private class HomePage extends JPanel
    {
        JLabel label = new JLabel();
        JButton phone = new JButton();
        JButton usename = new JButton();
        JButton register = new JButton();

        /**
         * 系统登陆标签
         */
        private void setLabel()
        {
            label.setText("系统登陆");
            label.setLocation(30,30);
            label.setSize(150,30);
            label.setFont(new Font("黑体",Font.PLAIN,20));
            label.setForeground(Color.BLACK);
        }

        /**
         * 手机登陆按钮
         */
        private void setPhone()
        {
            phone.setText("手机号登陆");
            phone.setLocation(40,90);
            phone.setSize(120,30);
            phone.setFont(new Font("黑体",Font.PLAIN,12));
            phone.setBackground(Color.GRAY);
            phone.setForeground(Color.cyan);
        }

        /**
         * 用户登陆按钮
         */
        private void setUsename()
        {
            usename.setText("用户名登陆");
            usename.setLocation(40,130);
            usename.setSize(120,30);
            usename.setFont(new Font("黑体",Font.PLAIN,12));
            usename.setForeground(Color.CYAN);
            usename.setBackground(Color.gray);
        }

        /**
         * 用户注册按钮
         */
        private void setRegister()
        {
            register.setText("新用户注册");
            register.setLocation(60,190);
            register.setSize(100,20);
            register.setFont(new Font("黑体",Font.PLAIN,10));
            register.setBackground(Color.GRAY);
            register.setForeground(Color.PINK);
        }

        /**
         * 手机号登陆按钮事件
         */
        private ActionListener phonenumber = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                PhonenumberLanding phonenumberLanding = new PhonenumberLanding();
            }
        };

        /**
         * 用户名登陆按钮事件
         */
        private ActionListener usernameL = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                UsernameLanding usernameLanding = new UsernameLanding();
            }
        };

        /**
         * 新用户注册按钮事件
         */
        private ActionListener registers = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Welcome.register = new Register();
            }
        };

        /**
         * 添加组件到系统登陆容器
         */
        private void addComponent()
        {
            this.add(label);
            phone.addActionListener(phonenumber);
            this.add(phone);
            usename.addActionListener(usernameL);
            this.add(usename);
            register.addActionListener(registers);
            this.add(register);
        }

        /**
         * 系统登陆中间容器的初始化
         */
        HomePage()
        {
            setSize(200,240);
            setLocation(150,60);
            setBackground(Color.LIGHT_GRAY);
            setLayout(null);

            setLabel();
            setPhone();
            setUsename();
            setRegister();
            addComponent();
            AccountManagement.AccountManager.Create();
        }
    }

    //表明一个组件的宽和高（大小）
    private Dimension Size = new Dimension(700,400);
    //表明图片的相对路径
    private ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/icon1.jpg"));
    private ImageIcon imageIcon1 = new ImageIcon(this.getClass().getResource("/images/bg1.jpg"));

    /**
     * 界面背景
     */
    private void addImage()
    {
        //提供一个专门添加图片的JPanel 详见ImagePale
        ImagePanel imagePanel = new ImagePanel(Size,imageIcon1.getImage());
        //添加图片JPanel到系统登陆界面（Welcome）里
        setContentPane(imagePanel);
    }

    /**
     * 登陆界面初始化
     */
    public Welcome()
    {
        setTitle("数学测试系统-SM-首页");
        setSize(Size);
        setLayout(null);
        setIconImage(imageIcon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(550,250);
        setVisible(true);
        addImage();
        HomePage homePage = new HomePage();
        this.add(homePage);
    }
}
