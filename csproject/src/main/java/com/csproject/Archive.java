package com.csproject;

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
        GameSystem.maps.stream()
            .forEach(m -> {
                idx = 0;
                IntStream.range(1, m.getHeight() + 1).forEach(
                    i -> {
                        IntStream.range(1, m.getWidth() + 1).forEach(
                            j -> {
                                properties.setProperty(String.format("map2D_%d.%d.%d", ++idx, i, j), ((Integer)(m.GetMapByIndex(i, j))).toString());
                            }
                        );
                    }
                );
            });
        
        properties.store(out, "Archive of user " + id);
        out.close();
    }
}
