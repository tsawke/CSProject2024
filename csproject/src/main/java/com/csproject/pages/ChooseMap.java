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

public class ChooseMap extends Index {

    // TODO
    // 调整label位置和字号大小
    // 学习如何让label可点击
    // 学习如何预留插入图片的位置

    public static void CreateAndShowWindow() {

        JFrame frame = new JFrame("Choose Map!");
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        JLabel title = new JLabel("Choose Map!");
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        Container content = frame.getContentPane();
        content.add(panel, BorderLayout.NORTH);
        frame.setLayout(new BorderLayout());
        frame.setSize(1920, 1080);
        Pair<JPanel, JButton> Map1 = createDefaultMenuButtonAndPanel("Map1");
        Pair<JPanel, JButton> Map2 = createDefaultMenuButtonAndPanel("Map2");
        Pair<JPanel, JButton> Map3 = createDefaultMenuButtonAndPanel("Map3");

        VerticalFlowLayout verticalLayout = new VerticalFlowLayout(10, 25);
        // verticalLayout.setFill(false);
        JPanel buttons = new JPanel(verticalLayout);
        buttons.setBackground(Color.DARK_GRAY);
        buttons.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        buttons.add(Map1.getLeft());
        buttons.add(Map2.getLeft());
        buttons.add(Map3.getLeft());

        content.add(buttons, BorderLayout.CENTER);
        Map1.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");

        });
        Map2.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");

        });
        Map3.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");

        });

        frame.setVisible(true);

    }
}
