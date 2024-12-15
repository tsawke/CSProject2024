package com.csproject;

import com.csproject.pages.Index;

public class Main {
    



    public static void main(String[] args) throws Exception {
        // JmeThread jmeThread = new JmeThread();
        // Archive.CreateDefaultArchiveByID(1);

        // H2Database.CreateDefaultTables();
        
        User.InitUsers();
        User.DescribeAll();
        // H2Database.InsertUser(new User(User.baseUID + User.GetAndUpdatePlayerCnt(), "Test", "pwd"));
        // jMEMain.main(null);
        // Index.CreateAndShowWindow();
        // ErrorDialog.CreateAndShowDialog(Index.frame, "Test errorpage.Test errorpage.Test errorpage.Test errorpage.Test errorpage.Test errorpage.Test errorpage.Test errorpage.");
    
        // Run3D.Run(2);
        // User.currentUser = H2Database.SelectUserByUsername("Test");
        // Archive.CreateDefaultArchiveByID(User.currentUser.getUID());
        Index.CreateAndShowWindow();

        // // LogIn.CreateAndShowWindow();

        // RankList.CreateAndShowWindow();

        // ChooseMap.CreateAndShowWindow();
        // SuccessDialog.CreateAndShowDialog(4);


        // for(int i = 1; i <= 5; ++i)
        //     ChooseMap.CreateIcons(GameMap.maps.get(i), i);

        // GameSystem gameSystem = new GameSystem(1);
        // gameSystem.CreateAndShowWindow();
        
    

        // for(User i : users)System.out.println(i);
        // Settings.GetProperties();
        // System.out.println("Hello world!");
        // //Index.CreateAndShowWindow();
        System.out.println("Complete!");
    }
}