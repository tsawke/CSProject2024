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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.csproject.Archive;
import com.csproject.BeautifyUtils;
import com.csproject.EncryptUtils;
import com.csproject.H2Database;
import com.csproject.User;
import com.csproject.dependencies.Validator;

public class SignUp {
    public static int SignUpUser(String username, String password_plain) throws Exception{
        if(!Validator.isUsername(username))return 1;
        if(!Validator.isPassword(password_plain))return 2;
        if(H2Database.IfExistUserByUsername(username))return 3;
        H2Database.InsertUser(
            new User(
                User.baseUID + H2Database.GetUsers().size() + 1,
                username,
                EncryptUtils.sha256(password_plain)
            )
        );
        Archive.CreateDefaultArchiveByID(H2Database.SelectUserByUsername(username).getUID());
        return 0;
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

        JTextField textField = new JTextField(40);
        textField.setPreferredSize(new Dimension(40, 30));
        textField.setBorder(BeautifyUtils.defaultGrayBorder);

        panel.add(lable);
        panel.add(textField);

        return panel;
    }

    //TODO
    private static JTextField usernameTextField = new JTextField();
    private static JTextField passwordTextField = new JTextField();
    private static JLabel usernameLabel = new JLabel();
    private static JLabel passwordLabel = new JLabel();
    
    public static void CreateAndShowDialog(JFrame father) {
        JDialog dialog = new JDialog(Index.frame, "SignUp");
        dialog.setModal(true);
        dialog.setSize(800, 400);
        dialog.setLocationRelativeTo(Index.frame);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("Sign Up");
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
        usernameLabel.setToolTipText("6-20 digits, only letters and digits are available.");

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

        passwordLabel.setToolTipText("6-15 digits, only letters and digits are available, not allowed to consist of only letters or numbers.");

        JButton signup = CreateDefaultMenuButton("Sign Up");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(300, 80));
        buttonPanel.setLayout(new GridLayout(1, 1, 30, 20));
        buttonPanel.add(signup);

        // JPanel buttonBorderPanel = new JPanel();
        // buttonBorderPanel.setLayout(new FlowLayout());
        
        // // buttonBorderPanel.add(buttonPanel, BorderLayout.CENTER);
        // buttonBorderPanel.add(buttonPanel);
        // buttonBorderPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));

        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(buttonPanel);

        content.add(mainPanel, BorderLayout.CENTER);

        JLabel tips = new JLabel("Please move your mouse to \'Username\' or \'Password\' to check the requirments!");
        tips.setLayout(new BorderLayout());

        tips.setFont(new Font("Arial", Font.PLAIN, 20));
        tips.setBorder(BeautifyUtils.defaultGrayBorder);
        tips.setHorizontalAlignment(SwingConstants.CENTER);
        // dialog.add(tips);
        content.add(tips, BorderLayout.SOUTH);

        // JPanel placeHolder = new JPanel();
        // placeHolder.setSize(400, placeHolder.getSize().height);
        // placeHolder.setPreferredSize(new Dimension(40, placeHolder.getSize().height));
        // content.add(placeHolder, BorderLayout.WEST);

        // mainPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        signup.addActionListener(
            e -> {
                String username = usernameTextField.getText();
                String password_plain = passwordTextField.getText();
                try {
                    switch(SignUpUser(username, password_plain)) {
                        case 0 -> {
                            LogIn.LogInUser(username, password_plain, father);
                            dialog.dispose();
                        }
                        case 1 -> {
                            ErrorDialog.CreateAndShowDialog(dialog, "Username is invalid!");
                        }
                        case 2 -> {
                            ErrorDialog.CreateAndShowDialog(dialog, "Password is invalid!");
                        }
                        case 3 -> {
                            ErrorDialog.CreateAndShowDialog(dialog, "Username is already exists!");
                        }
                        default -> {}
                    }
                } catch (Exception ex) {}
            }
        );


        dialog.setVisible(true);
    }
}
