package GUI;

import Work.BinaryTree.op;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Random;

import static Work.Working.*;

/**
 * 试题测试界面
 */
public class Examination extends JFrame
{


    JLabel grade = new JLabel();
    JLabel remain = new JLabel();
    JLabel Number = new JLabel();
    JTextField Grade = new JTextField();
    JTextField remainNum = new JTextField();
    JTextField QNumber = new JTextField();
    JTextField Ques = new JTextField();
    JRadioButton A = new JRadioButton();
    JRadioButton B = new JRadioButton();
    JRadioButton C = new JRadioButton();
    JRadioButton D = new JRadioButton();
    ButtonGroup buttonGroup = new ButtonGroup();
    JButton Confirm = new JButton();
    JButton reset = new JButton();

    private static int countRight = 0;          //当前答对的题目数
    private static int remainN;                 //剩余题目数
    private static int num = 0;                 //当前题号
    private static int sum = 0;                 //题目总数
    public static String Question = " ";        //题目
    private static String type = "";            //题目类型
    public static char[] str;                   //转化为可计算的题目
    static int[] check = new int[301];          //查重数组
    public static int Maxsize = 100;            //题目最大长度
    public static int index;                    //最大括号深度
    public static op[] Aop = new op[Maxsize];   //运算符集

    static Random rand = new Random();           //随机数生成
    static int loc = 0;                          //正确答案位置

    private ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/images/icon1.jpg"));  //主题图像


    /**
     * 设置做对题数标签
     */
    private void setGrade()
    {
        grade.setText("做对题数:");
        grade.setLocation(80,40);
        grade.setSize(120,30);
        grade.setFont(new Font("黑体",Font.PLAIN,16));
    }

    /**
     * 设置做对题数文本框
     */
    private void setGradeT()
    {
        Grade.setText("0");
        Grade.setLocation(300,40);
        Grade.setSize(50,25);
        Grade.setFont(new Font("黑体",Font.PLAIN,16));
        Grade.setEditable(false);
    }

    /**
     * 设置剩余题数数标签
     */
    private void setRemain()
    {
        remain.setText("剩余题数:");
        remain.setLocation(80,80);
        remain.setSize(120,30);
        remain.setFont(new Font("黑体",Font.PLAIN,16));
    }

    /**
     * 设置剩余题数文本框
     * @param num 剩余题数
     */
    private void setRemainNum(String num)
    {
        remainNum.setText(num);
        remainNum.setLocation(300,80);
        remainNum.setSize(50,25);
        remainNum.setFont(new Font("黑体",Font.PLAIN,16));
        remainNum.setEditable(false);
    }

    /**
     * 设置当前题号标签
     */
    private void setNumber()
    {
        Number.setText("当前题号:");
        Number.setLocation(80,120);
        Number.setSize(120,30);
        Number.setFont(new Font("黑体",Font.PLAIN,16));
    }

    /**
     * 设置当前题号文本框
     */
    private void setQNumber()
    {
        QNumber.setText("0");
        QNumber.setLocation(300,120);
        QNumber.setSize(50,25);
        QNumber.setFont(new Font("黑体",Font.PLAIN,16));
        QNumber.setEditable(false);
    }

    /**
     * 设置题目文本框
     */
    private void setQues()
    {
        Ques.setText("");
        Ques.setLocation(80,170);
        Ques.setSize(270,25);
        Ques.setFont(new Font("黑体",Font.PLAIN,16));
        Ques.setEditable(false);
    }

