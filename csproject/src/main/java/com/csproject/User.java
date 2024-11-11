package com.csproject;

import java.util.List;

public class User {
    public static final int baseUID = 10000000;
    private static int playerCnt;

    private int UID;
    private String Name;
    private String Password_sha256;
    private short Sex;

    public static void InitUsers() throws Exception{
        List < User > users = H2Database.GetUsers();
        playerCnt = users.size();
        System.out.printf("Current size = %d\n", users.size());
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword_sha256() {
        return Password_sha256;
    }

    public void setPassword_sha256(String password_sha256) {
        Password_sha256 = password_sha256;
    }

    public short getSex() {
        return Sex;
    }

    public void setSex(short sex) {
        Sex = sex;
    }

    public void Describe() {
        System.out.printf("User ID = %d, Name = %s, PWD = %s, Sex = %d\n", this.UID, this.Name, this.Password_sha256, this.Sex);
    }

    public User(int UID, String Name, String Password_sha256, short Sex) throws Exception{
        // Settings.GetProperties();
        // Settings.setPlayerCnt(Settings.getPlayerCnt() + 1);
        // this.UID = Settings.baseUID + Settings.getPlayerCnt();
        this.UID = UID;
        this.Name = Name;
        this.Password_sha256 = Password_sha256;
        this.Sex = Sex;
        // Settings.SetProperties();
    }
}
