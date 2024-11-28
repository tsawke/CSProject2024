package com.csproject.pages;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.formdev.flatlaf.FlatLightLaf;

public class LogIn extends Index{
    public static JButton CreateDefaultMenuButton(String Name) {
        JButton button = new JButton(Name);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        // button.setPreferredSize(new Dimension(500, 50));
        JPanel buttonPanel = new JPanel();
        // buttonPanel.add(button);
        // buttonPanel.setBackground(Color.DARK_GRAY);
        // return Pair.of(buttonPanel, button);
        return button;
    }
    public static void CreateAndShowWindow() {
        FlatLightLaf.setup();

        JFrame frame = new JFrame("Sokoban Game");
        JPanel panel = new JPanel();
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());
        // 设置标题
        JLabel title = new JLabel("Log In");
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        Container content = frame.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        JTextField usernameField = new JTextField(20);
        JTextField passwordField = new JTextField(20);
        JButton login = CreateDefaultMenuButton("Log In");
        JButton visit = CreateDefaultMenuButton("Visitor Mode");
        JButton signup = CreateDefaultMenuButton("Sign Up");

        login.addActionListener(l);

        // Pair<JPanel, JButton> LogIn = createDefaultMenuButtonAndPanel("Log In");

        // Pair<JPanel, JButton> Visitor = createDefaultMenuButtonAndPanel("Vistor");

        // VerticalFlowLayout verticalLayout = new VerticalFlowLayout(10, 25);
        // // verticalLayout.setFill(false);
        // JPanel buttons = new JPanel(verticalLayout);
        // buttons.setBackground(Color.DARK_GRAY);
        // buttons.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        // buttons.add(LogIn.getLeft());
        // buttons.add(Visitor.getLeft());

        // content.add(buttons, BorderLayout.CENTER);

        // panel.setBackground(Color.gray);

        // Visitor.getRight().addActionListener((e) -> {
        //     System.out.println("Press Button.");
        //     Index.CreateAndShowWindow();
        // });

        // LogIn.getRight().addActionListener((e) -> {
        //     System.out.println("Press Button.");
        //     Index.CreateAndShowWindow();
        // });



        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
}
