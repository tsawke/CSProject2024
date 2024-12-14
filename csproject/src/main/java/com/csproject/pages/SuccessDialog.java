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
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csproject.BeautifyUtils;
import com.csproject.GameSystem;

public class SuccessDialog {
    public static JButton CreateDefaultMenuButton(String Name) {
        JButton button = new JButton(Name);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        return button;
    }
    // public static JPanel CreateDefaultTextFieldWithLable(String Name) {
    //     JPanel panel = new JPanel();
    //     panel.setLayout(new FlowLayout());

    //     JLabel lable = new JLabel(Name);
    //     lable.setFont(new Font("Arial", Font.PLAIN, 35));

    //     JTextField textField = new JTextField(30);
    //     textField.setPreferredSize(new Dimension(30, 30));
    //     textField.setBorder(BeautifyUtils.defaultGrayBorder);

    //     panel.add(lable);
    //     panel.add(textField);

    //     return panel;
    // }

    // private static JTextField usernameTextField = new JTextField();
    // private static JTextField passwordTextField = new JTextField();
    // private static JLabel usernameLabel = new JLabel();
    // private static JLabel passwordLabel = new JLabel();
    
    public static void CreateAndShowDialog(int currentMapIndex) throws Exception {
        JDialog dialog = new JDialog(Index.frame, "Success");
        dialog.setModal(true);
        dialog.setSize(1200, 300);
        dialog.setLocationRelativeTo(Index.frame);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = currentMapIndex == 5 ? new JLabel("Congratulations! You've completed the levels!") : new JLabel("Excellent! You win!");
        title.setFont(new Font("Arial", Font.PLAIN, 50));
        // title.setSize(new Dimension(1200, 100));

        panel.add(title);

        dialog.add(panel);

        Container content = dialog.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        // passwordLabel.setToolTipText("6-15 digits, only letters and digits are available, not allowed to consist of only letters or numbers.");

        JButton next = CreateDefaultMenuButton("Next");
        JButton restart = CreateDefaultMenuButton("Restart");
        JButton exit = CreateDefaultMenuButton("Exit");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(600, 80);
        // buttonPanel.setPreferredSize(new Dimension(600, 80));
        buttonPanel.setLayout(new GridLayout(1, 3, 30, 20));
        buttonPanel.add(next);
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

        if(currentMapIndex == 5) {
            next.setBackground(Color.GRAY);
            next.setBorder(BeautifyUtils.defaultGrayBorder);
        } else {
            next.addActionListener(
                e -> {
                    dialog.dispose();
                    GameSystem gameSystem = new GameSystem(currentMapIndex + 1);
                    gameSystem.CreateAndShowWindow();
                }
            );
        }

        restart.addActionListener(
            e -> {
                dialog.dispose();
                GameSystem gameSystem = new GameSystem(currentMapIndex);
                gameSystem.CreateAndShowWindow();
            }
        );
        exit.addActionListener(
            e -> {
                dialog.dispose();
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
