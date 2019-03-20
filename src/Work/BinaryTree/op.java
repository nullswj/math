package Work.BinaryTree;

/**
 * 操作符数据结构
 */
public class op
{
    char opration;
    int Index;      //括号层数
    int locate;     //op的位置

    op()
    {
        opration = 0;
        Index = -1;
        locate = -1;
    }
}