    /**
     * 设置A单选框
     */
    private void setA()
    {
        A.setText("A.");
        A.setLocation(80,210);
        A.setSize(100,30);
        A.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置B单选框
     */
    private void setB()
    {
        B.setText("B.");
        B.setLocation(280,210);
        B.setSize(100,30);
        B.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置C单选框
     */
    private void setC()
    {
        C.setText("C.");
        C.setLocation(80,250);
        C.setSize(100,30);
        C.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置D单选框
     */
    private void setD()
    {
        D.setText("D.");
        D.setLocation(280,250);
        D.setSize(100,30);
        D.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置开始按钮
     */
    private void setConfirm()
    {
        Confirm.setText("开始");
        Confirm.setLocation(230,290);
        Confirm.setSize(100,30);
        Confirm.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 设置重置按钮
     */
    private void setReset()
    {
        reset.setText("重置");
        reset.setLocation(110,290);
        reset.setSize(100,30);
        reset.setFont(new Font("黑体",Font.PLAIN,12));
    }

    /**
     * 重置按钮事件
     */
    private ActionListener res = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //输出成绩页面，可注销登陆
            if (reset.getText().equals("注销")) dispose();
            //答题界面，可重置试卷
            else
            {
                countRight = 0;
                num = 0;
                Examination examination = new Examination(type,sum);
                dispose();
            }
        }
    };

    /**
     * 开始按钮事件
     */
    private ActionListener kaishi = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //答案判定
            if (num != 0 || remainN == 0)
            {
                switch (loc)
                {
                    case 0: if(A.isSelected()) countRight++;break;
                    case 1: if(B.isSelected()) countRight++;break;
                    case 2: if(C.isSelected()) countRight++;break;
                    case 3: if(D.isSelected()) countRight++;break;
                }
                Grade.setText(""+countRight);
            }

            //答题完毕，显示成绩
            if (remainN == 0)
            {
                QNumber.setText("0");           //剩余问题设置0
                reset.setText("注销");          //重置变为注销
                double grade = (double)countRight/(double)num*100;  //成绩

                //设置答案选项保留两位小数
                BigDecimal Result = new BigDecimal(grade);
                grade = Result.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                Ques.setText("本次测验成绩: " + grade);   //显示成绩
                remainN = -10;          //设置标志= -10
                A.setText("A " );
                B.setText("B " );
                C.setText("C " );
                D.setText("D " );
                Confirm.setText("继续测试");    //设置确认按钮的text=继续测试
                return;
            }

            //继续答题
            if (remainN == -10)
            {
                countRight = 0;
                num = 0;
                Change change = new Change();
                dispose();
            }

            //题目与选项生成
            if (remainN > 0)
            {
                Confirm.setText("确定");  //设置确认按钮的text= 确认
                remainN--;                  //剩余题目数- -
                num++;                      //当前题号+ +
                remainNum.setText(""+ remainN);
                QNumber.setText("" + num);
                while (true)
                {
                    getQuestion(type);                                       //获取一道题目
                    if(ValidCheck(type) == true)                            //合法性检查
                    {
                        int key = toHash(Question);                         //重复性检查，哈希方式
                        if(check[key] == 0)
                        {
                            Ques.setText(Question + " = ");
                            check[key] = 1;
                            break;
                        }
                    }
                }

                double result = Calculate();                                //计算题目
                double[] ans = new double[4];                              //选项数组

                //选项设置
                for(int i = 0; i < 4; i++)
                {
                    ans[i] = result+rand.nextInt(1000)-500;
                    BigDecimal Result = new BigDecimal(ans[i]);
                    ans[i] = Result.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                }

                //随机生成答案位置
                loc = rand.nextInt(4);
                ans[loc] = result;
                //添加单选按钮text
                A.setText("A " + ans[0]);
                B.setText("B " + ans[1]);
                C.setText("C " + ans[2]);
                D.setText("D " + ans[3]);
            }

        }
    };

    //出题界面添加组件
    private void addComponent()
    {
        this.add(grade);
        this.add(Grade);
        this.add(remain);
        this.add(remainNum);
        this.add(Number);
        this.add(QNumber);
        this.add(Ques);
        this.add(A);
        this.add(B);
        this.add(C);
        this.add(D);
        this.add(Confirm);
        this.add(reset);
    }

    //初始化出题界面
    Examination(String type,int num)
    {
        this.setLayout(null);
        setTitle(type + "数学测试");
        setSize(450,400);
        setIconImage(imageIcon.getImage());
        setLocation(700,300);

        setGrade();
        setGradeT();
        setRemain();
        setRemainNum(""+num);
        setNumber();
        setQNumber();
        setQues();
        setA();
        setB();
        setC();
        setD();
        setConfirm();
        setReset();
        this.remainN = num;
        this.type = type;
        this.sum = num;
        buttonGroup.add(A);         //组合单选按钮
        buttonGroup.add(B);
        buttonGroup.add(C);
        buttonGroup.add(D);
        Confirm.addActionListener(kaishi);
        reset.addActionListener(res);
        addComponent();
        setVisible(true);
    }
}
