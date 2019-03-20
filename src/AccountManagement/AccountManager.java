package AccountManagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 处理注册时用户信息的写入与重复性检查
 * 登陆时用户信息的比对与检索
 */
public class AccountManager
{

    //设置账户管理文件路径
    static String path = "D:\\Examination\\AccountManagement.txt";
    //设置词典存储账户名，密码间的一一对应关系
    static Map<String,String> map = new HashMap<>();

    //注册时检查用户名是否重复，传参：输入的用户名，返回值：fasle=不能注册 true=能注册

    /**
     * 注册时检查用户名是否重复
     * @param account 输入的用户名
     * @return fasle=不能注册 true=能注册
     */
    static public boolean Cnki(String account)
    {
        try
        {
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = "";
            String[] pc = {"",""};      //pc[0]=账户名;pc[1]=密码
            while ((str = bufferedReader.readLine()) != null)
            {
                pc = str.split(",");    //将账户密码切开
                //账户名已存在，停止继续读取下一条注册的用户信息，返回查找结果：fasle=不能注册 true=能注册
                if (account.equals(pc[0]))
                {
                    bufferedReader.close();
                    return false;
                }
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 登陆时账户信息检索比对
     * @param account 输入的用户名
     * @param password 密码
     * @return true=用户已注册并且密码正确，能登陆 false=用户不能登陆
     */
    static public boolean AccountVerification(String account,String password)       //登陆时账户信息检索比对
    {
        try
        {
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = "";
            String[] pc = {"",""};
            String usename = "";
            String pass = "";
            //读出用户表并存入map
            while ((str = bufferedReader.readLine()) != null)                       //读出用户表并存入map
            {
                pc = str.split(",");
                usename = pc[0];
                pass = pc[1];
                map.put(usename,pass);
            }

            Set<String> keySet = map.keySet();          //读出所有用户名
            //检索用户名是否存在以及与密码是否对应
            for(String string : keySet)                                             //检索用户名是否存在以及与密码是否对应
            {
                if (account.equals(string) && password.equals(map.get(string)))
                {
                    bufferedReader.close();
                    return true;
                }
            }
            bufferedReader.close();
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 手机号登陆时检索手机号是否被绑定
     * @param phone 输入的手机号
     * @return true=手机号已绑定 false=手机号未绑定
     */
    static public boolean PhoneCnki(String phone)                                   //手机号登陆时检索手机号是否被绑定
    {
        try
        {
            File file = new File(path);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String str = "";
            String[] pc = {"",""};
            while ((str = bufferedReader.readLine()) != null)
            {
                pc = str.split(",");
                if (phone.equals(pc[2]))
                {
                    bufferedReader.close();
                    return true;
                }
            }
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 注册成功时写入用户信息
     * @param account 用户名
     * @param password 密码
     * @param phone 电话号码
     */
    static public void Regist(String account, String password,String phone)         //注册成功时写入用户信息
    {
        try
        {
            //检验用户信息文件夹是否存在
            File file1 = new File("D:\\Examination" );                  //检验用户信息文件夹是否存在
            if(!file1.exists())
                file1.mkdir();
            //写入用户信息
            File file2 = new File("D:\\Examination\\AccountManagement.txt");    //写入用户信息
            FileOutputStream fileOutputStream = new FileOutputStream(file2,true);
            fileOutputStream.write((account+ "," + password + "," + phone+ "\r\n").getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 创建账户文件
     */
    static public void Create()                                                     //创建账户文件夹
    {
        try
        {
            File file1 = new File("D:\\Examination" );
            if(!file1.exists())
                file1.mkdir();
            File file2 = new File("D:\\Examination\\AccountManagement.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file2,true);
            fileOutputStream.write(("").getBytes("UTF-8"));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
