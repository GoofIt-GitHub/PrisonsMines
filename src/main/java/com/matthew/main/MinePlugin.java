package com.matthew.main;

import com.matthew.main.mines.apis.Config;
import com.matthew.main.mines.apis.mines.MineManager;
import com.matthew.main.mines.commands.MineCommand;
import com.matthew.main.mines.playermines.events.MineListener;
import com.matthew.main.mines.playermines.events.PlayerLeaveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinePlugin extends JavaPlugin {

    private static MinePlugin instance;

    @Override
    public void onEnable() {

        instance = this;

        //Run constructor first
        new Config(this);
        new MineManager();

        registerListeners();
        registerCommands();

        //Verification to console
        System.out.println("Mine Plugin running");


    }

    @Override
    public void onDisable() {

    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerLeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new MineListener(), this);
    }

    private void registerCommands() {
        getCommand("mine").setExecutor(new MineCommand());
    }

    public static MinePlugin getInstance() {
        return instance;
    }
}
