package com.matthew.main.mines.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static void incorrectUsage(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Incorrect usage. Please use: ");
        player.sendMessage(ChatColor.BLUE + "     - " + ChatColor.GOLD + "/mine reset (mine_name)");
        player.sendMessage(ChatColor.BLUE + "     - " + ChatColor.GOLD + "/mine stop (mine_name)");
        player.sendMessage(ChatColor.BLUE + "     - " + ChatColor.GOLD + "/mine start (mine_name)");
        player.sendMessage(ChatColor.BLUE + "     - " + ChatColor.GOLD + "/mine list");
        player.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + ChatColor.BOLD.toString() + "Note: " + ChatColor.GRAY + "mines are case sensitive");

    }

    public static void minesList(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "List of mines: ");
        player.sendMessage(ChatColor.BLUE + "     - " + ChatColor.YELLOW + "DIAMOND, GOLD, REDSTONE, IRON, COAL");
        player.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + ChatColor.BOLD.toString() + "Note: " + ChatColor.GRAY + "mines are case sensitive");

    }
}
