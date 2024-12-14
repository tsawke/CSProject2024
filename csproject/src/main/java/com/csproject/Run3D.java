package com.csproject;

public class Run3D {
    public static void Run(int idx) {
        jMEMain.mapIndex = idx;
        // jMEMain.main(null);
        jMEMain app = new jMEMain();
        app.start();
    }
}
