package com.csproject.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.csproject.BeautifyUtils;

public class LogIn {
    public static int LogInUser(String username, String password_plain) throws Exception {
        if(H2Database.IfExistUserByUsername(username))return 1;
        
    }
    public static JButton CreateDefaultMenuButton(String Name) {
        JButton button = new JButton(Name);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        return button;
    }
    public static JPanel CreateDefaultTextFieldWithLable(String Name) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel lable = new JLabel(Name);
        lable.setFont(new Font("Arial", Font.PLAIN, 35));

        JTextField textField = new JTextField(30);
        textField.setPreferredSize(new Dimension(30, 30));
        textField.setBorder(BeautifyUtils.defaultGrayBorder);

        panel.add(lable);
        panel.add(textField);

        return panel;
    }

    private static JTextField usernameTextField = new JTextField();
    private static JTextField passwordTextField = new JTextField();
    private static JLabel usernameLabel = new JLabel();
    private static JLabel passwordLabel = new JLabel();
    
    public static void CreateAndShowDialog() {
        JDialog dialog = new JDialog(Index.frame, "Log In");
        dialog.setModal(true);
        dialog.setSize(800, 400);
        dialog.setLocationRelativeTo(Index.frame);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("Log In");
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        dialog.add(panel);

        Container content = dialog.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        JPanel usernamePanel = CreateDefaultTextFieldWithLable("Username: ");
        
        if((Stream.of(usernamePanel.getComponents())
            .filter(c -> c instanceof JTextField)
            .collect(Collectors.toList())
            .getFirst()
        ) instanceof JTextField jTextField)usernameTextField = jTextField;

        if((Stream.of(usernamePanel.getComponents())
            .filter(c -> c instanceof JLabel)
            .collect(Collectors.toList())
            .getFirst()
        ) instanceof JLabel jLabel)usernameLabel = jLabel;
        // usernameLabel.setBorder(BeautifyUtils.defaultDarkGrayBorder);
        // usernameLabel.setBackground(Color.DARK_GRAY);

        // JTextField usernameTextField = (JTextField)tmp;
        
        // JLabel usernameLabel = (
        //     (JLabel)(Object)(Stream.of(usernamePanel.getComponents())
        //     .filter(c -> c instanceof JLabel)
        //     .findFirst()
        // ));
        // usernameLabel.setToolTipText("6-20 digits, only letters and digits are available.");
        // usernameTextField.setText("6-20 digits, only letters and digits are available");

        // usernameTextField.addFocusListener(
        //     new FocusListener() {
        //         public void focusGained(FocusEvent e) {

        //         }
        //     };
        // );

        JPanel passwordPanel = CreateDefaultTextFieldWithLable("Password: ");
        
        if((Stream.of(passwordPanel.getComponents())
            .filter(c -> c instanceof JTextField)
            .collect(Collectors.toList())
            .getFirst()
        ) instanceof JTextField jTextField)passwordTextField = jTextField;

        if((Stream.of(passwordPanel.getComponents())
            .filter(c -> c instanceof JLabel)
            .collect(Collectors.toList())
            .getFirst()
        ) instanceof JLabel jLabel)passwordLabel = jLabel;

        // passwordLabel.setToolTipText("6-15 digits, only letters and digits are available, not allowed to consist of only letters or numbers.");

        JButton login = CreateDefaultMenuButton("Log In");
        JButton visit = CreateDefaultMenuButton("Visitor");
        JButton signup = CreateDefaultMenuButton("Sign Up");

        JPanel buttonPanel = new JPanel();
        //TODO Why PreferredSize works, but Size doesn't?
        buttonPanel.setPreferredSize(new Dimension(600, 80));
        buttonPanel.setLayout(new GridLayout(1, 3, 30, 20));
        buttonPanel.add(login);
        buttonPanel.add(signup);
        buttonPanel.add(visit);

        // JPanel buttonBorderPanel = new JPanel();
        // buttonBorderPanel.setLayout(new FlowLayout());
        
        // // buttonBorderPanel.add(buttonPanel, BorderLayout.CENTER);
        // buttonBorderPanel.add(buttonPanel);
        // buttonBorderPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(buttonPanel);

        content.add(mainPanel, BorderLayout.CENTER);

        // JLabel tips = new JLabel("Please move your mouse to \'Username\' or \'Password\' to check the requirments!");
        // tips.setLayout(new BorderLayout());

        // tips.setFont(new Font("Arial", Font.PLAIN, 20));
        // tips.setBorder(BeautifyUtils.defaultGrayBorder);
        // tips.setHorizontalAlignment(SwingConstants.CENTER);
        // // dialog.add(tips);
        // content.add(tips, BorderLayout.SOUTH);

        // JPanel placeHolder = new JPanel();
        // placeHolder.setSize(400, placeHolder.getSize().height);
        // placeHolder.setPreferredSize(new Dimension(40, placeHolder.getSize().height));
        // content.add(placeHolder, BorderLayout.WEST);

        // mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        login.addActionListener(
            e -> {
                String username = usernameTextField.getText();
                String password_plain = passwordTextField.getText();
                switch(LogInUser(username, password_plain)) {

                }
            }
        );


        dialog.setVisible(true);
    }
}
