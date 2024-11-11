package com.csproject;

public class User {
    private int UID;
    private String Name;
    private String Password_sha256;
    private short Sex;

    public User(int UID, String Name, String Password_sha256, short Sex) {
        this.UID = UID;
        this.Name = Name;
        this.Password_sha256 = Password_sha256;
        this.Sex = Sex;
    }
}
