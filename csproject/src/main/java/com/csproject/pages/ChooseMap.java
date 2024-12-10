// package com.csproject.pages;

// import java.awt.*;
// import java.util.Arrays;
// import java.util.Map;
// import java.util.stream.IntStream;

// import javax.swing.ImageIcon;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.SwingConstants;

// import org.apache.commons.lang3.tuple.Pair;

// import com.csproject.FieldType;
// import com.csproject.GameMap;
// import com.csproject.Player;

// public class ChooseMap extends Index {

//     // TODO
//     // 调整label位置和字号大小
//     // 学习如何让label可点击
//     // 学习如何预留插入图片的位置

//     private class Field {
//         public JPanel panel;
//         public FieldType type;

//         public Field(FieldType type) {
//             this.panel = new JPanel();
//             this.panel.setBackground(type.getColor());
//             this.type = type;
//         }
//     }

   

//     public static void CreateAndShowWindow() {

//         JPanel[] MapPanel = new JPanel[5];

//         JFrame frame = new JFrame("Choose Map!"); // 创建Choose Map窗口
//         frame.setSize(1920, 1080);

//         // 创建BorderLayout窗口管理器，用于存放标题
//         frame.setLayout(new BorderLayout());

//         JPanel panel = new JPanel();
//         panel.setBackground(Color.gray);

//         // 设置标题
//         JLabel title = new JLabel("Choose Map!");
//         title.setFont(new Font("Arial", Font.PLAIN, 80));

//         panel.add(title);

//         Container content = frame.getContentPane();
//         content.add(panel, BorderLayout.NORTH);

        
//         GameMap Map1 = new GameMap(5, 6);
//         Map1.maps = Arrays.asList(
//             new GameMap(0, 0),
//             new GameMap(5, 6,
//                 new int[][]{
//                     {-1, -1, -1, -1, -1, -1, -1},
//                     {-1, 1, 1, 1, 1, 1, 1},
//                     {-1, 1, 4, 0, 0, 0, 1},
//                     {-1, 1, 0, 0, 2, 3, 1},
//                     {-1, 1, 0, 3, 2, 0, 1},
//                     {-1, 1, 1, 1, 1, 1, 1}
//                 }
//             )
//         );

//         GameMap Map2 = new GameMap(5, 6);
//         Map2.maps = Arrays.asList(
//             new GameMap(0, 0),
//             new GameMap(6, 7,
//                 new int[][]{
//                     {-1, -1, -1, -1, -1, -1, -1, -1},
//                     {-1, 1, 1, 1, 1, 1, 1, -1},
//                     {-1, 1, 4, 0, 0, 0, 1, 1},
//                     {-1, 1, 0, 2, 2, 0, 0, 1},
//                     {-1, 1, 0, 1, 3, 0, 3, 1},
//                     {-1, 1, 0, 0, 0, 0, 0, 1},
//                     {-1, 1, 1, 1, 1, 1, 1, 1}
//                 }
//             )
//         );

//         GameMap Map3 = new GameMap(5, 6);
//         Map3.maps = Arrays.asList(
//             new GameMap(0, 0),
//             new GameMap(7, 7,
//                 new int[][]{
//                     {-1, -1, -1, -1, -1, -1, -1, -1},
//                     {-1, -1, -1, 1, 1, 1, 1, -1},
//                     {-1, 1, 1, 1, 0, 0, 1, -1},
//                     {-1, 1, 4, 0, 3, 2, 1, 1},
//                     {-1, 1, 0, 0, 0, 2, 0, 1},
//                     {-1, 1, 0, 1, 3, 0, 0, 1},
//                     {-1, 1, 0, 0, 0, 0, 0, 1},
//                     {-1, 1, 1, 1, 1, 1, 1, 1}
//                 }
//             )
//         );

