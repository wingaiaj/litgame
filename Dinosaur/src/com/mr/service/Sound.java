package com.mr.service;

import java.io.FileNotFoundException;

/**
 * @ClassName Sound
 * @Description TODO
 * @Author xpower
 * @Date 2022/3/22 15:03
 * @Version 1.0
 */
public class Sound {
    static final String DIR="music/";//音乐文件夹
    static final String BACKGROUND="background.wav";//背景音乐
    static final String JUMP="jump.wav";//音效1
    static final String HIT="hit.wav";//音效2
    /**
     * 播放声音
     * @param file 音乐文件完整名称
     * @param circulate 是否循环播放
     */
    private static void play(String file,boolean circulate) {
        try {
            MusicPlayer player = new MusicPlayer(file, circulate);//创建播放器
            player.play();//调用方法启动线程
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
        /**
         * 播放跳跃音效
         */
        static public void jump(){
            play(DIR+JUMP,false);
        }
    /**
     * 播放撞击音效
     */
    static public void hit(){
        play(DIR+HIT,false);
    }
    /**
     * 播放背景音乐
     */
    static public void background(){
        play(DIR+BACKGROUND,true);
    }
}
