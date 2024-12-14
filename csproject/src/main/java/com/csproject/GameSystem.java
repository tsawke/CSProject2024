package com.csproject;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.csproject.dependencies.SwingUtil;
import com.csproject.pages.FailDialog;
import com.csproject.pages.SuccessDialog;
import com.formdev.flatlaf.FlatLightLaf;
import com.jogamp.newt.event.WindowEvent;

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
        public int size;
        public Field(FieldType type, int size) {
            this.panel = new JPanel();
            this.size = size - 20;

            this.type = type;

            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(this.size, this.size));
            label.setIcon(this.type.getIcon());
            
            this.panel.add(label);
            
            this.panel.setBackground(this.type.getColor());
            this.panel.updateUI();
        }
        // public void UpdateType(int type){
        //     this.type = FieldType.values()[type];
        //     this.panel.setBackground(this.type.getColor());
        //     this.panel.updateUI();
        // }
        public void UpdateType(FieldType type){
            this.type = type;

            this.panel.removeAll();

            JLabel label = new JLabel();
            label.setPreferredSize(new Dimension(this.size, this.size));
            label.setIcon(this.type.getIcon());
            
            this.panel.add(label);

            this.panel.setBackground(this.type.getColor());
            this.panel.updateUI();
        }
    }
    public GameSystem(int mapIndex) {
        this.mapIndex = mapIndex;
        try {
            this.currentMap = new GameMap(GameMap.maps.get(mapIndex).getHeight(), GameMap.maps.get(mapIndex).getWidth());
            // BeanUtils.copyProperties(this.currentMap, GameMap.maps.get(mapIndex));
        } catch (Exception ee) {
            ee.printStackTrace();
        }
        IntStream.range(1, currentMap.getHeight() + 1).forEach(
            i -> {
                IntStream.range(1, currentMap.getWidth() + 1).forEach(
                    j -> {
                        this.currentMap.SetMapByIndex(i, j, GameMap.maps.get(mapIndex).GetMapByIndex(i, j));
                    }
                );
            }
        );
        for(int i = 1; i <= this.currentMap.getHeight(); ++i)
            for(int j = 1; j <= this.currentMap.getWidth(); ++j)
                if(this.currentMap.GetMapByIndex(i, j) == 4 || this.currentMap.GetMapByIndex(i, j) == 5)
                    this.player = new Player(i, j);
        if(this.player == null){
            System.err.println("Building map failed! #Map without player.");
            System.exit(1);
        }
    }
    
    public static JButton CreateDefaultMenuButton(String Name) {
        JButton button = new JButton(Name);
        button.setFont(new Font("Arial", Font.PLAIN, 40));
        return button;
    }
    
    private int counter = 0;

    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }

    private boolean isEmpty(int x, int y) {
        return
            1 <= x &&
            x <= currentMap.getHeight() &&
            1 <= y &&
            y <= currentMap.getWidth() &&
            currentMap.GetMapByIndex(x, y) != 1;
    }

    private boolean flag = true;

    private void DealWithKeyPressed(char c, Field[][] field, JFrame frame, JLabel stepCounter) {
        c = Character.isUpperCase(c) ? Character.toLowerCase(c) : c;
        if(c != 'w' && c != 'a' && c != 's' && c != 'd')return;
        System.err.println("Key pressed " + c);
        int curX = player.getPosX();
        int curY = player.getPosY();
        int nextX = -1;
        int nextY = -1;
        switch (c) {
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
                --counter;
            }
        }
        if (nextX < 1 || nextX > currentMap.getHeight() || nextY < 1 || nextY > currentMap.getWidth())
            return;
        switch (field[nextX][nextY].type) {
            case Empty -> {
                field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player
                    ? FieldType.Empty
                    : FieldType.Target);
                field[nextX][nextY].UpdateType(FieldType.Player);
                player.setPosX(nextX);
                player.setPosY(nextY);
                player.setCntStep(player.getCntStep() + 1);
            }
            case Blocked -> {
                //Blocked
                --counter;
            }
            case Box -> {
                int boxNextX = -1;
                int boxNextY = -1;
                switch (c) {
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
                if (boxNextX < 1 || boxNextX > currentMap.getHeight() || boxNextY < 1 || boxNextY > currentMap.getWidth())
                    return;
                switch (field[boxNextX][boxNextY].type) {
                    case Empty -> {
                        field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player
                            ? FieldType.Empty
                            : FieldType.Target);
                        field[nextX][nextY].UpdateType(FieldType.Player);
                        field[boxNextX][boxNextY].UpdateType(FieldType.Box);
                        player.setPosX(nextX);
                        player.setPosY(nextY);
                        player.setCntStep(player.getCntStep() + 1);
                    }
                    case Blocked -> {
                        --counter;
                        //Blocked
                    }
                    case Box -> {
                        --counter;
                        //Blocked
                        //More than 1 box can't be pushed
                    }
                    case Target -> {
                        field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player
                            ? FieldType.Empty
                            : FieldType.Target);
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
                        --counter;
                        //Blocked
                        //More than 1 box can't be pushed
                    }
                }
            }
            case Target -> {
                field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player
                    ? FieldType.Empty
                    : FieldType.Target);
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
                switch (c) {
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
                if (boxNextX < 1 || boxNextX > currentMap.getHeight() || boxNextY < 1 || boxNextY > currentMap.getWidth())
                    return;
                switch (field[boxNextX][boxNextY].type) {
                    case Empty -> {
                        field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player
                            ? FieldType.Empty
                            : FieldType.Target);
                        field[nextX][nextY].UpdateType(FieldType.TargetWithPlayer);
                        field[boxNextX][boxNextY].UpdateType(FieldType.Box);
                        player.setPosX(nextX);
                        player.setPosY(nextY);
                        player.setCntStep(player.getCntStep() + 1);
                    }
                    case Blocked -> {
                        --counter;
                        //Blocked
                    }
                    case Box -> {
                        --counter;
                        //Blocked
                        //More than 1 box can't be pushed
                    }
                    case Target -> {
                        field[curX][curY].UpdateType(field[curX][curY].type == FieldType.Player
                            ? FieldType.Empty
                            : FieldType.Target);
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
                        --counter;
                        //Blocked
                        //More than 1 box can't be pushed
                    }
                }
            }
        }
        ++counter;
        stepCounter.setText(String.valueOf(counter));
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

        flag = true;
        IntStream.range(1, currentMap.getHeight() + 1).forEach(
            i -> {
                IntStream.range(1, currentMap.getWidth() + 1).forEach(
                    j -> {
                        if(field[i][j].type == FieldType.Box)
                            flag = false;
                    }
                );
            }
        );
        if(flag) {
            try {
                SuccessDialog.CreateAndShowDialog(mapIndex, false, frame);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            frame.dispose();
        }

        try {
            Archive.SetArchiveByID(User.currentUser.getUID(), mapIndex, currentMap, counter);
        } catch (Exception e1) {
        }

        IntStream.range(1, currentMap.getHeight() + 1).forEach(
            i -> {
                IntStream.range(1, currentMap.getWidth() + 1).forEach(
                    j -> {
                        if(field[i][j].type == FieldType.Box)
                            if (
                                (!isEmpty(i - 1, j) || !isEmpty(i + 1, j)) &&
                                (!isEmpty(i, j - 1) || !isEmpty(i, j + 1))
                            ){
                                try {
                                    FailDialog.CreateAndShowDialog(mapIndex, frame);
                                } catch (Exception e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                    }
                );
            }
        );
        
    }

    public void CreateAndShowWindow() {
        FlatLightLaf.setup();

        JFrame frame = new JFrame("Sokoban Game");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(null);

        JPanel mainPanel = new JPanel(new GridLayout(currentMap.getHeight(), currentMap.getWidth(), 10, 10));
        Field[][] field = new Field[currentMap.getHeight() + 1][currentMap.getWidth() + 1];

        int blockSize = 1000 / (int)Math.max(currentMap.getWidth(), currentMap.getHeight());
        // mainPanel.setBounds(100, 40, currentMap.getWidth() * 120, currentMap.getHeight() * 120);
        mainPanel.setBounds(100, 80, currentMap.getWidth() * blockSize, currentMap.getHeight() * blockSize);

        IntStream.range(1, currentMap.getHeight() + 1).forEach(
            i -> {
                IntStream.range(1, currentMap.getWidth() + 1).forEach(
                    j -> {
                        field[i][j] = new Field(FieldType.values()[currentMap.GetMapByIndex(i, j)], blockSize);
                        mainPanel.add(field[i][j].panel);
                    }
                );
            }
        );
        
        frame.add(mainPanel);

        JLabel levelLabel = new JLabel("Level:    " + mapIndex);
        levelLabel.setLocation(new Point(mainPanel.getWidth() + 100 + 100, 100));
        levelLabel.setSize(new Dimension(200, 80));
        levelLabel.setFont(new Font("Arial", Font.PLAIN, 40));

        frame.add(levelLabel);

        JButton saveButton = CreateDefaultMenuButton("Save");
        saveButton.setLocation(new Point(mainPanel.getWidth() + 100 + 100, 300));
        saveButton.setSize(new Dimension(200, 80));

        frame.add(saveButton);

        JButton resetButton = CreateDefaultMenuButton("Reset");
        resetButton.setLocation(new Point(mainPanel.getWidth() + 100 + 100, 400));
        resetButton.setSize(new Dimension(200, 80));

        frame.add(resetButton);

        JLabel stepLabel = new JLabel("Steps:");
        stepLabel.setLocation(new Point(mainPanel.getWidth() + 100 + 100, 200));
        stepLabel.setSize(new Dimension(200, 80));
        stepLabel.setFont(new Font("Arial", Font.PLAIN, 40));

        frame.add(stepLabel);
        
        JLabel stepCounter = new JLabel(String.valueOf(this.counter));
        stepCounter.setLocation(new Point(mainPanel.getWidth() + 100 + 250, 200));
        stepCounter.setSize(new Dimension(200, 80));
        stepCounter.setFont(new Font("Arial", Font.PLAIN, 40));

        frame.add(stepCounter);

        JLabel upButton = new JLabel();
        upButton.setLocation(new Point(mainPanel.getWidth() + 100 + 150, 500));
        upButton.setSize(new Dimension(100, 100));
        upButton.setIcon(SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/up.png", false));

        JLabel leftButton = new JLabel();
        leftButton.setLocation(new Point(mainPanel.getWidth() + 100 + 50, 600));
        leftButton.setSize(new Dimension(100, 100));
        leftButton.setIcon(SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/left.png", false));

        JLabel downButton = new JLabel();
        downButton.setLocation(new Point(mainPanel.getWidth() + 100 + 150, 600));
        downButton.setSize(new Dimension(100, 100));
        downButton.setIcon(SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/down.png", false));

        JLabel rightButton = new JLabel();
        rightButton.setLocation(new Point(mainPanel.getWidth() + 100 + 250, 600));
        rightButton.setSize(new Dimension(100, 100));
        rightButton.setIcon(SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/right.png", false));

        frame.add(upButton);
        frame.add(leftButton);
        frame.add(downButton);
        frame.add(rightButton);

        upButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DealWithKeyPressed('w', field, frame, stepCounter);
            }
        });
        leftButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DealWithKeyPressed('a', field, frame, stepCounter);
            }
        });
        downButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DealWithKeyPressed('s', field, frame, stepCounter);
            }
        });
        rightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DealWithKeyPressed('d', field, frame, stepCounter);
            }
        });

        
        // mainPanel.requestFocus();
        
        frame.addKeyListener(
            new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    char c = e.getKeyChar();
                    DealWithKeyPressed(c, field, frame, stepCounter);
                }
            }
        );
    
        saveButton.addActionListener(
            e -> {
                try {
                    Archive.SetArchiveByID(User.currentUser.getUID(), mapIndex, currentMap, counter);
                } catch (Exception e1) {}
            }
        );

        resetButton.addActionListener(
            e -> {
                try {
                    this.currentMap = new GameMap(GameMap.maps.get(mapIndex).getHeight(),
                    GameMap.maps.get(mapIndex).getWidth());
                    this.counter = 0;
                    stepCounter.setText("0");
                } catch (Exception ee) {}
                IntStream.range(1, currentMap.getHeight() + 1).forEach(
                    i -> {
                        IntStream.range(1, currentMap.getWidth() + 1).forEach(
                            j -> {
                                this.currentMap.SetMapByIndex(i, j, GameMap.maps.get(mapIndex).GetMapByIndex(i, j));
                            }
                        );
                    }
                );
                for(int i = 1; i <= this.currentMap.getHeight(); ++i)
                    for(int j = 1; j <= this.currentMap.getWidth(); ++j)
                        if(this.currentMap.GetMapByIndex(i, j) == 4 || this.currentMap.GetMapByIndex(i, j) == 5)
                            this.player = new Player(i, j);
                if(this.player == null){
                    System.err.println("Building map failed! #Map without player.");
                    System.exit(1);
                }
                IntStream.range(1, currentMap.getHeight() + 1).forEach(
                    i -> {
                        IntStream.range(1, currentMap.getWidth() + 1).forEach(
                            j -> {
                                field[i][j].UpdateType(FieldType.values()[currentMap.GetMapByIndex(i, j)]);
                            }
                        );
                    }
                );
                try {
                    Archive.SetArchiveByID(User.currentUser.getUID(), mapIndex, currentMap, counter);
                } catch (Exception e1) {}
                frame.setFocusable(true);
                frame.requestFocus();
            }
        );

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    Archive.SetArchiveByID(User.currentUser.getUID(), mapIndex, currentMap, counter);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        frame.setFocusable(true);
        frame.requestFocus();

        frame.setVisible(true);


    }

    public void TestShowcase(Field field[][]) {
        System.out.printf("Current Map %d * %d :\n", currentMap.getHeight(), currentMap.getWidth());
        for (int i = 1; i <= currentMap.getHeight(); ++i)
            for (int j = 1; j <= currentMap.getWidth(); ++j)
                System.out.printf("%d%c", field[i][j].type, j == currentMap.getWidth() ? '\n' : ' ');
        System.out.printf("Player position %d, %d\n", player.getPosX(), player.getPosY());
    }

    public static void NextLevelGame() {
        GameSystem game = new GameSystem(mapIndex + 1);
        game.CreateAndShowWindow();
    }

    public static void main(String[] args) {
        GameSystem game = new GameSystem(1);
        game.CreateAndShowWindow();
    }
    
}
