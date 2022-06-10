package com.mr.modle;

import com.mr.view.BackgroundImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @ClassName Obstacle
 * @Description TODO
 * @Author xpower
 * @Date 2022/3/22 14:46
 * @Version 1.0
 */
public class Obstacle {

    public int x, y;
    public BufferedImage image;
    private BufferedImage Head;//图片1
    private BufferedImage Head2;//图片2
    private int speed;//移动速度
    public Obstacle() {
        try {
            Head = ImageIO.read(new File("image/Head.png"));
            Head2 = ImageIO.read(new File("image/Head2.png"));
        } catch (IOException e) {
                e.printStackTrace();
        }
        Random r=new Random();//创建随机对象
        if(r.nextInt(2)==0){//从0和1取一值为0
            image=Head2;//Head2图片
        }else{
            image=Head;
        }
        x=800;//初始横坐标
        y=220-image.getHeight();//纵坐标????????????
        speed= BackgroundImage.SPEED;//移动速度与背景同步
    }
    //移动
    public void move(){
        x-=speed;//横坐标递减
    }
    //消除
    public boolean isLive(){
        if(x<=-image.getWidth()){//如果移出了游戏界面
            return false;//消亡
        }
            return true;//存活
    }
//边界对象
    public Rectangle getBounds() {
        if (image == Head) {
            return new Rectangle(x+7 , y+5, 15, image.getHeight());
        }
        return new Rectangle(x+5 , y+4 , 23,21 );
    }
}
