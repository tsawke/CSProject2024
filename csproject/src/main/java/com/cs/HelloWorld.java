package com.cs;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class HelloWorld {
    private static void createAndShowWindow() {
        // JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Hello World!");
        frame.getContentPane().add(label);
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                createAndShowWindow();
            }
        });
    }
}
