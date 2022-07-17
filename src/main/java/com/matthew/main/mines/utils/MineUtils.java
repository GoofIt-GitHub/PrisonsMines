package com.matthew.main.mines.utils;

public class MineUtils {

    public static boolean isValidMine(String mine) {
        if(mine.equals("DIAMOND") || mine.equals("GOLD") || mine.equals("COAL") || mine.equals("IRON") || mine.equals("REDSTONE")) {
            return true;
        }
        return false;
    }
}
