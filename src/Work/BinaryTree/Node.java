package Work.BinaryTree;

/**
 * 计算二叉树结点，包括左孩子，右孩子，是操作数节点则存操作数（flag=0），是运算符节点则存运算符(flag=1)
 */
public class Node
{
    perdate DATE;
    Node lchild;
    Node rchild;
    int flag = -1;

    public Node()
    {
        DATE = new perdate();
        lchild = null;
        rchild = null;
    }
    public void setOpe(char ope)
    {
        DATE.setOpe(ope);
        flag = 0;
    }
    public void setDate(double dat)
    {
        DATE.setDate(dat);
        flag = 1;
    }
}
