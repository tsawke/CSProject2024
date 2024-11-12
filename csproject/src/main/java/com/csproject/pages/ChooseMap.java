package com.csproject.pages;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChooseMap {

    // TODO
    //调整label位置和字号大小
    //学习如何让label可点击
    //学习如何预留插入图片的位置


    public static void CreateAndShowWindow() {
        JFrame frame = new JFrame("Choose Map!");   //创建choosemap页面
        frame.setSize(1920, 1080);
        JPanel Map1 = new JPanel();
        JLabel Map1Label = new JLabel("Level1");

        Map1.setBackground(Color.WHITE);
        Map1.add(Map1Label);
        frame.add(Map1);
        frame.setVisible(true);

    }
}
