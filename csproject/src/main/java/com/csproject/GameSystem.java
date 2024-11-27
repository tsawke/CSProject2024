package com.csproject;

import java.util.Arrays;
import java.util.List;

public class GameSystem {
    private List < GameMap > maps;

    public GameSystem() {
        this.maps = Arrays.asList(
            new GameMap(0, 0),
            new GameMap(6, 6,
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
    }
    
}
