package com.csproject.pages;

import com.csproject.EncryptUtils;
import com.csproject.H2Database;
import com.csproject.User;

public class SignUp {
    public void SignUpUser(String username, String password_plain, short sex) throws Exception{
        H2Database.InsertUser(
            new User(
                H2Database.GetUsers().size() + 1,
                username,
                EncryptUtils.sha256(password_plain),
                sex
            )
        );
    }
}
