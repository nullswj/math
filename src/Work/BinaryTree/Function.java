package Work.BinaryTree;

import static GUI.Examination.*;
import static java.lang.Math.*;


public class Function
{

    /**
     * 填充操作符数组
     * @return
     */
    public static int Sortop()
    {
        int j = 0;      //记录Aop的top
        int ind = 0;    //记录括号层数
        int len = str.length;
        for(int i = 0; i < len && str[i] != 0; i++)
        {
            if(str[i] == '(')   ind++;
            else if(str[i] == ')') ind--;
            else if(str[i] == '+' || str[i] == '-' || str[i] == '*' || str[i] == '/' || str[i] == '^' || str[i] == '√' || str[i] == 's' || str[i] == 'c' || str[i] == 't')
            {
                Aop[j]= new op();   //建立运算符节点
                Aop[j].Index = ind; //设置此运算符的括号层数
                Aop[j].locate = i;  //设置此运算符的位置
                Aop[j].opration = str[i];   //设置此运算符
                j++;
            }
            index = (index >ind) ? index : ind; //更新整个题目的括号层数
        }
        return j;
    }

    /**
     * 将字符串转化为浮点数
     * @param strploy 操作数字符串
     * @param p 起始下标
     * @param q 结束下标
     * @return 浮点操作数
     */
    public static double str_to_float(char strploy[],int p, int q)
    {
        if(strploy[p] == '(')   p++;
        if(strploy[q] == ')')   q--;

        int Index = 0;      //操作数位数
        int temp = p;       //备份操作数字符串起始下标
        double n = 0;       //操作数

        for(;p <= q; p++)   //计算操作数位数
            Index ++;
        p = temp;           //恢复操作数字符串起始下标

        for(; p <= q; p++)  //转换操作数为double
        {
            Index--;
            n = n+((double)(strploy[p]-'0'))*(pow(10,Index));
        }
        return n;
    }

    /**
     * 判断是否切割为只有数字，忽略括号
     * @param p 题目字符串起始下标
     * @param q 结束下标
     * @return true=只有数字 false=有运算符
     */
    public static boolean isdate(int p,int q)
    {
        for(int i = p; i <= q; i++)
        {
            if (str[i] == '+' || str[i] == '-' || str[i] == '*' ||str[i]=='/' || str[i] == '^' || str[i] == '√' || str[i] == 's' || str[i] == 'c' || str[i] == 't')
                return false;
        }
        return true;
    }

