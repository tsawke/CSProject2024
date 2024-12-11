package com.csproject.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Pair;

import com.csproject.FieldType;
import com.csproject.GameMap;
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
        // frame2.setAlwaysOnTop(true);

        frame.setVisible(true);

        BufferedImage img = new  BufferedImage(mainPanel.getWidth(), mainPanel.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = img.createGraphics();
        mainPanel.paintAll(g2d);
        ImageIO.write(img, "png", new File("./csproject/src/main/resources/Icons/map" + idx + ".png"));
        Thread.sleep(200);
        frame.dispose();
        frame2.dispose();
    }
   

    public static void CreateAndShowWindow() {

        JPanel[] MapPanel = new JPanel[5];

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
    
    }

        
        


    //     JPanel Map1Panel = new JPanel(new GridLayout(Map1.getHeight(), Map1.getWidth(), 10, 10));
    //     Field[][] field1 = new Field[Map1.getHeight() + 1][Map1.getWidth() + 1];
        
    //     IntStream.range(1, Map1.getHeight() + 1).forEach(
    //          i -> {
    //              IntStream.range(1, Map1.getWidth() + 1).forEach(
    //                  j -> {
    //                      field1[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
    //                      Map1Panel.add(field1[i][j].panel);
    //                  }
    //              );
    //          }
    //      );
    //      frame.add(Map1Panel);
    //      MapPanel[0] = Map1Panel;

    //      JPanel Map2Panel = new JPanel(new GridLayout(Map2.getHeight(), Map2.getWidth(), 10, 10));
    //     Field[][] field2 = new Field[Map2.getHeight() + 1][Map2.getWidth() + 1];
        
    //     IntStream.range(1, Map2.getHeight() + 1).forEach(
    //          i -> {
    //              IntStream.range(1, Map2.getWidth() + 1).forEach(
    //                  j -> {
    //                      field2[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
    //                      Map2Panel.add(field2[i][j].panel);
    //                  }
    //              );
    //          }
    //      );
    //      frame.add(Map2Panel);
    //      MapPanel[1] = Map2Panel;

    //      JPanel Map3Panel = new JPanel(new GridLayout(Map3.getHeight(), Map3.getWidth(), 10, 10));
    //     Field[][] field3 = new Field[Map3.getHeight() + 1][Map3.getWidth() + 1];
        
    //     IntStream.range(1, Map3.getHeight() + 1).forEach(
    //          i -> {
    //              IntStream.range(1, Map3.getWidth() + 1).forEach(
    //                  j -> {
    //                      field3[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
    //                      Map3Panel.add(field3[i][j].panel);
    //                  }
    //              );
    //          }
    //      );
    //      frame.add(Map3Panel);
    //      MapPanel[2] = Map3Panel;

    //      JPanel Map4Panel = new JPanel(new GridLayout(Map4.getHeight(), Map4.getWidth(), 10, 10));
    //     Field[][] field4 = new Field[Map4.getHeight() + 1][Map4.getWidth() + 1];
        
    //     IntStream.range(1, Map4.getHeight() + 1).forEach(
    //          i -> {
    //              IntStream.range(1, Map4.getWidth() + 1).forEach(
    //                  j -> {
    //                      field4[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
    //                      Map4Panel.add(field4[i][j].panel);
    //                  }
    //              );
    //          }
    //      );
    //      frame.add(Map4Panel);
    //      MapPanel[3] = Map4Panel;

    //      JPanel Map5Panel = new JPanel(new GridLayout(Map5.getHeight(), Map5.getWidth(), 10, 10));
    //     Field[][] field5 = new Field[Map5.getHeight() + 1][Map5.getWidth() + 1];
        
    //     IntStream.range(1, Map5.getHeight() + 1).forEach(
    //          i -> {
    //              IntStream.range(1, Map5.getWidth() + 1).forEach(
    //                  j -> {
    //                      field5[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
    //                      Map5Panel.add(field5[i][j].panel);
    //                  }
    //              );
    //          }
    //      );
    //      frame.add(Map5Panel);
    //      MapPanel[4] = Map5Panel;
    //  }

        // 获取地图图片
        //ImageIcon MapPicture1 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
        //ImageIcon MapPicture2 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
        //ImageIcon MapPicture3 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
        //ImageIcon MapPicture4 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
        //ImageIcon MapPicture5 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");

        // 创建选地图按钮

        public static Pair<JPanel, JButton> createButtonAndPanel(String Name, JPanel MapPanel) {
            JButton button = new JButton(Name);
            button.setFont(new Font("Arial", Font.PLAIN, 40));
            button.setPreferredSize(new Dimension(500, 50));
            JPanel buttonPanel = MapPanel;
            buttonPanel.add(button);
            buttonPanel.setBackground(Color.DARK_GRAY);
            return Pair.of(buttonPanel, button);
        }


        //  JButton Map1Button = Map1.getRight();
        //  JButton Map2Button = Map2.getRight();
        //  JButton Map3Button = Map3.getRight();
        //  JButton Map4Button = Map4.getRight();
        //  JButton Map5Button = Map5.getRight();
        // Map1Button.setIcon(MapPicture1);
        // Map2Button.setIcon(MapPicture2);
        // Map3Button.setIcon(MapPicture3);
        // Map4Button.setIcon(MapPicture4);
        // Map5Button.setIcon(MapPicture5);
        // // 设置文本相对于图片的垂直位置为底部
        // Map1Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        // Map1Button.setText("Map1");
        // Map2Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        // Map2Button.setText("Map2");
        // Map3Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        // Map3Button.setText("Map3");
        // Map4Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        // Map4Button.setText("Map4");
        // Map5Button.setVerticalTextPosition(SwingConstants.BOTTOM);
        // Map5Button.setText("Map5");

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
        // buttons.setBackground(Color.DARK_GRAY);
        // buttons.add(Map1.getRight());
        // buttons.add(Map2.getRight());
        // buttons.add(Map3.getRight());
        // buttons.add(Map4.getRight());
        // buttons.add(Map5.getRight());
        // buttons.add(Exit.getRight());
        // frame.add(buttons, BorderLayout.CENTER);

        // Map1.getRight().addActionListener((e) -> {
        //     System.out.println("Press Button.");

        // });
        // Map2.getRight().addActionListener((e) -> {
        //     System.out.println("Press Button.");

        // });
        // Map3.getRight().addActionListener((e) -> {
        //     System.out.println("Press Button.");

        // });
        // Map4.getRight().addActionListener((e) -> {
        //     System.out.println("Press Button.");

        // });
        // Map5.getRight().addActionListener((e) -> {
        //     System.out.println("Press Button.");

        // });
        // Exit.getRight().addActionListener((e) -> {
        //     System.out.println("Press Button.");

        // });

        // frame.setVisible(true);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
