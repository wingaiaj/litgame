package com.mr.service;

import com.mr.view.GamePanel;
import com.mr.view.MainFrame;
import com.mr.view.ScoreDialog;

import java.awt.*;

/**
 * @ClassName FreshThread
 * @Description TODO
 * @Author xpower
 * @Date 2022/3/22 10:41
 * @Version 1.0
 */
public class FreshThread extends Thread{
        public static final int FREASH=20;
        GamePanel p;
    public FreshThread(GamePanel p) {
            this.p=p;
    }

    @Override
    public void run() {
        while(!p.isFinish()){
            p.repaint();
            try{
                Thread.sleep(FREASH);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        Container c=p.getParent();
        while(!(c instanceof MainFrame)){
            c=c.getParent();
        }
        MainFrame frame=(MainFrame) c;
        new ScoreDialog(frame);
        frame.restart();
    }
}
