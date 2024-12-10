package com.csproject;

import java.awt.Color;

public enum FieldType {
    Empty(Color.WHITE),
    Blocked(Color.RED),
    Box(Color.BLUE),
    Target(Color.GREEN),
    Player(Color.CYAN),
    TargetWithPlayer(Color.DARK_GRAY),
    TargetWithBox(Color.ORANGE);

    private FieldType(Color color) {
        this.color = color;
    }
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
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