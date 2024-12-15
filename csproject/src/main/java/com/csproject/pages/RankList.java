package com.csproject.pages;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

import org.apache.commons.lang3.tuple.Pair;

import com.csproject.H2Database;
import com.csproject.User;
import com.csproject.dependencies.VerticalFlowLayout;
import com.formdev.flatlaf.FlatLightLaf;

public class RankList {

    public static JFrame frame;

    public static Pair<JPanel, JButton> createDefaultMenuButtonAndPanel(String Name) {
        JButton button = new JButton(Name);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        button.setPreferredSize(new Dimension(500, 50));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(button);
        buttonPanel.setBackground(Color.DARK_GRAY);
        return Pair.of(buttonPanel, button);
    }

    public static void CreateAndShowWindow() throws Exception {
        FlatLightLaf.setup();
        frame = new JFrame("RankList");
        frame.setSize(1920, 1080);
        frame.setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("RankList", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        Container content = frame.getContentPane();

        panel.setBounds(0, 0, 1920, 100);
        content.add(panel);

        VerticalFlowLayout verticalLayout = new VerticalFlowLayout(10, 25);
        // verticalLayout.setFill(false);
        JPanel Ranks = new JPanel(verticalLayout);
        Ranks.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        JPanel LevelPanel = new JPanel();
        LevelPanel.setBackground(Color.DARK_GRAY);
        LevelPanel.setBounds(0, 150, 500, 100);
        JLabel chooseLabel = new JLabel("Please Choose level");
        chooseLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        JComboBox<Object> ChooseLevel = new JComboBox();
        ChooseLevel.addItem("--ChooseLevel--");
        ChooseLevel.addItem("Level 1");
        ChooseLevel.addItem("Level 2");
        ChooseLevel.addItem("Level 3");
        ChooseLevel.addItem("Level 4");
        ChooseLevel.addItem("Level 5");
        ChooseLevel.setBounds(50, 50, 400, 100);
        chooseLabel.setBounds(30, 50, 20, 50);

        LevelPanel.add(chooseLabel);
        LevelPanel.add(ChooseLevel);

        content.add(LevelPanel);

        ChooseLevel.addActionListener((e) -> {
            String level = (String) ChooseLevel.getSelectedItem();
            if (level.equals("--ChooseLevel--")) {
                return;
            }
            if (level.equals("Level 1")) {
                List<Pair<User, Integer>> rankList;
                try {
                    rankList = H2Database.GetRank(1);
                    int rank = 1;
                for (Pair<User, Integer> pair : rankList) {
                    User user = pair.getLeft();
                    int steps = pair.getRight();
                    JLabel rankLabel = new JLabel(String.format("\t%-10s\t%-15s\t%d", "No." + rank, user.getUsername(), steps));
                    rankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    rankLabel.setForeground(Color.WHITE);
                    Ranks.add(rankLabel);
                    rank++;
            }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } 
                
        }
            if (level.equals("Level 2")) {
                List<Pair<User, Integer>> rankList2;
                try {
                    rankList2 = H2Database.GetRank(2);
                    int rank2 = 1;
                for (Pair<User, Integer> pair : rankList2) {
                    User user = pair.getLeft();
                    int steps = pair.getRight();
                    JLabel rankLabel = new JLabel(String.format("\t%-10s\t%-15s\t%d", "No." + rank2, user.getUsername(), steps));
                    rankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    rankLabel.setForeground(Color.WHITE);
                    Ranks.add(rankLabel);
                    rank2++;
            }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } 
                
        }
            if (level.equals("Level 3")) {
                List<Pair<User, Integer>> rankList3;
                try {
                    rankList3 = H2Database.GetRank(3);
                    int rank3 = 1;
                for (Pair<User, Integer> pair : rankList3) {
                    User user = pair.getLeft();
                    int steps = pair.getRight();
                    JLabel rankLabel = new JLabel(String.format("\t%-10s\t%-15s\t%d", "No." + rank3, user.getUsername(), steps));
                    rankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    rankLabel.setForeground(Color.WHITE);
                    Ranks.add(rankLabel);
                    rank3++;

            }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } 
                
        }
            if (level.equals("Level 4")) {
                 List<Pair<User, Integer>> rankList4;
                try {
                    rankList4 = H2Database.GetRank(4);
                    int rank4 = 1;
                    for (Pair<User, Integer> pair : rankList4) {
                    User user = pair.getLeft();
                    int steps = pair.getRight();
                    JLabel rankLabel = new JLabel(String.format("\t%-10s\t%-15s\t%d", "No." + rank4, user.getUsername(), steps));
                    rankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    rankLabel.setForeground(Color.WHITE);
                    Ranks.add(rankLabel);
                    rank4++;

            }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } 
                
                
        }
            if (level.equals("Level 5")) {
                 List<Pair<User, Integer>> rankList5;
                try {
                    rankList5 = H2Database.GetRank(2);
                    int rank5 = 1;
                for (Pair<User, Integer> pair : rankList5) {
                    User user = pair.getLeft();
                    int steps = pair.getRight();
                    JLabel rankLabel = new JLabel(String.format("\t%-10s\t%-15s\t%d", "No." + rank5, user.getUsername(), steps));
                    rankLabel.setFont(new Font("Arial", Font.PLAIN, 20));
                    rankLabel.setForeground(Color.WHITE);
                    Ranks.add(rankLabel);
                    rank5++;

            }
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } 
                
        }
            Ranks.removeAll();
            Ranks.revalidate();
            Ranks.repaint();
            try {
                RankList.CreateAndShowWindow();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        // JLabel First = new JLabel(String.format("\t%-10s\t%-15s\t%d", "1st", H2Database.GetRank(0), 123), 0);
        // JLabel Second = new JLabel(String.format("\t%-10s\t%-15s\t%d", "2nd", "Username", 123), 0);
        // JLabel Third = new JLabel(String.format("\t%-10s\t%-15s\t%d", "3rd", "Username", 123), 0);
        // JLabel Fourth = new JLabel(String.format("\t%-10s\t%-15s\t%d", "4th", "Username", 123), 0);
        // JLabel Fifth = new JLabel(String.format("\t%-10s\t%-15s\t%d", "5th", "Username", 123), 0);
        // JLabel Sixth = new JLabel(String.format("\t%-10s\t%-15s\t%d", "6th", "Username", 123), 0);
        // JLabel Seventh = new JLabel(String.format("\t%-10s\t%-15s\t%d", "7th", "Username", 123), 0);
        // JLabel Eighth = new JLabel(String.format("\t%-10s\t%-15s\t%d", "8th", "Username", 123), 0);
        // JLabel Ninth = new JLabel(String.format("\t%-10s\t%-15s\t%d", "9th", "Username", 123), 0);
        // JLabel Tenth = new JLabel(String.format("\t%-9s\t%-15s\t%d", "10th", "Username", 123), 0);
        JPanel p = new JPanel();
        p.setBackground(Color.GRAY);
        JLabel CurrentPlayer = new JLabel(String.format("\t%-9s\t%-15s\t%d", "YOU", "Username", 123), 0);
        p.add(CurrentPlayer);
        // JLabel CurrentPlayer = new JLabel(String.format("\t%-9s\t%-15s\t%d", "YOU",
        // User.currentUser.getUsername(),123 , 0);

        // First.setFont(new Font("Arial", Font.PLAIN, 40));
        // Second.setFont(new Font("Arial", Font.PLAIN, 40));
        // Third.setFont(new Font("Arial", Font.PLAIN, 40));
        // Fourth.setFont(new Font("Arial", Font.PLAIN, 40));
        // Fifth.setFont(new Font("Arial", Font.PLAIN, 40));
        // Sixth.setFont(new Font("Arial", Font.PLAIN, 40));
        // Seventh.setFont(new Font("Arial", Font.PLAIN, 40));
        // Eighth.setFont(new Font("Arial", Font.PLAIN, 40));
        // Ninth.setFont(new Font("Arial", Font.PLAIN, 40));
        // Tenth.setFont(new Font("Arial", Font.PLAIN, 40));
        CurrentPlayer.setFont(new Font("Arial", Font.PLAIN, 40));

        // Ranks.add(First);
        // Ranks.add(Second);
        // Ranks.add(Third);
        // Ranks.add(Fourth);
        // Ranks.add(Fifth);
        // Ranks.add(Sixth);
        // Ranks.add(Seventh);
        // Ranks.add(Eighth);
        // Ranks.add(Ninth);
        // Ranks.add(Tenth);
        Ranks.setBackground(Color.GRAY);

        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.PLAIN, 40));
        Back.setPreferredSize(new Dimension(200, 50));
        Ranks.add(Back);
        Ranks.setBackground(Color.DARK_GRAY);
        Back.setBackground(Color.WHITE);

        // 创建一个JScrollPane并将Ranks面板放入其中
        JScrollPane scrollPane = new JScrollPane(Ranks);
        scrollPane.setPreferredSize(new Dimension(1920, 500)); // 设置滚动面板的首选大小
        scrollPane.setBackground(Color.DARK_GRAY);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);

        // 将滚动面板添加到JFrame的中央区域
        scrollPane.setBounds(0, 100, 1920, 580);
        frame.add(scrollPane);

        CurrentPlayer.setBounds(0, 680, 1920, 100);
        CurrentPlayer.setBackground(Color.DARK_GRAY);
        frame.add(CurrentPlayer);
        Back.setBounds(0, 780, 1920, 100);
        frame.add(Back);

        Back.addActionListener((E) -> {
            try {
                Index.CreateAndShowWindow();
                frame.dispose();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        frame.setVisible(true);
}

    public static void main(String[] args) {
        try {
            RankList.CreateAndShowWindow();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}