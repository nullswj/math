package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 试卷定制界面
 */
public class Change extends JFrame{

    JLabel mainTitle = new JLabel();
    JLabel num = new JLabel();
    JTextField Number = new JTextField();
    JRadioButton xiaoxue = new JRadioButton();
    JRadioButton chuzhong = new JRadioButton();
    JRadioButton gaozhong = new JRadioButton();
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton Confirm = new JButton();

    private ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/icon1.jpg"));

    /**
     * 设置制定试卷标签
     */
    private void setMainTitle()
    {
        mainTitle.setText("请定制试卷信息");
        mainTitle.setLocation(70,40);
        mainTitle.setSize(150,30);
        mainTitle.setFont(new Font("黑体",Font.PLAIN,16));
    }

    /**
     * 设置小学单选按钮
     */
    private void setXiaoxue()
    {
        xiaoxue.setText("小学");
        xiaoxue.setLocation(100,90);
        xiaoxue.setSize(50,30);
        xiaoxue.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置初中单选按钮
     */
    private void setChuzhong()
    {
        chuzhong.setText("初中");
        chuzhong.setLocation(100,130);
        chuzhong.setSize(50,30);
        chuzhong.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置高中单选按钮
     */
    private void setGaozhong()
    {
        gaozhong.setText("高中");
        gaozhong.setLocation(100,170);
        gaozhong.setSize(50,30);
        gaozhong.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置题目数量标签
     */
    private void setNum()
    {
        num.setText("题目数量:");
        num.setLocation(70,210);
        num.setSize(100,30);
        num.setFont(new Font("黑体",Font.PLAIN,16));
    }

    /**
     * 设置输入题目数量文本框
     */
    private void setNumber()
    {
        Number.setText("");
        Number.setLocation(140,213);
        Number.setSize(50,25);
        Number.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置确定按钮
     */
    private void setConfirm()
    {
        Confirm.setText("确定");
        Confirm.setLocation(90,250);
        Confirm.setSize(80,30);
        Confirm.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 添加组件到试卷定制界面
     */
    private void addComponent()
    {
        this.add(mainTitle);
        this.add(xiaoxue);
        this.add(chuzhong);
        this.add(gaozhong);
        this.add(num);
        this.add(Number);
        Confirm.addActionListener(queding);
        this.add(Confirm);
    }

    /**
     * 检查出题数目是否合法
     * @param num 输入的题目数
     * @return true = 合法， false = 不合法
     */
    private boolean CheckNum(String num)
    {
        if (num.equals("")) return false;
        char[] array = num.toCharArray();
        int len = array.length;
        for(int i = 0; i < len; i++)
            if(array[i] < '0' || array[i] > '9') return false;
        return true;
    }

    /**
     * 确定按钮事件
     */
    private ActionListener queding = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //检测输入出题数目是否合法
            if (CheckNum(Number.getText()) == false)
            {
                JOptionPane.showMessageDialog(null,"请输入合法的题目数量！","合法性检查",JOptionPane.ERROR_MESSAGE);
                return;
            }
            //判断选择哪一个单选按钮并出相应的试题
            if (xiaoxue.isSelected())
            {
                Examination examination = new Examination("小学",Integer.parseInt(Number.getText()));
                dispose();
            }
            else if(chuzhong.isSelected())
            {
                Examination examination = new Examination("初中",Integer.parseInt(Number.getText()));
                dispose();
            }
            else if(gaozhong.isSelected())
            {
                Examination examination = new Examination("高中",Integer.parseInt(Number.getText()));
                dispose();
            }
        }
    };

    //试卷定制界面初始化
    Change()
    {
        this.setLayout(null);
        setTitle("试卷定制");
        setSize(300,400);
        setIconImage(imageIcon.getImage());
        setLocation(700,300);

        setMainTitle();
        setXiaoxue();
        setChuzhong();
        setGaozhong();
        setNum();
        setNumber();
        setConfirm();
        buttonGroup.add(xiaoxue);
        buttonGroup.add(chuzhong);
        buttonGroup.add(gaozhong);
        addComponent();
        setVisible(true);
    }
}
