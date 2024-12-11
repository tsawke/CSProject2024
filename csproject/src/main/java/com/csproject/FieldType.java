package com.csproject;

import java.awt.Color;

import javax.swing.ImageIcon;

import com.csproject.dependencies.SwingUtil;

public enum FieldType {
    Empty(Color.WHITE, SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/empty.png", false)),
    Blocked(Color.RED, SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/blocked.png", false)),
    Box(Color.BLUE, SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/basketball.png", false)),
    Target(Color.GREEN, SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/target.png", false)),
    Player(Color.CYAN, SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/player.gif", false)),
    TargetWithPlayer(Color.DARK_GRAY, SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/player.gif", false)),
    TargetWithBox(Color.GREEN, SwingUtil.createAutoAdjustIcon("./csproject/src/main/resources/Icons/target_with_box.png", false));

    private FieldType(Color color, ImageIcon icon) {
        this.color = color;
        this.icon = icon;
    }
    private Color color;
    private ImageIcon icon;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public int ConvertToInt() {
        switch(this){
            case Empty -> {return 0;}
            case Blocked -> {return 1;}
            case Box -> {return 2;}
            case Target -> {return 3;}
            case Player -> {return 4;}
            case TargetWithPlayer -> {return 5;}
            case TargetWithBox -> {return 6;}
            default -> {return -1;}
        }
    }
}