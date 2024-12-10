package com.csproject;

import java.util.List;

public class User {
    public static User currentUser;

    public static final int baseUID = 10000000;
    private static int playerCnt;

    private int UID;
    private String Username;
    private String Password_sha256;

    public static void InitUsers() throws Exception{
        List < User > users = H2Database.GetUsers();
        playerCnt = users.size();
        // System.out.printf("Current size = %d\n", users.size());
        Main.setUsers(users);
    }

    public static int GetAndUpdatePlayerCnt() {
        return ++playerCnt;
    }
    public int getUID() {
        return UID;
    }

    public void setUID(int uID) {
        UID = uID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String name) {
        Username = name;
    }

    public String getPassword_sha256() {
        return Password_sha256;
    }

    public void setPassword_sha256(String password_sha256) {
        Password_sha256 = password_sha256;
    }


    public void Describe() {
        System.err.printf("User ID = %d, Name = %s, PWD = %s\n", this.UID, this.Username, this.Password_sha256);
    }

    public User(int UID, String Username, String Password_sha256) throws Exception{
        // Settings.GetProperties();
        // Settings.setPlayerCnt(Settings.getPlayerCnt() + 1);
        // this.UID = Settings.baseUID + Settings.getPlayerCnt();
        this.UID = UID;
        this.Username = Username;
        this.Password_sha256 = Password_sha256;
        // Settings.SetProperties();
    }
}
