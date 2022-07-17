package com.matthew.main.mines.lists;

import org.bukkit.ChatColor;

public enum Mines {

    DIAMOND("DIAMOND", ChatColor.AQUA),
    GOLD("GOLD", ChatColor.GOLD),
    REDSTONE("REDSTONE", ChatColor.DARK_RED),
    IRON("IRON", ChatColor.WHITE),
    COAL("COAL", ChatColor.DARK_GRAY);


    private String name;
    private ChatColor color;

    Mines(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Name - name of mines created
     * @return the name of the mine
     */

    public String getName() {
        return this.name.toString();
    }

    /**
     * Color - refers to the chatcolor used when identifying the mine
     * @return the chatcolor corresponding to the mine
     */

    public ChatColor getColor() {
        return this.color;
    }
}
