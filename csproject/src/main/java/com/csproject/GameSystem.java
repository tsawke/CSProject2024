package com.csproject;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatLightLaf;

public class GameSystem {
    private List < GameMap > maps;
    private GameMap currentMap;
    private Player player;

    private class Field{
        public JPanel panel;
        public FieldType type;
        public Field(FieldType type) {
            this.panel = new JPanel();
            this.panel.setBackground(type.getColor());
            this.type = type;
        }
        public void UpdateType(int type){
            this.type = FieldType.values()[type];
            this.panel.setBackground(this.type.getColor());
            this.panel.updateUI();
        }
    }

    public GameSystem(int mapIndex) {
        this.maps = Arrays.asList(
            new GameMap(0, 0),
            new GameMap(5, 6,
                new int[][]{
                    {-1, -1, -1, -1, -1, -1, -1},
                    {-1, 1, 1, 1, 1, 1, 1},
                    {-1, 1, 4, 0, 0, 0, 1},
                    {-1, 1, 0, 0, 2, 3, 1},
                    {-1, 1, 0, 3, 2, 0, 1},
                    {-1, 1, 1, 1, 1, 1, 1}
                }
            )
        );
        this.currentMap = this.maps.get(mapIndex);
        for(int i = 1; i <= this.currentMap.getHeight(); ++i)
            for(int j = 1; j <= this.currentMap.getWidth(); ++j)
                if(this.currentMap.GetMapByIndex(i, j) == 4)
                    this.player = new Player(i, j);
        if(this.player == null){
            System.err.println("Building map failed! #Map without player.");
            System.exit(1);
        }
    }
    public void CreateAndShowWindow() {
        FlatLightLaf.setup();

        JFrame frame = new JFrame("Sokoban Game");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(currentMap.getHeight(), currentMap.getWidth(), 10, 10));
        Field[][] field = new Field[currentMap.getHeight() + 1][currentMap.getWidth() + 1];
        
        IntStream.range(1, currentMap.getHeight() + 1).forEach(
            i -> {
                IntStream.range(1, currentMap.getWidth() + 1).forEach(
                    j -> {
                        field[i][j] = new Field(FieldType.values()[currentMap.GetMapByIndex(i, j)]);
                        mainPanel.add(field[i][j].panel);
                    }
                );
            }
        );
        frame.add(mainPanel);
        

        frame.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e){
                    char c = e.getKeyChar();
                    int curX = player.getPosX();
                    int curY = player.getPosY();
                    int nextX = -1;
                    int nextY = -1;
                    switch(c) {
                        case 'w' -> {
                            nextX = player.getPosX() - 1;
                            nextY = player.getPosY();
                        }
                        case 's' -> {
                            nextX = player.getPosX() + 1;
                            nextY = player.getPosY();
                        }
                        case 'a' -> {
                            nextX = player.getPosX();
                            nextY = player.getPosY() - 1;
                        }
                        case 'd' -> {
                            nextX = player.getPosX();
                            nextY = player.getPosY() + 1;
                        }
                        default -> {

                        }
                    }
                    if(nextX != -1 && nextY != -1){
                        switch(currentMap.GetMapByIndex(nextX, nextY)) {
                            case 0 -> {
                                field[curX][curY].UpdateType(0);
                                field[nextX][nextY].UpdateType(4);
                                player.setPosX(nextX);
                                player.setCntStep(player.getCntStep() + 1);
                            }
                            case 1 -> {
                                //Blocked
                            }
                            case 2 -> {
                                
                            }
                            case 3 -> {
                                field[curX][curY].UpdateType(0);
                                field[nextX][nextY].UpdateType(5);
                                player.setPosX(nextX);
                                player.setCntStep(player.getCntStep() + 1);
                            }
                            case 4 -> {
                                System.err.println("Failed in running! #Map multiple players.");
                                System.exit(1);
                            }
                            case 5 -> {
                                System.err.println("Failed in running! #Map multiple players.");
                                System.exit(1);
                            }
                            case 6 -> {

                            }
                        }
                    }
                    
                }
            }
        );
        frame.setVisible(true);


    }
    public void TestShowcase() {
        System.out.printf("Current Map %d * %d :\n", currentMap.getHeight(), currentMap.getWidth());
        for(int i = 1; i <= currentMap.getHeight(); ++i)
            for(int j = 1; j <= currentMap.getWidth(); ++j)
                System.out.printf("%d%c", currentMap.GetMapByIndex(i, j), j == currentMap.getWidth() ? '\n' : ' ');
        System.out.printf("Player position %d, %d\n", player.getPosX(), player.getPosY());
    }
    
}
