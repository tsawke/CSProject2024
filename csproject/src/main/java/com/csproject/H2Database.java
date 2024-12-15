package com.csproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

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
        statement.execute("CREATE TABLE User(UID INT PRIMARY KEY, Username VARCHAR(50) NOT NULL, Password_sha256 VARCHAR(300));");
        IntStream.range(1, 5).forEach(
            i -> {
                try {
                    statement.execute("DROP TABLE IF EXISTS Rank" + i + ";");
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    statement.execute("CREATE TABLE Rank" + i + "(UID INT PRIMARY KEY, Steps INT);");
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        );
        
    }
    public static void InsertRank(int idx, User user, int steps) throws Exception{
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO Rank%d VALUES(%d, %d);", idx, user.getUID(), steps));
    }
    public static List < Pair < User, Integer > > GetRank(int idx) throws Exception {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM Rank" + idx + ";");
        List < Pair < User, Integer > > ret = new ArrayList<>();
        while(res.next())
            ret.add(Pair.of(SelectUserByUID(res.getInt("UID")), res.getInt("steps")));
        return ret.stream()
            .sorted((a, b) -> -Integer.compare(a.getRight(), b.getRight()))
            .collect(Collectors.toList());

    }
    public static void InsertUser(User user) throws Exception{
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("INSERT INTO User VALUES(%d, '%s', '%s');", user.getUID(), user.getUsername(), user.getPassword_sha256()));
    
        User.InitUsers();
    }
    public static List < User > GetUsers() throws Exception {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery("SELECT * FROM User;");
        List < User > ret = new ArrayList<>();
        while(res.next())
            ret.add(new User(res.getInt("UID"), res.getString("Username"), res.getString("Password_sha256")));
        // for(User i : ret)i.Describe();
        return ret;
    }
    public static boolean IfExistUserByUsername(String username) throws Exception {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(String.format("SELECT * FROM User WHERE Username = '%s';", username));
        List < User > ret = new ArrayList<>();
        while(res.next())
            ret.add(new User(res.getInt("UID"), res.getString("Username"), res.getString("Password_sha256")));
        return ret.size() >= 1;
    }
    public static User SelectUserByUsername(String username) throws Exception {
        if(!IfExistUserByUsername(username))System.exit(1);
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(String.format("SELECT * FROM User WHERE Username = '%s';", username));
        List < User > ret = new ArrayList<>();
        while(res.next())
            ret.add(new User(res.getInt("UID"), res.getString("Username"), res.getString("Password_sha256")));
        return ret.get(0);
    }
    public static boolean IfExistUserByUID(int UID) throws Exception {
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(String.format("SELECT * FROM User WHERE UID = %d;", UID));
        List < User > ret = new ArrayList<>();
        while(res.next())
            ret.add(new User(res.getInt("UID"), res.getString("Username"), res.getString("Password_sha256")));
        return ret.size() >= 1;
    }
    public static User SelectUserByUID(int UID) throws Exception {
        if(!IfExistUserByUID(UID))System.exit(1);
        Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(String.format("SELECT * FROM User WHERE UID = %d;", UID));
        List < User > ret = new ArrayList<>();
        while(res.next())
            ret.add(new User(res.getInt("UID"), res.getString("Username"), res.getString("Password_sha256")));
        return ret.get(0);
    }
}
