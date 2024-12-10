package com.csproject;

import java.util.Arrays;
import java.util.List;

public class GameMap {
    public static final List < GameMap > maps = Arrays.asList(
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
        ),
        new GameMap(6, 7,
            new int[][]{
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {-1, 1, 1, 1, 1, 1, 1, 0},
                {-1, 1, 4, 0, 0, 0, 1, 1},
                {-1, 1, 0, 2, 2, 0, 0, 1},
                {-1, 1, 0, 1, 3, 0, 3, 1},
                {-1, 1, 0, 0, 0, 0, 0, 1},
                {-1, 1, 1, 1, 1, 1, 1, 1}
            }
        )
    );



    private int height;
    private int width;


    /*
    Map:
    -1 <=> undefined
    0 <=> Empty
    1 <=> Block
    2 <=> Box
    3 <=> Target Position
    4 <=> Player
    5 <=> TargetWithPlayer
    6 <=> TargetWithBox
    */
    int[][] map;
    // public static List < GameMap > maps;

    // public void InitPlayer() {
    //     for(int i = 1; i <= height; ++i)
    //         for(int j = 1; j <= width; ++j)
    //             if(this.map[i][j] == 4)
    //                 this.player = new Player(i, j);
    //     if(this.player == null){
    //         System.err.println("Building map failed! #Map without player.");
    //         System.exit(1);
    //     }
    // }

    public GameMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.map = new int[height + 5][width + 5];
    }

    public GameMap(int height, int width, int[][] map) {
        this.height = height;
        this.width = width;
        this.map = map;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[][] getMap() {
        return map;
    }
    
    public int GetMapByIndex(int x, int y) {
        return this.map[x][y];
    }
    public void SetMapByIndex(int x, int y, int type) {
        this.map[x][y] = type;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
    
}
