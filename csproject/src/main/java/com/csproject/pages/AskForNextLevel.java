package com.csproject.pages;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csproject.GameSystem;

import javax.swing.*;

import java.awt.*;


public class AskForNextLevel extends JDialog{
    public AskForNextLevel() {
        setSize(600, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());
    }

    public static void CreateAndShowDialog() {

        AskForNextLevel NextLevel = new AskForNextLevel();
        JPanel panel = new JPanel();
        

        JLabel title = new JLabel("Move to Next Level?");
        title.setFont(new Font("Arial", Font.PLAIN, 50));

        panel.add(title);

        NextLevel.add(panel);

        Container content = NextLevel.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        JPanel buttons = new JPanel(null);

        JButton yes = new JButton("Yes");
        JButton Back = new JButton("Back to menu");

        yes.setBounds(50, 100, 150, 50);
        Back.setBounds(250, 100, 300, 50);

        yes.setFont(new Font("Arial", Font.PLAIN, 40));
        Back.setFont(new Font("Arial", Font.PLAIN, 40));

        yes.setPreferredSize(new Dimension(200, 50));
        Back.setPreferredSize(new Dimension(200, 50));

        yes.setBackground(Color.GREEN);
        Back.setBackground(Color.RED);

        yes.addActionListener((e) -> {
            try {
                GameSystem.NextLevelGame();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            NextLevel.dispose();
        });

        Back.addActionListener((e) -> {  
            try {
                AskForSave.CreateAndShowDialog();
                NextLevel.dispose();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        buttons.add(yes);
        buttons.add(Back);
        content.add(buttons);

        NextLevel.setVisible(true);
    }
    
    public static void main(String[] args) {
        CreateAndShowDialog();
    }
}

