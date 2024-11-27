package com.csproject;

public class Player {
    private int posX, posY;
    private int cntStep;
    public Player(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.cntStep = 0;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getCntStep() {
        return cntStep;
    }

    public void setCntStep(int cntStep) {
        this.cntStep = cntStep;
    }

}
