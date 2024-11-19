package com.csproject.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Pair;

import com.csproject.pages.ChooseMap;
import com.csproject.dependencies.VerticalFlowLayout;
import com.formdev.flatlaf.FlatLightLaf;

public class LogIn extends Index{
    public static void CreateAndShowWindow() {
        FlatLightLaf.setup();

        JFrame frame = new JFrame("Sokoban Game");
        JPanel panel = new JPanel();
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        // 设置标题
        JLabel title = new JLabel("Log In");
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        Container content = frame.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        Pair<JPanel, JButton> LogIn = createDefaultMenuButtonAndPanel("Log In");

        Pair<JPanel, JButton> Visitor = createDefaultMenuButtonAndPanel("Vistor");

        VerticalFlowLayout verticalLayout = new VerticalFlowLayout(10, 25);
        // verticalLayout.setFill(false);
        JPanel buttons = new JPanel(verticalLayout);
        buttons.setBackground(Color.DARK_GRAY);
        buttons.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        buttons.add(LogIn.getLeft());
        buttons.add(Visitor.getLeft());

        content.add(buttons, BorderLayout.CENTER);

        panel.setBackground(Color.gray);

        Visitor.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");
            Index.CreateAndShowWindow();
        });

        LogIn.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");
            Index.CreateAndShowWindow();
        });



        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
}
