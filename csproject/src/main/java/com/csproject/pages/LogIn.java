package com.csproject.pages;
 
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatLightLaf;

public class LogIn {
    public static void CreateAndShowWindow() {
        FlatLightLaf.setup();

        JFrame frame = new JFrame("Sokoban Game");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
    }
}
