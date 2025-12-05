// package com.csproject;

// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.OutputStream;
// import java.util.Properties;
// import java.util.stream.IntStream;

// public class Archive {
//     private static int idx = 0;
//     public static void CreateDefaultArchiveByID(int id) throws Exception {
//         Properties properties = new Properties();
//         OutputStream out = new FileOutputStream("./csproject/target/archives/" + id + ".properties");
//         // properties.setProperty("playerCnt", Integer.toString(playerCnt));
//         properties.setProperty("currentLevel", "1");
//         properties.setProperty("counter", "0");
//         idx = 0;
//         GameMap.maps.stream()
//             .forEach(m -> {
//                 IntStream.range(1, m.getHeight() + 1).forEach(
//                     i -> {
//                         IntStream.range(1, m.getWidth() + 1).forEach(
//                             j -> {
//                                 properties.setProperty(String.format("map2D_%d.%d.%d", idx, i, j), ((Integer)(m.GetMapByIndex(i, j))).toString());
//                             }
//                         );
//                     }
//                 );
//                 ++idx;
//             });
        
//         properties.store(out, "Archive of user " + id);
//         out.close();
//     }
//     public static void SetArchiveByID(int id, int mapID, GameMap m, int counter) throws Exception {
//         final String filename = "./csproject/target/archives/" + id + ".properties";
//         Properties properties = new Properties();
//         properties.load(new FileInputStream(filename));
//         OutputStream out = new FileOutputStream(filename);
//         properties.setProperty("currentLevel", ((Integer)mapID).toString());
//         properties.setProperty("counter", String.valueOf(counter));
//         IntStream.range(1, m.getHeight() + 1).forEach(
//             i -> {
//                 IntStream.range(1, m.getWidth() + 1).forEach(
//                     j -> {
//                         properties.setProperty(String.format("map2D_%d.%d.%d", mapID, i, j), ((Integer)(m.GetMapByIndex(i, j))).toString());
//                     }
//                 );
//             }
//         );
        
//         properties.store(out, "Archive of user " + id); 
//         out.close();
//     }
//     public static void SetLevelArchiveByID(int id, int mapID) throws Exception {
//         final String filename = "./csproject/target/archives/" + id + ".properties";
//         Properties properties = new Properties();
//         properties.load(new FileInputStream(filename));
//         OutputStream out = new FileOutputStream(filename);
//         properties.setProperty("currentLevel", ((Integer)mapID).toString());
//         properties.store(out, "Archive of user " + id); 
//         out.close();
//     }

//     private static boolean isEmpty(int x, int y, GameMap currentMap) {
//         return
//             1 <= x &&
//             x <= currentMap.getHeight() &&
//             1 <= y &&
//             y <= currentMap.getWidth() &&
//             currentMap.GetMapByIndex(x, y) != 1;
//     }

//     public static void RecreateAndLoadDefaultArchiveByID(int id, GameSystem gameSystem) throws Exception {
//         CreateDefaultArchiveByID(id);
//         final String filename = "./csproject/target/archives/" + id + ".properties";
//         Properties properties = new Properties();
//         properties.load(new FileInputStream(filename));
        
//         // properties.setProperty("playerCnt", Integer.toString(playerCnt));
//         int mapID = Integer.parseInt(properties.getProperty("currentLevel"));
//         // gameSystem.getCurrentMap().setMap(new int[GameMap.maps.get(mapID).getHeight()][GameMap.maps.get(mapID).getWidth()]);

//         gameSystem = new GameSystem(mapID);

//         // GameSystem.setMapIndex(mapID);

//         // gameSystem.setCounter(Integer.parseInt(properties.getProperty("counter")));

//         // GameMap m = gameSystem.getCurrentMap();
//         // IntStream.range(1, m.getHeight() + 1).forEach(
//         //     i -> {
//         //         IntStream.range(1, m.getWidth() + 1).forEach(
//         //             j -> {
//         //                 m.SetMapByIndex(i, j, Integer.parseInt(properties.getProperty(String.format("map2D_%d.%d.%d", mapID, i, j))));
//         //             }
//         //         );
//         //     }
//         // );


//     }
//     private static int mapID = 1;
//     public static boolean LoadArchiveByID_WithCheckArchive(int id, GameSystem gameSystem) throws Exception {
//         final String filename = "./csproject/target/archives/" + id + ".properties";
//         Properties properties = new Properties();
//         properties.load(new FileInputStream(filename));
        
//         // properties.setProperty("playerCnt", Integer.toString(playerCnt));
//         // if(properties.getProperty("currentLevel") == null) {
//         //     System.err.println("Archive is modified and illegal! Switch to default archive.");
//         //     RecreateAndLoadDefaultArchiveByID(id, gameSystem);
//         //     return false;
//         // }
//         mapID = 1;
//         try{
//             mapID = Integer.parseInt(properties.getProperty("currentLevel"));
//         }catch (Exception e){}
//         gameSystem = new GameSystem(mapID);

//         gameSystem.getCurrentMap().setMap(new int[GameMap.maps.get(mapID).getHeight() + 10][GameMap.maps.get(mapID).getWidth() + 10]);

//         GameSystem.setMapIndex(mapID);

//         // if(properties.getProperty("counter") == null) {
//         //     System.err.println("Archive is modified and illegal! Switch to default archive.");
//         //     RecreateAndLoadDefaultArchiveByID(id, gameSystem);
//         //     return false;
//         // }
//         int counter = 0;
//         try{
//             counter = Integer.parseInt(properties.getProperty("counter"));
//         }catch (Exception e){}
//         gameSystem.setCounter(counter);

