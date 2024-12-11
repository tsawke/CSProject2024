package com.csproject.pages;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.system.CallbackI.J;

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
        

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        JLabel First = new JLabel(String.format("%-10s\t%-15s\t%d","1st", "Username", 123));
        JLabel Second = new JLabel(String.format("%-10s\t%-15s\t%d", "2nd","Username", 123));
        JLabel Third = new JLabel(String.format("%-10s\t%-15s\t%d", "3rd","Username", 123));
        JLabel Fourth = new JLabel(String.format("%-10s\t%-15s\t%d","4th", "Username", 123));
        JLabel Fifth = new JLabel(String.format("%-10s\t%-15s\t%d","5th", "Username", 123));

        First.setFont(new Font("Arial", Font.PLAIN, 40));
        Second.setFont(new Font("Arial", Font.PLAIN, 40));
        Third.setFont(new Font("Arial", Font.PLAIN, 40));
        Fourth.setFont(new Font("Arial", Font.PLAIN, 40));
        Fifth.setFont(new Font("Arial", Font.PLAIN, 40));

        panel1.add(First);
        panel2.add(Second);
        panel3.add(Third);
        panel4.add(Fourth);
        panel5.add(Fifth);

        panel1.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.WHITE);
        panel4.setBackground(Color.WHITE);
        panel5.setBackground(Color.WHITE);

        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.CENTER);
        frame.add(panel4, BorderLayout.CENTER);
        frame.add(panel5, BorderLayout.CENTER);

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