//         GameMap Map4 = new GameMap(5, 6);
//         Map4.maps = Arrays.asList(
//             new GameMap(0, 0),
//             new GameMap(7, 7,
//                 new int[][]{
//                     {-1, -1, -1, -1, -1, -1, -1, -1},
//                     {-1, -1, 1, 1, 1, 1, 1, -1},
//                     {-1, 1, 1, 4, 0, 0, 1, 1},
//                     {-1, 1, 0, 0, 1, 0, 0, 1},
//                     {-1, 1, 0, 2, 6, 2, 0, 1},
//                     {-1, 1, 0, 0, 3, 0, 0, 1},
//                     {-1, 1, 1, 0, 3, 0, 1, 1},
//                     {-1, 1, 1, 1, 1, 1, 1, 1}
//                 }
//             )
//         );

//         GameMap Map5 = new GameMap(5, 6);
//         Map5.maps = Arrays.asList(
//             new GameMap(0, 0),
//             new GameMap(6, 8,
//                 new int[][]{
//                     {-1, -1, -1, -1, -1, -1, -1, -1, -1},
//                     {-1, 1, 1, 1, 1, 1, 1, -1, -1},
//                     {-1, 1, 0, 0, 0, 0, 1, 1, 1},
//                     {-1, 1, 0, 0, 0, 3, 3, 0, 1},
//                     {-1, 1, 0, 2, 2, 2, 4, 0, 1},
//                     {-1, 1, 0, 0, 1, 0, 3, 0, 1},
//                     {-1, 1, 1, 1, 1, 1, 1, 1, 1},
//                 }
//             )
//         );


//         JPanel Map1Panel = new JPanel(new GridLayout(Map1.getHeight(), Map1.getWidth(), 10, 10));
//         Field[][] field1 = new Field[Map1.getHeight() + 1][Map1.getWidth() + 1];
        
//         IntStream.range(1, Map1.getHeight() + 1).forEach(
//              i -> {
//                  IntStream.range(1, Map1.getWidth() + 1).forEach(
//                      j -> {
//                          field1[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
//                          Map1Panel.add(field1[i][j].panel);
//                      }
//                  );
//              }
//          );
//          frame.add(Map1Panel);
//          MapPanel[0] = Map1Panel;

//          JPanel Map2Panel = new JPanel(new GridLayout(Map2.getHeight(), Map2.getWidth(), 10, 10));
//         Field[][] field2 = new Field[Map2.getHeight() + 1][Map2.getWidth() + 1];
        
//         IntStream.range(1, Map2.getHeight() + 1).forEach(
//              i -> {
//                  IntStream.range(1, Map2.getWidth() + 1).forEach(
//                      j -> {
//                          field2[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
//                          Map2Panel.add(field2[i][j].panel);
//                      }
//                  );
//              }
//          );
//          frame.add(Map2Panel);
//          MapPanel[1] = Map2Panel;

//          JPanel Map3Panel = new JPanel(new GridLayout(Map3.getHeight(), Map3.getWidth(), 10, 10));
//         Field[][] field3 = new Field[Map3.getHeight() + 1][Map3.getWidth() + 1];
        
//         IntStream.range(1, Map3.getHeight() + 1).forEach(
//              i -> {
//                  IntStream.range(1, Map3.getWidth() + 1).forEach(
//                      j -> {
//                          field3[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
//                          Map3Panel.add(field3[i][j].panel);
//                      }
//                  );
//              }
//          );
//          frame.add(Map3Panel);
//          MapPanel[2] = Map3Panel;

//          JPanel Map4Panel = new JPanel(new GridLayout(Map4.getHeight(), Map4.getWidth(), 10, 10));
//         Field[][] field4 = new Field[Map4.getHeight() + 1][Map4.getWidth() + 1];
        
//         IntStream.range(1, Map4.getHeight() + 1).forEach(
//              i -> {
//                  IntStream.range(1, Map4.getWidth() + 1).forEach(
//                      j -> {
//                          field4[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
//                          Map4Panel.add(field4[i][j].panel);
//                      }
//                  );
//              }
//          );
//          frame.add(Map4Panel);
//          MapPanel[3] = Map4Panel;

//          JPanel Map5Panel = new JPanel(new GridLayout(Map5.getHeight(), Map5.getWidth(), 10, 10));
//         Field[][] field5 = new Field[Map5.getHeight() + 1][Map5.getWidth() + 1];
        
