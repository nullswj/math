package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * 重绘界面背景
 */
public class ImagePanel extends JPanel          //图片中间容器
{
    Dimension d;            //图片大小
    Image image;            //图片

    public ImagePanel(Dimension d,Image image)
    {
        super();
        this.d = d;
        this.image = image;
    }

    //paintComponenet的作用是给组件画上背景
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //画背景图片
        g.drawImage(image,0,0,d.width,d.height,this);
        //界面重绘
        this.repaint();
    }
}