package com.csproject.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.stream.IntStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csproject.BeautifyUtils;
import com.csproject.FieldType;
import com.csproject.GameMap;
import com.csproject.GameSystem;
import com.csproject.Run3D;
import com.csproject.dependencies.SwingUtil;
import com.formdev.flatlaf.FlatLightLaf;

public class ChooseMap extends Index {

    private static class Field{
        public JPanel panel;
        public FieldType type;
        public Field(FieldType type) {
            this.panel = new JPanel();

            this.type = type;

            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(50, 50));
            label.setIcon(this.type.getIcon());
            
            this.panel.add(label);
            
            this.panel.setBackground(this.type.getColor());
            this.panel.updateUI();
        }
        // public void UpdateType(int type){
        //     this.type = FieldType.values()[type];
        //     this.panel.setBackground(this.type.getColor());
        //     this.panel.updateUI();
        // }
        public void UpdateType(FieldType type){
            this.type = type;

            this.panel.removeAll();

            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(50, 50));
            label.setIcon(this.type.getIcon());
            
            this.panel.add(label);

            this.panel.setBackground(this.type.getColor());
            this.panel.updateUI();
        }
    }

    // private static JPanel mainPanel;
    // private static Field[][] field = new Field[20][20];
    public static void CreateIcons(GameMap currentMap, int idx) throws Exception {
        FlatLightLaf.setup();

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(currentMap.getHeight(), currentMap.getWidth(), 10, 10));
        mainPanel.setSize(new Dimension(1000, 1000));
        // field = new Field[currentMap.getHeight() + 1][currentMap.getWidth() + 1];
        for(int i = 1; i <= currentMap.getHeight(); ++i)
            for(int j = 1; j <= currentMap.getWidth(); ++j)
                mainPanel.add((new Field(FieldType.values()[currentMap.GetMapByIndex(i, j)])).panel);
        frame.add(mainPanel);

        JFrame frame2 = new JFrame();
        frame2.setSize(500, 500);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);
        frame2.setAlwaysOnTop(true);

        frame.setVisible(true);

        Thread.sleep(50);

        BufferedImage img = new  BufferedImage(mainPanel.getWidth(), mainPanel.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = img.createGraphics();
        mainPanel.paintAll(g2d);
        ImageIO.write(img, "png", new File("./csproject/src/main/resources/Icons/map" + idx + ".png"));
        Thread.sleep(100);
        frame.dispose();
        frame2.dispose();
    }

    public static JPanel CreateCellPanel(int index, JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Level " + index);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel icon = new JLabel();
        icon.setIcon(SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/map" + index + ".png", false));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);
        icon.setPreferredSize(new Dimension(300, 300));

        JButton button2D = new JButton("Play 2D");
        button2D.setFont(new Font("Arial", Font.PLAIN, 20));
        button2D.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button3D = new JButton("Play 3D");
        button3D.setFont(new Font("Arial", Font.PLAIN, 20));
        button3D.setAlignmentX(Component.CENTER_ALIGNMENT);

        button2D.addActionListener(
            e -> {
                frame.dispose();
                GameSystem gameSystem = new GameSystem(index);
                gameSystem.CreateAndShowWindow();
            }
        );

        button3D.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Thread thread = new Thread(new Runnable(){
                        @Override
                        public void run() {
                            Run3D.Run(index);
                        }
                    });
                    thread.start();
                    frame.dispose();
                }
            }
        );

        panel.add(label);
        panel.add(icon);
        panel.add(Box.createVerticalStrut(10));
        panel.add(button2D);
        panel.add(Box.createVerticalStrut(5));
        panel.add(button3D);
        panel.add(Box.createVerticalStrut(10));

        panel.setBorder(BeautifyUtils.defaultGrayBorder);

        return panel;
    }

    public static void CreateAndShowWindow() {

        JFrame frame = new JFrame("Choose Map");
        frame.setSize(1920, 1080);

        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("Choose Map");
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        Container content = frame.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(2, 3, 150, 20));

        // JPanel[] descMapPanel = new JPanel[6];
        IntStream.range(1, 6).forEach(
            i -> {
                mapPanel.add(CreateCellPanel(i, frame));
            }
        );
        
        // JPanel placeHolder = new JPanel();
        // placeHolder.setPreferredSize(new Dimension(1800, 10));

        JPanel mainPanel = new JPanel();
        // mainPanel.setSize(new Dimension(1920, 900));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // mainPanel.add(placeHolder);
        mainPanel.add(mapPanel);

        content.add(mainPanel, BorderLayout.CENTER);

        frame.setFocusable(true);
        frame.requestFocus();
        frame.setVisible(true);
    }
}