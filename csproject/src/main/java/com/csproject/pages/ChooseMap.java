package com.csproject.pages;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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

        JFrame frame = new JFrame("Choose Map!"); // 创建Choose Map窗口
        frame.setSize(1920, 1080);

        // 创建BorderLayout窗口管理器，用于存放标题
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.white);

        // 设置标题
        JLabel title = new JLabel("Choose Map!");
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        Container content = frame.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        // 获取地图图片
        ImageIcon MapPicture1 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
        ImageIcon MapPicture2 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
        ImageIcon MapPicture3 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
        ImageIcon MapPicture4 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
        ImageIcon MapPicture5 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");

        // 创建选地图按钮

        Pair<JPanel, JButton> Map1 = createDefaultMenuButtonAndPanel("Map1");
        Pair<JPanel, JButton> Map2 = createDefaultMenuButtonAndPanel("Map2");
        Pair<JPanel, JButton> Map3 = createDefaultMenuButtonAndPanel("Map3");
        Pair<JPanel, JButton> Map4 = createDefaultMenuButtonAndPanel("Map4");
        Pair<JPanel, JButton> Map5 = createDefaultMenuButtonAndPanel("Map5");
        Pair<JPanel, JButton> Exit = createDefaultMenuButtonAndPanel("Exit");

        JButton Map1Button = Map1.getRight();
        JButton Map2Button = Map2.getRight();
        JButton Map3Button = Map3.getRight();
        JButton Map4Button = Map4.getRight();
        JButton Map5Button = Map5.getRight();
        Map1Button.setIcon(MapPicture1);
        Map2Button.setIcon(MapPicture2);
        Map3Button.setIcon(MapPicture3);
        Map4Button.setIcon(MapPicture4);
        Map5Button.setIcon(MapPicture5);
        // 设置文本相对于图片的垂直位置为底部
        Map1Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        Map1Button.setText("Map1");
        Map2Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        Map2Button.setText("Map2");
        Map3Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        Map3Button.setText("Map3");
        Map4Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        Map4Button.setText("Map4");
        Map5Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        Map5Button.setText("Map5");

        /*
         * Dimension buttonSize = new Dimension(150, 150);
         * Map1Button.setPreferredSize(buttonSize);
         * Map2Button.setPreferredSize(buttonSize);
         * Map3Button.setPreferredSize(buttonSize);
         * Map4Button.setPreferredSize(buttonSize);
         * Map5Button.setPreferredSize(buttonSize);
         */

        // 使用Grid Layout排布选地图按钮
        // 创建一个JPanel作为子容器，使用GridLayout
        JPanel buttons = new JPanel(new GridLayout(2, 3, 20, 20));
        buttons.setBackground(Color.DARK_GRAY);
        buttons.add(Map1.getRight());
        buttons.add(Map2.getRight());
        buttons.add(Map3.getRight());
        buttons.add(Map4.getRight());
        buttons.add(Map5.getRight());
        buttons.add(Exit.getRight());
        frame.add(buttons, BorderLayout.CENTER);

        Map1.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");

        });
        Map2.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");

        });
        Map3.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");

        });
        Map4.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");

        });
        Map5.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");

        });
        Exit.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");

        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
