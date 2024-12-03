package com.csproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class H2Database {
    private static final String JDBC_URL = "jdbc:h2:file:./csproject/target/MainDB";
    private static final String DRIVER_CLASS = "org.h2.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    public static void CreateDefaultTables() throws Exception{
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS User;");
        statement.execute("CREATE TABLE User(UID INT PRIMARY KEY, Username VARCHAR(50) NOT NULL, Password_sha256 VARCHAR(300), Sex TINYINT);");
    }
    public static void InsertUser(User user) throws Exception{
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO User VALUES(%d, '%s', '%s', %d);", user.getUID(), user.getUsername(), user.getPassword_sha256(), user.getSex()));
    
        User.InitUsers();
    }
    public static List < User > GetUsers() throws Exception {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM User;");
        List < User > ret = new ArrayList<>();
        while(res.next())
            ret.add(new User(res.getInt("UID"), res.getString("Username"), res.getString("Password_sha256"), res.getShort("Sex")));
        // for(User i : ret)i.Describe();
        return ret;
    }
}
