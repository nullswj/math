package Daemon;

import GUI.PhonenumberLanding;

/**
 * 发送验证码时倒计时
 */
public class DaemonConsole
{
    public static void run(final PhonenumberLanding.HomePage f)
    {
        //创建线程
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    //设置验证码倒计时文本框的text
                    for(int i = 60; i > 0; i--)
                    {
                        f.setTime("("+i+"s)");
                        Thread.sleep(1000);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return;
            }
        }).start();
    }
}
