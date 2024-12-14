package com.csproject.pages;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csproject.GameSystem;

public class FailDialog {
    public static JButton CreateDefaultMenuButton(String Name) {
        JButton button = new JButton(Name);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        return button;
    }
    
    public static void CreateAndShowDialog(int currentMapIndex, JFrame father) throws Exception {
        JDialog dialog = new JDialog(Index.frame, "Fail");
        dialog.setModal(true);
        dialog.setSize(1200, 300);
        dialog.setLocationRelativeTo(father);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("Oops! It seems like you've failed.");

        title.setFont(new Font("Arial", Font.PLAIN, 50));
        // title.setSize(new Dimension(1200, 100));

        panel.add(title);

        dialog.add(panel);

        Container content = dialog.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        // passwordLabel.setToolTipText("6-15 digits, only letters and digits are available, not allowed to consist of only letters or numbers.");

        // JButton next = CreateDefaultMenuButton("Next");
        JButton restart = CreateDefaultMenuButton("Restart");
        JButton exit = CreateDefaultMenuButton("Exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(600, 80);
        // buttonPanel.setPreferredSize(new Dimension(600, 80));
        buttonPanel.setLayout(new GridLayout(1, 2, 30, 20));
        // buttonPanel.add(next);
        buttonPanel.add(restart);
        buttonPanel.add(exit);

        // JPanel buttonBorderPanel = new JPanel();
        // buttonBorderPanel.setLayout(new FlowLayout());
        
        // // buttonBorderPanel.add(buttonPanel, BorderLayout.CENTER);
        // buttonBorderPanel.add(buttonPanel);
        // buttonBorderPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        JPanel placeHolder = new JPanel();
        placeHolder.setPreferredSize(new Dimension(1200, 50));

        mainPanel.add(placeHolder);
        mainPanel.add(buttonPanel);

        content.add(mainPanel, BorderLayout.CENTER);

        
        restart.addActionListener(
            e -> {
                dialog.dispose();
                father.dispose();
                GameSystem gameSystem = new GameSystem(currentMapIndex);
                gameSystem.CreateAndShowWindow();
            }
        );
        
        exit.addActionListener(
            e -> {
                dialog.dispose();
                father.dispose();
                try {
                    Index.CreateAndShowWindow();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        );
        
        mainPanel.setFocusable(true);
        mainPanel.requestFocus();

        dialog.setVisible(true);
    }
}
