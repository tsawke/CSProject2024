package com.csproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class H2Database {
    private static final String JDBC_URL = "jdbc:h2:file:./target/MainDB";
    private static final String DRIVER_CLASS = "org.h2.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public static void CreateDefaultDatabase() throws Exception{
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        
        statement.execute("CREATE TABLE USER(UID INT PRIMARY KEY, Name VARCHAR(50) NOT NULL, Password_sha256 VARCHAR(300), Sex TINYINT) IF UNEXISTS USER;");
    }
}
