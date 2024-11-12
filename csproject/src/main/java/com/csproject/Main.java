package com.csproject;

import java.util.ArrayList;
import java.util.List;

import com.csproject.pages.*;;

public class Main {
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // H2Database.CreateDefaultTables();
        // User.InitUsers();
        // H2Database.InsertUser(new User(User.baseUID + User.GetAndUpdatePlayerCnt(),
        // "Test", "pwd", (short)1));

        // for(User i : users)System.out.println(i);
        // Settings.GetProperties();
        ChooseMap.CreateAndShowWindow();
        System.out.println("Hello world!");
        // Index.CreateAndShowWindow();
        System.out.println("Complete!");
    }

    public static List<User> getUsers() {
        return users;
    }

    public static void setUsers(List<User> users) {
        Main.users = users;
    }
}