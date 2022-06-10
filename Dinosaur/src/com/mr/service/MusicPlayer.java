package com.mr.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

/**
 * @ClassName MusicPlayer
 * @Description TODO
 * @Author xpower
 * @Date 2022/3/22 15:03
 * @Version 1.0
 */
public class MusicPlayer implements Runnable {
    File soundFile;//音乐文件
    Thread thread;//父线程
    boolean circulate;//是否循环播放

    public MusicPlayer(String filepath, boolean circulate) throws FileNotFoundException {
        this.circulate = circulate;
        soundFile = new File(filepath);//文件不存在
        if (!soundFile.exists()) {
            throw new FileNotFoundException(filepath + "未找到");
        }
    }

    @Override
    public void run() {
        byte[] auBuffer = new byte[1024 * 128];//128kb缓冲区
        do {
            AudioInputStream audioinputStream = null;//创建音频输入流对象
            SourceDataLine auline = null;//混频器源数据行
            try {
                //从音乐文件中获取音频输入流
                audioinputStream = AudioSystem.getAudioInputStream(soundFile);
                AudioFormat format = audioinputStream.getFormat();//获取音频格式
                //按照源数据行类型和指定音频格式创建数据行对象
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                //利用音频系统类获得与指定line.info对象中描述匹配的行对象
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);//按照指定格式打开源数据行
                auline.start();//源数据行开启读写活动
                int byteCount = 0; //记录音频输入流读出字节数
                while (byteCount != -1) {
                    byteCount = audioinputStream.read(auBuffer, 0, auBuffer.length);
                    if (byteCount >= 0) {
                        auline.write(auBuffer, 0, byteCount);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } finally {
                if(auline!=null){
                auline.drain();
                }
                if(auline!=null){
                auline.close();
                }
            }
        } while (circulate);
    }
    public void play(){
        thread=new Thread(this);//创建线程
        thread.start();//启动线程
    }
    public void stop(){
        thread.stop();//此方法已过时
    }
}
