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
}