package com.csproject.pages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang3.tuple.Pair;

import com.csproject.Archive;
import com.csproject.BeautifyUtils;
import com.csproject.GameMap;
import com.csproject.GameSystem;
import com.csproject.User;
import com.csproject.dependencies.VerticalFlowLayout;
import com.formdev.flatlaf.FlatLightLaf;

public class Index {
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

        frame = new JFrame("Sokoban Game");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);

        JLabel title = new JLabel("Welcome to Sokoban Game!");
        title.setFont(new Font("Arial", Font.PLAIN, 80));

        panel.add(title);

        Container content = frame.getContentPane();
        content.add(panel, BorderLayout.NORTH);

        Pair<JPanel, JButton> newGame = createDefaultMenuButtonAndPanel("New Game");
        Pair<JPanel, JButton> loadArchive = createDefaultMenuButtonAndPanel("Load Archive");
        Pair<JPanel, JButton> rankList = createDefaultMenuButtonAndPanel("Ranklist");
        Pair<JPanel, JButton> setting = createDefaultMenuButtonAndPanel("Setting");
        Pair<JPanel, JButton> exit = createDefaultMenuButtonAndPanel("Exit");

        if (User.currentUser == null || GameSystem.isGuest || false/* TODO */) {
            loadArchive.getRight().setBackground(Color.GRAY);
            loadArchive.getRight().setBorder(BeautifyUtils.defaultGrayBorder);
        } else {
            loadArchive.getRight().addActionListener((e) -> {
                GameSystem gameSystem = new GameSystem(1);
                try {
                    Archive.LoadArchiveByID(User.currentUser.getUID(), gameSystem);
                } catch (Exception e1) {
                }
                gameSystem.CreateAndShowWindow();
            });
        }

        newGame.getRight().addActionListener((e) -> {
            IntStream.range(1, 5 + 1).forEach(
                    i -> {
                        try {
                            ChooseMap.CreateIcons(GameMap.maps.get(i), i);
                        } catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    });

            ChooseMap.CreateAndShowWindow();
        });

        rankList.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");
            try {
                RankList.CreateAndShowWindow();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        setting.getRight().addActionListener((e) -> {
            System.out.println("Press Button.");
        });

        exit.getRight().addActionListener((e) -> {
            System.exit(0);
        });

        VerticalFlowLayout verticalLayout = new VerticalFlowLayout(10, 25);
        // verticalLayout.setFill(false);
        JPanel buttons = new JPanel(verticalLayout);
        buttons.setBackground(Color.DARK_GRAY);
        buttons.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));
        buttons.add(newGame.getLeft());
        buttons.add(loadArchive.getLeft());
        buttons.add(rankList.getLeft());
        buttons.add(setting.getLeft());
        buttons.add(exit.getLeft());

        // BackgroundImagePanel background = new
        // BackgroundImagePanel("D:/CSProject2024/resources/Background.png");
        // content.add(background, BorderLayout.CENTER,0);

        content.add(buttons, BorderLayout.CENTER,1);

        frame.setVisible(true);

        BackgroundImagePanel background = new BackgroundImagePanel("D:/CSProject2024/resources/Background.png");
        content.add(background, BorderLayout.CENTER,0);

        LogIn.CreateAndShowDialog();
        // SignUp.CreateAndShowDialog();
    }
    
    public static void main(String[] args) throws Exception {
        Index.CreateAndShowWindow();
    }
}
