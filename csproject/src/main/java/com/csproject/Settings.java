package com.csproject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    public void GetProperties() throws Exception{
        Properties properties = new Properties();
        InputStream in = new FileInputStream("config.properties");

    }
}
