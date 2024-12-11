package com.csproject.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AskForSave extends JDialog {
    public AskForSave() {
        setSize(600, 300);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());
    }

    public static void CreateAndShowDialog() {

        AskForSave Save = new AskForSave();
        JPanel panel = new JPanel();
        

        JLabel title = new JLabel("Save?");
        title.setFont(new Font("Arial", Font.PLAIN, 50));

        panel.add(title);

        Save.add(panel);

        Container content = Save.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        JPanel buttons = new JPanel(null);

        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");

        yes.setBounds(75, 100, 200, 50);
        no.setBounds(325, 100, 200, 50);

        yes.setFont(new Font("Arial", Font.PLAIN, 40));
        no.setFont(new Font("Arial", Font.PLAIN, 40));
        yes.setPreferredSize(new Dimension(200, 50));
        no.setPreferredSize(new Dimension(200, 50));
        yes.setBackground(Color.GREEN);
        no.setBackground(Color.RED);
        yes.addActionListener((e) -> {
            try {
                //GameSystem.SaveGame();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            Save.dispose();
        });
        no.addActionListener((e) -> {
            Save.dispose();
        });

        buttons.add(yes);
        buttons.add(no);
        content.add(buttons);

        Save.setVisible(true);
    }
    
    public static void main(String[] args) {
        CreateAndShowDialog();
    }
}
