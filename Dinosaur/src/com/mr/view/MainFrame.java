package com.mr.view;

import com.mr.service.ScoreRecorder;
import com.mr.service.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @ClassName MainFrame
 * @Description TODO
 * @Author xpower
 * @Date 2022/3/22 15:02
 * @Version 1.0
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        restart();
        setBounds(340, 150, 820, 260 );
        setTitle("奔跑吧，嘉然！");
        Sound.background();
        ScoreRecorder.init();
        addListener();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void restart() {
        Container c =getContentPane();
        c.removeAll();
        GamePanel panel=new GamePanel();
        c.add(panel);
        addKeyListener(panel);
        c.validate();
    }

    private void addListener() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                ScoreRecorder.saverScore();
            }
        });
    }

}
