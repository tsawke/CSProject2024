package com.csproject;

import java.awt.Color;

import com.csproject.dependencies.TextBorderUtlis;

public class BeautifyUtils {
    public static TextBorderUtlis defaultGrayBorder = GetDefaultBorderWithColor(Color.GRAY);
    public static TextBorderUtlis defaultDarkGrayBorder = GetDefaultBorderWithColor(Color.DARK_GRAY);
    public static TextBorderUtlis defaultGreenBorder = GetDefaultBorderWithColor(Color.GREEN);

    public static TextBorderUtlis GetDefaultBorderWithColor(Color col) {
        return new TextBorderUtlis(col, 1, true);
    }
}
