package Work;

import Work.BinaryTree.Node;

import java.math.BigDecimal;
import java.util.Random;

import static Work.BinaryTree.Function.Comp;
import static Work.BinaryTree.Function.Createnode;
import static Work.BinaryTree.Function.Sortop;
import static GUI.Examination.Question;
import static GUI.Examination.str;

public class Working {

    static char[] FH_KH = {'+','-','*','/'};                                         //运算符集
    static String[] FH = {"+", "-", "*", "/","+√","-√","*√", "/√","^2+","^2-","^2*","^2/","+sin","-sin","*sin","/sin","+cos","-cos","*cos","/cos","+tan","-tan","*tan","/tan"};
    static char[][] FH_check = {{'+','-','*','/'},{'√','^'},{'s','i','n','c','o','s','t','a','n'}};
    static Random rand = new Random();                                           //随机数生成
    static int Strlen = 0;                                                       //运算符数目


    /**
     * 字符串哈希
     * @param str
     * @return
     */
    public static int toHash(String str)                                        //字符串哈希函数
    {
        int hashcode = 0;
        int length = str.length();
        int letterValue = 0;
        for(int i = 0; i < length; i++)
        {
            letterValue += str.charAt(i);
            hashcode = letterValue % 301;
        }
        return hashcode;
    }

    /**
     * 获取对应阶段的一道题目
     * @param type 题目类型
     */
    public static void getQuestion(String type)                                       //获取对应阶段的一道题目
    {
        int NumberOperand,NumberOpeC;                                                    //操作数数目与操作符范围
        if (type.equals("小学"))  NumberOpeC = 4;
        else if (type.equals("初中")) NumberOpeC = 12;
        else NumberOpeC = 24;
        NumberOperand = rand.nextInt(4)+2;                                       //随机生成操作数的数目
        Strlen = NumberOperand-1;
        Question = "";
        for(int i = 0; i <NumberOperand; i++)                                           //题目组合
        {
            if(i == NumberOperand-1)
                Question += (rand.nextInt(100)+1);
            else
                Question += (rand.nextInt(100)+1)+(FH[rand.nextInt(NumberOpeC)]);
        }
    }

    /**
     * 计算题目
     * @return 计算结果
     */
    public static double Calculate()
    {
        char[] ques = Question.toCharArray();
        int len = ques.length;
        int count = 0;
        char[] jisuan = new char[100];
        for (int k = 0; k < len; k++)
        {
            jisuan[count] = ques[k];
            if(ques[k] == 'c' || ques[k] == 's' || ques[k] == 't')
                k+=2;
            count ++;
        }

        str = jisuan;
        Node b = new Node();
        int tail = Sortop();
        Createnode(b,0,count-1,tail);
        double result = Comp(b);
        BigDecimal Result = new BigDecimal(result);
        result = Result.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();

        return result;
    }

    /**
     * 题目的合法性检查
     * @param type 题目类型
     * @return
     */
    static public Boolean ValidCheck(String type)                                       //题目的合法性检查
    {
        // key=1=题目运算符符合该年级出题要求 key=2=成功添加左括号 key=3=成功添加右括号   F_key= FH_check的i    F_flag=FH_check的j
        int key = 0, F_key,F_flag;
        int flag_left = 0, flag_right = 0;
        //判断出题年级
        if(type.equals("小学")) { F_key = 0;  F_flag = 4;}
        else if(type.equals("初中"))  { F_key = 1;  F_flag = 2;}
        else    { F_key = 2;  F_flag = 9;}
        char[] Question1 = Question.toCharArray();
        int len = Question1.length;
        //检查是否缺少对应的操作符
        for(int i = 0; i < len; i++)                                                    //检查是否缺少对应的操作符
        {
            for(int j = 0; j < F_flag; j++)
            {
                if(Question1[i] == FH_check[F_key][j])
                {
                    key = 1;
                    break;
                }
            }
            if(key == 1)    break;
        }

        //只有一个操作数
        if (key == 0)   return false;
        if(Strlen == 1)
        {
            Question = ""+Question;
            return  true;
        }

        //加左括号
        int Ind = rand.nextInt(len/2-1);                                  //左括号随机下标
        char[] Question2 = new char[len+1];                                     //添加左括号后的题目
        if(Ind == 0)
        {
            Question2[0] = '(';
            for (int i = 1; i < len+1; i++)
                Question2[i] = Question1[i-1];
            flag_left = 1;
        }
        else
        {
            for(int i = Ind; i >= 0; i--)
            {
                for(int j = 0; j < 4; j++)
                {
                    //括号加在最外面
                    if(i == 0)
                    {
                        Question2[0] = '(';
                        flag_left = 1;
                        for (int k = 1; k < len+1; k++)
                            Question2[k] = Question1[k-1];
                        key = 2;
                        break;
                    }
                    //括号加在运算符后
                    else if(Question1[i] == FH_KH[j])
                    {
                        int k = 0;
                        for(k = 0; k < i +1; k++)
                            Question2[k] = Question1[k];
                        Question2[k] = '(';
                        flag_left = k;
                        for(++k; k < len+1; k++)
                            Question2[k] = Question1[k-1];
                        key = 2;
                        break;
                    }
                }
                if(key == 2)    break;
            }
        }
        //加右括号，同左括号添加法
        Ind = rand.nextInt(len-(len/2))+(len/2)+2;                                      //加右括号
        char[] Question3 = new char[len+2];
        if (Ind == len)
        {
            for (int i = 0; i < len+1; i++)
                Question3[i] = Question2[i];
            Question3[len+1] = ')';
            flag_right = len+1;
        }
        else
        {
            for(int i = Ind; i < len+1; i++)
            {
                for(int j = 0; j < 4; j++)
                {
                    if(i == len)
                    {
                        int k = 0;
                        for(k = 0; k < len+1; k++)
                            Question3[k] = Question2[k];
                        Question3[k] = ')';
                        flag_right = k;
                        key = 3;
                        break;
                    }
                    else if(Question2[i] == FH_KH[j])
                    {
                        int k;
                        for(k = 0; k < i; k++)
                            Question3[k] = Question2[k];
                        Question3[k] = ')';
                        flag_right = k;
                        for(++k; k < len+2; k++)
                            Question3[k] = Question2[k-1];
                        key = 3;
                        break;
                    }
                }
                if(key == 3)
                    break;
            }
        }
        //去除（）加在最外面的题目
        if (flag_right-flag_left < 4 || flag_right-flag_left == len)
            return false;
        //去除空行
        if (Question3[1] == 0 || Question3[2] == 0|| Question3[3] == 0)
            return false;
        Question = "";
        //字符串重新赋值
        for(int i = 0; i < Question3.length; i++)
            Question += Question3[i];

        return true;
    }
}
