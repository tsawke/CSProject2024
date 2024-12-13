package com.csproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.stream.IntStream;

public class Archive {
    private static int idx = 0;
    public static void CreateDefaultArchiveByID(int id) throws Exception {
        Properties properties = new Properties();
        OutputStream out = new FileOutputStream("./csproject/target/archives/" + id + ".properties");
        // properties.setProperty("playerCnt", Integer.toString(playerCnt));
        properties.setProperty("currentLevel", "1");
        properties.setProperty("counter", "0");
        GameMap.maps.stream()
            .forEach(m -> {
                IntStream.range(1, m.getHeight() + 1).forEach(
                    i -> {
                        IntStream.range(1, m.getWidth() + 1).forEach(
                            j -> {
                                properties.setProperty(String.format("map2D_%d.%d.%d", idx, i, j), ((Integer)(m.GetMapByIndex(i, j))).toString());
                            }
                        );
                    }
                );
                ++idx;
            });
        
        properties.store(out, "Archive of user " + id);
        out.close();
    }
    public static void SetArchiveByID(int id, int mapID, GameMap m, int counter) throws Exception {
        final String filename = "./csproject/target/archives/" + id + ".properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(filename));
        OutputStream out = new FileOutputStream(filename);
        properties.setProperty("currentLevel", ((Integer)mapID).toString());
        properties.setProperty("counter", String.valueOf(counter));
        IntStream.range(1, m.getHeight() + 1).forEach(
            i -> {
                IntStream.range(1, m.getWidth() + 1).forEach(
                    j -> {
                        properties.setProperty(String.format("map2D_%d.%d.%d", mapID, i, j), ((Integer)(m.GetMapByIndex(i, j))).toString());
                    }
                );
            }
        );
        
        properties.store(out, "Archive of user " + id); 
        out.close();
    }
    public static void LoadArchiveByID(int id, GameSystem gameSystem) throws Exception {
        final String filename = "./csproject/target/archives/" + id + ".properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(filename));
        
        // properties.setProperty("playerCnt", Integer.toString(playerCnt));
        int mapID = Integer.parseInt(properties.getProperty("currentLevel"));
        gameSystem.getCurrentMap().setMap(new int[GameMap.maps.get(mapID).getHeight()][GameMap.maps.get(mapID).getWidth()]);

        GameSystem.setMapIndex(mapID);

        gameSystem.setCounter(Integer.parseInt(properties.getProperty("counter")));

        GameMap m = gameSystem.getCurrentMap();
        IntStream.range(1, m.getHeight() + 1).forEach(
            i -> {
                IntStream.range(1, m.getWidth() + 1).forEach(
                    j -> {
                        m.SetMapByIndex(i, j, Integer.parseInt(properties.getProperty(String.format("map2D_%d.%d.%d", mapID, i, j))));
                    }
                );
            }
        );
    }
}