//         GameMap m = gameSystem.getCurrentMap();
//         // for(int i = 1; i <= m.getHeight(); ++i)
//         //     for(int j = 1; j <= m.getWidth(); ++i)
//         //         if(properties.getProperty(String.format("map2D_%d.%d.%d", mapID, i, j)) == null) {
//         //             System.err.println("Archive is modified and illegal! Switch to default archive.");
//         //             try {
//         //                 RecreateAndLoadDefaultArchiveByID(id, gameSystem);
//         //             } catch (Exception e) {
//         //                 // TODO Auto-generated catch block
//         //                 e.printStackTrace();
//         //             }
//         //             return false;
//         //         }
//         IntStream.range(1, m.getHeight() + 1).forEach(
//             i -> {
//                 IntStream.range(1, m.getWidth() + 1).forEach(
//                     j -> {
//                         try{
//                         m.SetMapByIndex(i, j, Integer.parseInt(properties.getProperty(String.format("map2D_%d.%d.%d", mapID, i, j))));
//                         }catch (Exception e){}
//                     }
//                 );
//             }
//         );
//         boolean flag = true;
//         int cntPlayer = 0;
//         int cntBox = 0;
//         int cntTarget = 0;
//         for(int i = 1; i <= m.getHeight(); ++i)
//             for(int j = 1; j <= m.getWidth(); ++j) {
//                 switch(FieldType.values()[m.GetMapByIndex(i, j)]) {
//                     case Empty -> {
                        
//                     }
//                     case Blocked -> {

//                     }
//                     case Player -> {
//                         ++cntPlayer;
//                         // if((!isEmpty(i - 1, j, m) || !isEmpty(i + 1, j, m)) && (!isEmpty(i, j - 1, m) || !isEmpty(i, j + 1, m)))
//                             // flag = false;
//                     }
//                     case Box -> {
//                         // if((!isEmpty(i - 1, j, m) || !isEmpty(i + 1, j, m)) && (!isEmpty(i, j - 1, m) || !isEmpty(i, j + 1, m)))
//                             // flag = false;
//                         ++cntBox;
//                     }
//                     case Target -> {
//                         ++cntTarget;
//                     }
//                     case TargetWithBox -> {
//                         ++cntBox;
//                         ++cntTarget;
//                     }
//                     case TargetWithPlayer -> {
//                         ++cntTarget;
//                         ++cntPlayer;
//                     }
//                     default -> {}
//                 }
//             }
//         if(!flag || cntPlayer != 1 || cntBox != cntTarget) {
//             System.err.println("Archive is modified and illegal! Switch to default archive.");
//             RecreateAndLoadDefaultArchiveByID(id, gameSystem);
//             return false;
//         }
//         return true;
//     }
// }


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
        idx = 1;
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
    public static void SetLevelArchiveByID(int id, int mapID) throws Exception {
        final String filename = "./csproject/target/archives/" + id + ".properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(filename));
        OutputStream out = new FileOutputStream(filename);
        properties.setProperty("currentLevel", ((Integer)mapID).toString());
        properties.store(out, "Archive of user " + id); 
        out.close();
    }

    private static boolean isEmpty(int x, int y, GameMap currentMap) {
        return
            1 <= x &&
            x <= currentMap.getHeight() &&
            1 <= y &&
            y <= currentMap.getWidth() &&
            currentMap.GetMapByIndex(x, y) != 1;
    }

    public static void RecreateAndLoadDefaultArchiveByID(int id, GameSystem gameSystem) throws Exception {
        CreateDefaultArchiveByID(id);
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

    public static void LoadArchiveByID_WithCheckArchive(int id, GameSystem gameSystem) throws Exception {
        final String filename = "./csproject/target/archives/" + id + ".properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(filename));
        
        // properties.setProperty("playerCnt", Integer.toString(playerCnt));
        int mapID = Integer.parseInt(properties.getProperty("currentLevel"));

        // gameSystem = new GameSystem(mapID);/

        gameSystem.getCurrentMap().setMap(new int[GameMap.maps.get(mapID).getHeight() + 10][GameMap.maps.get(mapID).getWidth() + 10]);

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
        boolean flag = true;
        int cntPlayer = 0;
        int cntBox = 0;
        int cntTarget = 0;
        for(int i = 1; i <= m.getHeight(); ++i)
            for(int j = 1; j <= m.getWidth(); ++j) {
                switch(FieldType.values()[m.GetMapByIndex(i, j)]) {
                    case Empty -> {
                        
                    }
                    case Blocked -> {

                    }
                    case Player -> {
                        // gameSystem.player.setPosX(i);
                        // gameSystem.player.setPosY(j);
                        ++cntPlayer;
                        if((!isEmpty(i - 1, j, m) || !isEmpty(i + 1, j, m)) && (!isEmpty(i, j - 1, m) || !isEmpty(i, j + 1, m)))
                            flag = false;
                    }
                    case Box -> {
                        if((!isEmpty(i - 1, j, m) || !isEmpty(i + 1, j, m)) && (!isEmpty(i, j - 1, m) || !isEmpty(i, j + 1, m)))
                            flag = false;
                        ++cntBox;
                    }
                    case Target -> {
                        ++cntTarget;
                    }
                    case TargetWithBox -> {
                        ++cntBox;
                        ++cntTarget;
                    }
                    case TargetWithPlayer -> {
                        ++cntTarget;
                        ++cntPlayer;
                    }
                    default -> {}
                }
            }
        if(cntPlayer != 1 || cntBox != cntTarget) {
            System.err.println("Archive is modified and illegal! Switch to default archive.");
            RecreateAndLoadDefaultArchiveByID(id, gameSystem);
        }
    }
}