//         IntStream.range(1, Map5.getHeight() + 1).forEach(
//              i -> {
//                  IntStream.range(1, Map5.getWidth() + 1).forEach(
//                      j -> {
//                          field5[i][j] = new Field(FieldType.values()[Map1.GetMapByIndex(i, j)]);
//                          Map5Panel.add(field5[i][j].panel);
//                      }
//                  );
//              }
//          );
//          frame.add(Map5Panel);
//          MapPanel[4] = Map5Panel;
//      }

//         // 获取地图图片
//         //ImageIcon MapPicture1 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
//         //ImageIcon MapPicture2 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
//         //ImageIcon MapPicture3 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
//         //ImageIcon MapPicture4 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");
//         //ImageIcon MapPicture5 = new ImageIcon("D:\\CSProject2024\\resources\\TempMap.png");

//         // 创建选地图按钮

//         public static Pair<JPanel, JButton> createButtonAndPanel(String Name, JPanel MapPanel) {
//             JButton button = new JButton(Name);
//             button.setFont(new Font("Arial", Font.PLAIN, 40));
//             button.setPreferredSize(new Dimension(500, 50));
//             JPanel buttonPanel = MapPanel;
//             buttonPanel.add(button);
//             buttonPanel.setBackground(Color.DARK_GRAY);
//             return Pair.of(buttonPanel, button);
//         }


//         //  JButton Map1Button = Map1.getRight();
//         //  JButton Map2Button = Map2.getRight();
//         //  JButton Map3Button = Map3.getRight();
//         //  JButton Map4Button = Map4.getRight();
//         //  JButton Map5Button = Map5.getRight();
//         // Map1Button.setIcon(MapPicture1);
//         // Map2Button.setIcon(MapPicture2);
//         // Map3Button.setIcon(MapPicture3);
//         // Map4Button.setIcon(MapPicture4);
//         // Map5Button.setIcon(MapPicture5);
//         // // 设置文本相对于图片的垂直位置为底部
//         // Map1Button.setVerticalTextPosition(SwingConstants.BOTTOM);
//         // Map1Button.setText("Map1");
//         // Map2Button.setVerticalTextPosition(SwingConstants.BOTTOM);
//         // Map2Button.setText("Map2");
//         // Map3Button.setVerticalTextPosition(SwingConstants.BOTTOM);
//         // Map3Button.setText("Map3");
//         // Map4Button.setVerticalTextPosition(SwingConstants.BOTTOM);
//         // Map4Button.setText("Map4");
//         // Map5Button.setVerticalTextPosition(SwingConstants.BOTTOM);
//         // Map5Button.setText("Map5");

//         /*
//          * Dimension buttonSize = new Dimension(150, 150);
//          * Map1Button.setPreferredSize(buttonSize);
//          * Map2Button.setPreferredSize(buttonSize);
//          * Map3Button.setPreferredSize(buttonSize);
//          * Map4Button.setPreferredSize(buttonSize);
//          * Map5Button.setPreferredSize(buttonSize);
//          */

//         // 使用Grid Layout排布选地图按钮
//         // 创建一个JPanel作为子容器，使用GridLayout
//         JPanel buttons = new JPanel(new GridLayout(2, 3, 20, 20));
//         // buttons.setBackground(Color.DARK_GRAY);
//         // buttons.add(Map1.getRight());
//         // buttons.add(Map2.getRight());
//         // buttons.add(Map3.getRight());
//         // buttons.add(Map4.getRight());
//         // buttons.add(Map5.getRight());
//         // buttons.add(Exit.getRight());
//         // frame.add(buttons, BorderLayout.CENTER);

//         // Map1.getRight().addActionListener((e) -> {
//         //     System.out.println("Press Button.");

//         // });
//         // Map2.getRight().addActionListener((e) -> {
//         //     System.out.println("Press Button.");

//         // });
//         // Map3.getRight().addActionListener((e) -> {
//         //     System.out.println("Press Button.");

//         // });
//         // Map4.getRight().addActionListener((e) -> {
//         //     System.out.println("Press Button.");

//         // });
//         // Map5.getRight().addActionListener((e) -> {
//         //     System.out.println("Press Button.");

//         // });
//         // Exit.getRight().addActionListener((e) -> {
//         //     System.out.println("Press Button.");

//         // });

//         // frame.setVisible(true);
//         // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//     }
