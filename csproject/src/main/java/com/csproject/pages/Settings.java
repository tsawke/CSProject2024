package com.csproject.pages;





// To Be Continued!




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Settings {
    public static JButton CreateDefaultMenuButton(String Name) {
        JButton button = new JButton(Name);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        return button;
    }

    public static void CreateAndShowDialog(JFrame father) throws Exception {
        JDialog dialog = new JDialog(Index.frame, "Fail");
        dialog.setModal(true);
        dialog.setSize(1200, 800);
        dialog.setLocationRelativeTo(father);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("Settings");

        title.setFont(new Font("Arial", Font.PLAIN, 80));
        // title.setSize(new Dimension(1200, 100));

        panel.add(title);

        dialog.add(panel);

        Container content = dialog.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JComboBox bgm = new JComboBox<>();


        
        dialog.setVisible(true);
    }
}
