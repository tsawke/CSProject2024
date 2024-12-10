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
    

    private GameMap currentMap;
    private Player player;

    public GameMap getCurrentMap() {
        return currentMap;
    }
    public void setCurrentMap(GameMap currentMap) {
        this.currentMap = currentMap;
    }
    public static boolean isGuest = false;

    private static int mapIndex;

    public static int getMapIndex() {
        return mapIndex;
    }
    public static void setMapIndex(int mapIndex) {
        GameSystem.mapIndex = mapIndex;
    }

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
        public void UpdateType(FieldType type){
            if(type == FieldType.Player) {
                
            }
            this.type = type;
            this.panel.setBackground(this.type.getColor());
            this.panel.updateUI();
        }
    }
    public GameSystem(int mapIndex) {
        this.mapIndex = mapIndex;
        this.currentMap = this.maps.get(mapIndex);
        for(int i = 1; i <= this.currentMap.getHeight(); ++i)
            for(int j = 1; j <= this.currentMap.getWidth(); ++j)
                if(this.currentMap.GetMapByIndex(i, j) == 4 || this.currentMap.GetMapByIndex(i, j) == 5)
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
                    if(nextX < 1 || nextX > currentMap.getHeight() || nextY < 1 || nextY > currentMap.getWidth())return;
                    switch(field[nextX][nextY].type) {
                        case Empty -> {
                            field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player ? FieldType.Empty : FieldType.Target);
                            field[nextX][nextY].UpdateType(FieldType.Player);
                            player.setPosX(nextX);
                            player.setPosY(nextY);
                            player.setCntStep(player.getCntStep() + 1);
                        }
                        case Blocked -> {
                            //Blocked
                        }
                        case Box -> {
                            int boxNextX = -1;
                            int boxNextY = -1;
                            switch(c) {
                                case 'w' -> {
                                    boxNextX = nextX - 1;
                                    boxNextY = nextY;
                                }
                                case 's' -> {
                                    boxNextX = nextX + 1;
                                    boxNextY = nextY;
                                }
                                case 'a' -> {
                                    boxNextX = nextX;
                                    boxNextY = nextY - 1;
                                }
                                case 'd' -> {
                                    boxNextX = nextX;
                                    boxNextY = nextY + 1;
                                }
                                default -> {

                                }
                            }
                            if(boxNextX < 1 || boxNextX > currentMap.getHeight() || boxNextY < 1 || boxNextY > currentMap.getWidth())return;
                            switch(field[boxNextX][boxNextY].type) {
                                case Empty -> {
                                    field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player ? FieldType.Empty : FieldType.Target);
                                    field[nextX][nextY].UpdateType(FieldType.Player);
                                    field[boxNextX][boxNextY].UpdateType(FieldType.Box);
                                    player.setPosX(nextX);
                                    player.setPosY(nextY);
                                    player.setCntStep(player.getCntStep() + 1);
                                }
                                case Blocked -> {
                                    //Blocked
                                }
                                case Box -> {
                                    //Blocked
                                    //More than 1 box can't be pushed
                                }
                                case Target -> {
                                    field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player ? FieldType.Empty : FieldType.Target);
                                    field[nextX][nextY].UpdateType(FieldType.Player);
                                    field[boxNextX][boxNextY].UpdateType(FieldType.TargetWithBox);
                                    player.setPosX(nextX);
                                    player.setPosY(nextY);
                                    player.setCntStep(player.getCntStep() + 1);
                                }
                                case Player -> {
                                    System.err.println("Failed in running! #Map multiple players.");
                                    System.exit(1);
                                }
                                case TargetWithPlayer -> {
                                    System.err.println("Failed in running! #Map multiple players.");
                                    System.exit(1);
                                }
                                case TargetWithBox -> {
                                    //Blocked
                                    //More than 1 box can't be pushed
                                }
                            }
                        }
                        case Target -> {
                            field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player ? FieldType.Empty : FieldType.Target);
                            field[nextX][nextY].UpdateType(FieldType.TargetWithPlayer);
                            player.setPosX(nextX);
                            player.setPosY(nextY);
                            player.setCntStep(player.getCntStep() + 1);
                        }
                        case Player -> {
                            System.err.println("Failed in running! #Map multiple players.");
                            System.exit(1);
                        }
                        case TargetWithPlayer -> {
                            System.err.println("Failed in running! #Map multiple players.");
                            System.exit(1);
                        }
                        case TargetWithBox -> {
                            int boxNextX = -1;
                            int boxNextY = -1;
                            switch(c) {
                                case 'w' -> {
                                    boxNextX = nextX - 1;
                                    boxNextY = nextY;
                                }
                                case 's' -> {
                                    boxNextX = nextX + 1;
                                    boxNextY = nextY;
                                }
                                case 'a' -> {
                                    boxNextX = nextX;
                                    boxNextY = nextY - 1;
                                }
                                case 'd' -> {
                                    boxNextX = nextX;
                                    boxNextY = nextY + 1;
                                }
                                default -> {

                                }
                            }
                            if(boxNextX < 1 || boxNextX > currentMap.getHeight() || boxNextY < 1 || boxNextY > currentMap.getWidth())return;
                            switch(field[boxNextX][boxNextY].type) {
                                case Empty -> {
                                    field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player ? FieldType.Empty : FieldType.Target);
                                    field[nextX][nextY].UpdateType(FieldType.TargetWithPlayer);
                                    field[boxNextX][boxNextY].UpdateType(FieldType.Box);
                                    player.setPosX(nextX);
                                    player.setPosY(nextY);
                                    player.setCntStep(player.getCntStep() + 1);
                                }
                                case Blocked -> {
                                    //Blocked
                                }
                                case Box -> {
                                    //Blocked
                                    //More than 1 box can't be pushed
                                }
                                case Target -> {
                                    field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player ? FieldType.Empty : FieldType.Target);
                                    field[nextX][nextY].UpdateType(FieldType.TargetWithPlayer);
                                    field[boxNextX][boxNextY].UpdateType(FieldType.TargetWithBox);
                                    player.setPosX(nextX);
                                    player.setPosY(nextY);
                                    player.setCntStep(player.getCntStep() + 1);
                                }
                                case Player -> {
                                    System.err.println("Failed in running! #Map multiple players.");
                                    System.exit(1);
                                }
                                case TargetWithPlayer -> {
                                    System.err.println("Failed in running! #Map multiple players.");
                                    System.exit(1);
                                }
                                case TargetWithBox -> {
                                    //Blocked
                                    //More than 1 box can't be pushed
                                }
                            }
                        }
                    }
                    // TestShowcase(field);
                    IntStream.range(1, currentMap.getHeight() + 1).forEach(
                        i -> {
                            IntStream.range(1, currentMap.getWidth() + 1).forEach(
                                j -> {
                                    currentMap.SetMapByIndex(i, j, field[i][j].type.ConvertToInt());
                                }
                            );
                        }
                    );
                    try {
                        Archive.SetArchiveByID(User.currentUser.getUID(), mapIndex, currentMap);
                    } catch (Exception e1) {}
                }
            }
        );
        frame.setVisible(true);


    }
    public void TestShowcase(Field field[][]) {
        System.out.printf("Current Map %d * %d :\n", currentMap.getHeight(), currentMap.getWidth());
        for(int i = 1; i <= currentMap.getHeight(); ++i)
            for(int j = 1; j <= currentMap.getWidth(); ++j)
                System.out.printf("%d%c", field[i][j].type, j == currentMap.getWidth() ? '\n' : ' ');
        System.out.printf("Player position %d, %d\n", player.getPosX(), player.getPosY());
    }
    
}
