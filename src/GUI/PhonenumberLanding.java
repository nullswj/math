package GUI;

import Daemon.DaemonConsole;
import IdentCode.Send;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 手机号登陆界面
 */
public class PhonenumberLanding extends JFrame
{

    //手机号登陆界面中间容器
    public HomePage homePage;
    public class HomePage extends JPanel
    {
        JLabel Title1 = new JLabel();
        JLabel phonenum = new JLabel();
        JLabel IdeCode = new JLabel();
        JTextField phone = new JTextField();
        public JTextField Countdown = new JTextField();
        JTextField IdeC = new JTextField();
        JButton getID = new JButton();
        JButton land = new JButton();

        String idcode = "";         //验证码

        /**
         * //设置主题标签
         */
        private void setTitle1()
        {
            Title1.setText("欢迎登陆在线测试系统");
            Title1.setLocation(45,30);
            Title1.setSize(200,30);
            Title1.setFont(new Font("黑体",Font.PLAIN,18));
            Title1.setForeground(Color.BLACK);
        }

        /**
         * //设置手机号标签
         */
        private void setPhonenum()
        {
            phonenum.setText(" 手机号:");
            phonenum.setLocation(50,70);
            phonenum.setSize(50,30);
            phonenum.setBackground(Color.GRAY);
            phonenum.setOpaque(true);
            phonenum.setFont(new Font("黑体",Font.PLAIN,12));
            phonenum.setForeground(Color.cyan);
        }

        /**
         * 设置手机号输入文本框
         */
        private void setPhone()
        {
            phone.setText("");
            phone.setLocation(100,70);
            phone.setSize(120,30);
            phone.setBackground(Color.GRAY);
            phone.setFont(new Font("黑体",Font.PLAIN,12));
            phone.setForeground(Color.cyan);
        }

        /**
         * 设置获取验证码倒计时文本框
         */
        private void setCountdown()
        {
            Countdown.setText("(60s)");
            Countdown.setLocation(70,110);
            Countdown.setSize(50,30);
            Countdown.setBackground(Color.GRAY);
            Countdown.setFont(new Font("黑体",Font.PLAIN,12));
            Countdown.setForeground(Color.cyan);
        }

        /**
         * 更新验证码倒计时
         * @param time
         */
        public void setTime(String time)                //验证码倒计时设置函数
        {
            Countdown.setText(time);
        }

        /**
         * 设置获取验证码按钮
         */
        private void setGetID()
        {
            getID.setText("获取验证码");
            getID.setLocation(130,110);
            getID.setSize(90,30);
            getID.setBackground(Color.GRAY);
            getID.setFont(new Font("黑体",Font.PLAIN,10));
            getID.setForeground(Color.cyan);
        }

        /**
         * 设置验证码标签
         */
        private void setIdeCode()
        {
            IdeCode.setText(" 验证码:");
            IdeCode.setLocation(50,150);
            IdeCode.setSize(50,30);
            IdeCode.setBackground(Color.GRAY);
            IdeCode.setOpaque(true);
            IdeCode.setFont(new Font("黑体",Font.PLAIN,12));
            IdeCode.setForeground(Color.cyan);
        }

        /**
         * 验证码输入文本框
         */
        private void setIdeC()
        {
            IdeC.setText("");
            IdeC.setLocation(100,150);
            IdeC.setSize(120,30);
            IdeC.setBackground(Color.GRAY);
            IdeC.setFont(new Font("黑体",Font.PLAIN,12));
            IdeC.setForeground(Color.cyan);
        }

        /**
         * 设置登陆按钮
         */
        private void setLand()
        {
            land.setText("登陆");
            land.setLocation(70,190);
            land.setSize(130,30);
            land.setBackground(Color.GRAY);
            land.setFont(new Font("黑体",Font.PLAIN,12));
            land.setForeground(Color.cyan);
        }

        /**
         * 添加组件，设置按钮监听
         */
        private void addComponent()
        {
            this.add(Title1);
            this.add(phonenum);
            this.add(phone);
            this.add(Countdown);
            getID.addActionListener(huoqu);
            this.add(getID);
            this.add(IdeCode);
            this.add(IdeC);
            land.addActionListener(denglu);
            this.add(land);
        }

        /**
         * 获取验证码按钮事件
         */
        private ActionListener huoqu = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    if (phone.getText().length() != 11) //判断手机号码是否为11位
                    {
                        JOptionPane.showMessageDialog(null,"手机号不合法！","合法性检查",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    char[] array = phone.getText().toCharArray();
                    for(int i = 0; i < 11; i++)     //判断手机号码各数是否为0-9
                    {
                        if(array[i] < '0' || array[i] > '9')
                        {
                            JOptionPane.showMessageDialog(null,"手机号不合法！","合法性检查",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    //手机号是否已经完成注册检查
                    if (AccountManagement.AccountManager.PhoneCnki(phone.getText()) == false)
                    {
                        JOptionPane.showMessageDialog(null,"手机号未注册！","合法性检查",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //手机号已注册，发送验证码
                    else
                    {
                        String number = phone.getText();
                        idcode = new Send().sendCode(number);
                        DaemonConsole.run(homePage);
                        //System.out.println(idcode);
                    }
                }
                catch (Exception a)
                {
                    a.printStackTrace();
                }
            }
        };

        /**
         * 登陆按钮事件
         */
        private ActionListener denglu = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //检测验证码是否输入正确
                if(IdeC.getText().equals(idcode) && idcode != "")
                {
                    JOptionPane.showMessageDialog(null,"登陆成功","登陆",JOptionPane.INFORMATION_MESSAGE);
                    Change change = new Change();
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"验证码错误","登陆",JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        };

        //手机登陆界面中间容器初始化
        HomePage()
        {
            this.setLayout(null);
            this.setSize(270,240);
            this.setLocation(150,60);
            this.setBackground(Color.LIGHT_GRAY);

            setTitle1();
            setPhonenum();
            setPhone();
            setCountdown();
            setGetID();
            setIdeCode();
            setIdeC();
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

    //手机登录界面初始化
    public PhonenumberLanding()
    {
        setTitle("数学测试系统-SM-登陆");
        setLayout(null);
        setSize(Size);
        setIconImage(imageIcon.getImage());
        setLocation(600,300);
        addImage();
        setVisible(true);
        setLayout(null);
        homePage = new HomePage();
        this.add(homePage);
    }
}
