package com.csproject.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class ErrorDialog {
    public static void CreateAndShowDialog(Component father, String text) {
        JDialog dialog = new JDialog(Index.frame, "Error!");
        dialog.setDefaultCloseOperation(JFrame.ERROR);
        dialog.setModal(true);
        dialog.setSize(600, 300);
        dialog.setLocationRelativeTo(father);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("Error");
        title.setFont(new Font("Arial", Font.PLAIN, 50));

        panel.add(title);

        dialog.add(panel);

        Container content = dialog.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        // WrapLabel notice = new WrapLabel(text);
        JLabel notice = new JLabel("<html>" + text + "</html>");
        notice.setFont(new Font("Arial", Font.PLAIN, 20));
        notice.setHorizontalAlignment(SwingConstants.CENTER);
        
        content.add(notice, BorderLayout.CENTER);

        dialog.setVisible(true);
    }
}