    /**
     * 创建运算二叉树
     * @param b 根节点
     * @param p 起始下标
     * @param q 结束下标
     * @param tail 运算符集top
     */
    public static void Createnode(Node b, int p, int q, int tail)
    {
        int j = 0;
        int find = 0;                                   //标记是否找到符合条件的运算符
        if(isdate(p,q))                                 //递归至只有数据
            b.setDate(str_to_float(str,p,q));           //创建头结点，转化数据为double
        else
        {
            for(int temp = 0; temp <= index; temp++)        //依次处理计算，按照运算符与括号的优先级
            {
                for(j = tail-1; j >=  0; j--)               //按照括号优先级，优先级越低越放在上层
                {
                    //先放括号层数最少的，优先级最低的的运算符（+ -）
                    if(Aop[j].Index == temp && ((Aop[j].opration == '+') || (Aop[j].opration == '-')) && Aop[j].locate >= p && Aop[j].locate <= q)
                    {
                        find ++;
                        Aop[j].Index = -1;
                        Node lt, rt;
                        lt = new Node();
                        rt = new Node();
                        b.lchild = lt;
                        b.rchild = rt;
                        b.setOpe(Aop[j].opration);
                        Createnode(b.lchild,p,Aop[j].locate-1,tail);
                        Createnode(b.rchild,Aop[j].locate+1,q,tail);
                    }
                }
                //没有括号层数最少的，优先级最低的的运算符（+ -），找括号层数最少的，优先级次之的的运算符（* /）
                if(find == 0)
                {
                    for(j = tail-1; j >= 0; j--)
                    {
                        if(Aop[j].Index == temp && ((Aop[j].opration == '*') || (Aop[j].opration == '/')) && Aop[j].locate >= p && Aop[j].locate <= q)
                        {
                            find++;
                            Aop[j].Index = -1;
                            Node lt,rt;
                            lt = new Node();
                            rt = new Node();
                            b.lchild = lt;
                            b.rchild = rt;
                            b.setOpe(Aop[j].opration);
                            Createnode(b.lchild,p,Aop[j].locate-1,tail);
                            Createnode(b.rchild,Aop[j].locate+1,q,tail);
                        }
                    }
                }
                //没有括号层数最少的，优先级次之的的运算符（* /），找括号层数最少的，优先级再次之的的运算符（√）
                if(find == 0)
                {
                    for(j = tail-1; j >= 0; j--)
                    {
                        if(Aop[j].Index == temp && (Aop[j].opration == '√') && Aop[j].locate >= p && Aop[j].locate <= q)
                        {
                            Aop[j].Index = -1;
                            Node rt;
                            rt = new Node();
                            b.rchild = rt;
                            b.setOpe(Aop[j].opration);
                            Createnode(b.rchild,Aop[j].locate+1,q,tail);
                        }
                    }
                }
                //没有括号层数最少的，优先级次之的的运算符（√），找括号层数最少的，优先级再次之的的运算符（^ ）
                if(find == 0)
                {
                    for(j = tail-1; j >= 0; j--)
                    {
                        if(Aop[j].Index == temp && (Aop[j].opration == '^') && Aop[j].locate >= p && Aop[j].locate <= q)
                        {
                            Aop[j].Index = -1;
                            Node lt,rt;
                            lt = new Node();
                            rt = new Node();
                            b.lchild = lt;
                            b.rchild = rt;
                            b.setOpe(Aop[j].opration);
                            Createnode(b.lchild,p,Aop[j].locate-1,tail);
                            Createnode(b.rchild,Aop[j].locate+1,q,tail);
                        }
                    }
                }
                //没有括号层数最少的，优先级次之的的运算符（^），找括号层数最少的，优先级再次之的的运算符（sin cos tan）
                if(find == 0)
                {
                    for(j = tail-1; j >= 0; j--)
                    {
                        if(Aop[j].Index == temp && (Aop[j].opration == 's' || Aop[j].opration == 'c' || Aop[j].opration == 't') && Aop[j].locate >= p && Aop[j].locate <= q)
                        {
                            Aop[j].Index = -1;
                            Node rt;
                            rt = new Node();
                            b.rchild = rt;
                            b.setOpe(Aop[j].opration);
                            Createnode(b.rchild,Aop[j].locate+1,q,tail);
                        }
                    }
                }
            }
        }
    }

    /**
     * 计算递归二叉树
     * @param b 根节点
     * @return 计算结果
     */
    public static double Comp(Node b)
    {
        double v1, v2, v3 = 0;      //v3=结果
        if(b == null)   return 0;

        //结点为操作数，返回数据
        if(b.lchild == null && b.rchild == null)
            return b.DATE.date;

        //左子树为空，为叶节点（操作数节点）的上一层运算符节点（括号级别最高的√，sin，cos，tan），进行运算
        if(b.lchild == null)
        {
            v1 = Comp(b.rchild);
            switch (b.DATE.Operator)
            {
                case '√':
                    v3 = sqrt(v1);
                    break;
                case 's':
                    v3 = sin(toRadians(v1));
                    break;
                case 'c':
                    v3 = cos(toRadians(v1));
                    break;
                case 't':
                    v3 = tan(toRadians(v1));
            }
        }
        //节点为运算符节点，进行运算
        else
        {
            v1 = Comp(b.lchild);
            v2 = Comp(b.rchild);
            switch (b.DATE.Operator)
            {
                case '+':
                    v3 = v1+v2;
                    break;
                case '-':
                    v3 =  v1-v2;
                    break;
                case '*':
                    v3 = v1*v2;
                    break;
                case '/':
                    if(v2 != 0) v3 = v1/v2;
                    else
                    {
                        System.out.println("除零");
                        System.exit(0);
                    }
                    break;
                case '^':
                    v3 =  (pow(v1,v2));
                    break;
                default:
                    System.exit(0);
            }
        }
        return v3;
    }
}
