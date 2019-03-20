package GUI;

import IdentCode.Send;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 注册界面
 */
public class Register extends JFrame
{
    /**
     * 注册界面中间容器
     */
    private class Home extends JPanel
    {
        JLabel Title1 = new JLabel();
        JLabel usename = new JLabel();
        JLabel password = new JLabel();
        JLabel phone = new JLabel();
        JTextField phonenum = new JTextField();
        JTextField use = new JTextField();
        JPasswordField pass = new JPasswordField();
        JTextField IdeCode = new JTextField();

        JButton regist = new JButton();
        JButton getIdeCode = new JButton();

        String idcode = "";                 //验证码

        /**
         * 设置注册界面标签
         */
        private void setTitle1()
        {
            Title1.setText("欢迎注册在线测试系统");
            Title1.setLocation(45,15);
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
            usename.setLocation(50,55);
            usename.setSize(50,30);
            usename.setBackground(Color.GRAY);
            usename.setOpaque(true);
            usename.setFont(new Font("黑体",Font.PLAIN,12));
            usename.setForeground(Color.cyan);
        }

        /**
         * 设置输入用户名文本框
         */
        private void setUse()
        {
            use.setText("");
            use.setLocation(100,55);
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
            password.setLocation(50,95);
            password.setSize(50,30);
            password.setBackground(Color.GRAY);
            password.setOpaque(true);
            password.setFont(new Font("黑体",Font.PLAIN,12));
            password.setForeground(Color.cyan);
        }

        /**
         * 设置输入密码标签
         */
        private void setPass()
        {
            pass.setText("");
            pass.setLocation(100,95);
            pass.setSize(120,30);
            pass.setBackground(Color.GRAY);
            pass.setFont(new Font("黑体",Font.PLAIN,12));
            pass.setForeground(Color.cyan);
        }

        /**
         * 设置手机号标签
         */
        private void setPhone()
        {
            phone.setText(" 手机号:");
            phone.setLocation(50,135);
            phone.setSize(50,30);
            phone.setBackground(Color.GRAY);
            phone.setOpaque(true);
            phone.setFont(new Font("黑体",Font.PLAIN,12));
            phone.setForeground(Color.cyan);
        }

        /**
         * 设置输入手机号文本框
         */
        private void setPhonenum()
        {
            phonenum.setText("");
            phonenum.setLocation(100,135);
            phonenum.setSize(120,30);
            phonenum.setBackground(Color.GRAY);
            phonenum.setFont(new Font("黑体",Font.PLAIN,12));
            phonenum.setForeground(Color.cyan);
        }

        /**
         * 设置获取验证码文本框
         */
        private void setIdeCode()
        {
            IdeCode.setText("");
            IdeCode.setLocation(50,175);
            IdeCode.setSize(80,30);
            IdeCode.setBackground(Color.GRAY);
            IdeCode.setFont(new Font("黑体",Font.PLAIN,12));
            IdeCode.setForeground(Color.cyan);
        }

        /**
         * 设置获取验证码标签
         */
        private void setGetIdeCode()
        {
            getIdeCode.setText("获取验证码");
            getIdeCode.setLocation(120,175);
            getIdeCode.setSize(100,30);
            getIdeCode.setBackground(Color.GRAY);
            getIdeCode.setFont(new Font("黑体",Font.PLAIN,12));
            getIdeCode.setForeground(Color.cyan);
        }

        /**
         * 设置注册按钮
         */
        private void setRegist()
        {
            regist.setText("注册");
            regist.setLocation(70,215);
            regist.setSize(130,30);
            regist.setBackground(Color.GRAY);
            regist.setFont(new Font("黑体",Font.PLAIN,12));
            regist.setForeground(Color.cyan);
        }

        /**
         * 密码检查
         * @param account
         * @param password
         * @return
         */
        private boolean Check(String account,String password)
        {
            if(account.equals("") || password.length() < 6 || password.length() > 10)   //检测密码是否符合长度要求
                return false;
            int flag = 0,len = password.length();
            char[] array = password.toCharArray();
            //检测密码是否同时含有大小写字母和数字
            for(int i = 0; i < len; i++)
            {
                if(array[i] <= '9' && array[i] >= '0')
                {
                    flag ++;
                    break;
                }
            }
            for(int i = 0; i < len; i++)
            {
                if(array[i] <= 'Z' && array[i] >= 'A')
                {
                    flag ++;
                    break;
                }
            }
            for(int i = 0; i < len; i++)
            {
                if(array[i] <= 'z' && array[i] >= 'a')
                {
                    flag ++;
                    break;
                }
            }
            if(flag == 3)
                return true;
            else
                return  false;
        }

        //添加组件到注册界面中间容器
        private void addComponent()
        {
            this.add(Title1);
            this.add(usename);
            this.add(use);
            this.add(password);
            this.add(pass);
            this.add(phone);
            this.add(phonenum);
            this.add(IdeCode);
            getIdeCode.addActionListener(huoqu);
            this.add(getIdeCode);
            regist.addActionListener(zhuce);
            this.add(regist);
        }

        /**
         * 获取验证码按钮事件
         */
        private ActionListener huoqu = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    //检测手机号长度是否正确
                    if (phonenum.getText().length() != 11)
                    {
                        JOptionPane.showMessageDialog(null,"手机号不合法！","合法性检查",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //检测手机号是否为0-9的数字
                    char[] array = phonenum.getText().toCharArray();
                    for(int i = 0; i < 11; i++)
                    {
                        if(array[i] < '0' || array[i] > '9')
                        {
                            JOptionPane.showMessageDialog(null,"手机号不合法！","合法性检查",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    //发送验证码
                    String number = phonenum.getText();
                    idcode = new Send().sendCode(number);
                }
                catch (Exception a)
                {
                    a.printStackTrace();
                }
            }
        };

        /**
         * 注册按钮事件
         */
        private  ActionListener zhuce = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //检测用户和密码是否合法
                if (Check(use.getText(),pass.getText()) == false)
                {
                    JOptionPane.showMessageDialog(null,"用户名或密码不合法！","合法性检查",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //检测用户名是否已经被注册
                if (AccountManagement.AccountManager.Cnki(use.getText()) == false)
                {
                    JOptionPane.showMessageDialog(null,"该用户已注册！","合法性检查",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //检测验证码是否正确
                if((IdeCode.getText().equals(idcode) && idcode != "")==false)
                {
                    JOptionPane.showMessageDialog(null,"验证码错误！","验证检查",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //检测在验证码正确的情况下，弹出再次确认密码界面，检测密码输入是否正确
                if(IdeCode.getText().equals(idcode) && idcode != "")
                {
                    PasswordCheck passwordCheck = new PasswordCheck(use.getText(),pass.getText(),phonenum.getText());
                }
            }
        };

        /**
         * 注册界面中间容器初始化
         */
        Home()
        {
            this.setLayout(null);
            setSize(270,260);
            setLocation(150,50);
            setBackground(Color.LIGHT_GRAY);

            setTitle1();
            setUsename();
            setUse();
            setPassword();
            setPass();
            setPhone();
            setPhonenum();
            setIdeCode();
            setGetIdeCode();
            setRegist();
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
     * 注册界面初始化
     */
    public Register()
    {
        setTitle("数学测试系统-SM-注册");
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
