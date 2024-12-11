package com.csproject.pages;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.system.CallbackI.J;

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
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("RankList");
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        Container content = frame.getContentPane();
        content.add(panel, BorderLayout.NORTH);
        

        VerticalFlowLayout verticalLayout = new VerticalFlowLayout(10, 25);
        // verticalLayout.setFill(false);
        JPanel Ranks = new JPanel(verticalLayout);
        Ranks.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
     

        JLabel First = new JLabel(String.format("\t%-10s\t%-15s\t%d", "1st", "Username", 123),0);
        JLabel Second = new JLabel(String.format("\t%-10s\t%-15s\t%d", "2nd", "Username", 123),0);
        JLabel Third = new JLabel(String.format("\t%-10s\t%-15s\t%d", "3rd", "Username", 123),0);
        JLabel Fourth = new JLabel(String.format("\t%-10s\t%-15s\t%d", "4th", "Username", 123),0);
        JLabel Fifth = new JLabel(String.format("\t%-10s\t%-15s\t%d", "5th", "Username", 123),0);

        First.setFont(new Font("Arial", Font.PLAIN, 40));
        Second.setFont(new Font("Arial", Font.PLAIN, 40));
        Third.setFont(new Font("Arial", Font.PLAIN, 40));
        Fourth.setFont(new Font("Arial", Font.PLAIN, 40));
        Fifth.setFont(new Font("Arial", Font.PLAIN, 40));

        Ranks.add(First);
        Ranks.add(Second);
        Ranks.add(Third);
        Ranks.add(Fourth);
        Ranks.add(Fifth);
        Ranks.setBackground(Color.GRAY);

        

        JButton Back = new JButton("Back");
        Back.setFont(new Font("Arial", Font.PLAIN, 40));
        Back.setPreferredSize(new Dimension(200, 50));
        Ranks.add(Back);
        Ranks.setBackground(Color.DARK_GRAY);
        Back.setBackground(Color.WHITE);

        content.add(Ranks, BorderLayout.CENTER);
        
        Back.addActionListener((e) -> {
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
