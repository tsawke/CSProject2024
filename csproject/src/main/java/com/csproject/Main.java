package com.csproject;

import java.util.ArrayList;
import java.util.List;

import com.csproject.pages.LogIn;

public class Main {
    private static List<User> users = new ArrayList<>();



    public static void main(String[] args) throws Exception {
        H2Database.CreateDefaultTables();
        User.InitUsers();
        H2Database.InsertUser(new User(User.baseUID + User.GetAndUpdatePlayerCnt(),
                "Test", "pwd", (short) 1));

        LogIn.CreateAndShowWindow();

        GameSystem gameSystem = new GameSystem(1);
        gameSystem.CreateAndShowWindow();
        
        //先显示登陆界面，并实现了Vistor功能
        // LogIn.CreateAndShowWindow();
    

        // for(User i : users)System.out.println(i);
        // Settings.GetProperties();
        // System.out.println("Hello world!");
        // //Index.CreateAndShowWindow();
        System.out.println("Complete!");
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        Main.users = users;
    }
}