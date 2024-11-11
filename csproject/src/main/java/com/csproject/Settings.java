package com.csproject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Settings {
    // 
    // public static void GetProperties() throws Exception{
    //     Properties properties = new Properties();
    //     InputStream in = new FileInputStream("./csproject/target/config.properties");
    //     properties.load(in);
    //     playerCnt = Integer.parseInt(properties.getProperty("playerCnt"));
    //     // System.out.println(playerCnt);
    // }
    // public static void SetProperties() throws Exception{
    //     Properties properties = new Properties();
    //     OutputStream out = new FileOutputStream("./csproject/target/config.properties");
    //     properties.setProperty("playerCnt", Integer.toString(playerCnt));
    //     properties.store(out, "Settings");
    //     out.close();
    // }
}
