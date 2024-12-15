package com.csproject.pages;





// To Be Continued!




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csproject.BackgroundMusic;

public class Settings {
    public static JButton CreateDefaultMenuButton(String Name) {
        JButton button = new JButton(Name);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        return button;
    }
    
    private static List < String > GetAudioFiles(String path) {
        List < String > audioFiles = new ArrayList<>();
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".wav"));
            if (files != null)
                for (File file : files){
                    audioFiles.add(file.getAbsolutePath());
                    System.err.println(file.getAbsolutePath());
                }
        }
        return audioFiles;
    }

    private static List < String > GetFileNames(List < String > filePaths) {
        return filePaths.stream()
            .map(s -> new File(s).getName())
            .collect(Collectors.toList());
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
        mainPanel.setLayout(new FlowLayout());

        List < String > audioFilePaths = GetAudioFiles("./csproject/src/main/resources/Musics/");
        List < String > audioFileNames = GetFileNames(audioFilePaths);

        for(String s : audioFileNames)System.err.println(s);

        // Notice: toArray Retrun Type
        JComboBox audioComboBox = new JComboBox<>(audioFileNames.toArray(new String[0]));

        audioComboBox.setPreferredSize(new Dimension(800, 30));

        audioComboBox.addActionListener(e -> {
            int selectedIndex = audioComboBox.getSelectedIndex();
            if(selectedIndex >= 0 && selectedIndex < audioFileNames.size()) {
                if (BackgroundMusic.bgm != null) {
                    BackgroundMusic.bgm.Stop();
                }
                BackgroundMusic.bgm = new BackgroundMusic(audioFilePaths.get(selectedIndex));
                BackgroundMusic.bgm.Play();
            }
        });

        mainPanel.add(audioComboBox);

        content.add(mainPanel, BorderLayout.CENTER);

        
        dialog.setVisible(true);
    }
}
