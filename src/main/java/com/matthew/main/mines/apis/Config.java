package com.matthew.main.mines.apis;

import com.matthew.main.MinePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public class Config {

    /**
     * Construct the config and copy defaults
     *
     * @param instance - class that extends JavaPlugin
     */
    public Config(MinePlugin instance) {
        instance.getConfig().options().copyDefaults();
        instance.saveDefaultConfig();
    }

    /**
     * Get the lower corner of the mine's region (Corner furthest from the topCorner)
     *
     * @param mine - name of the mine
     * @return the location of the lower corner of the mine's region
     */
    public static Location getLowerCorner(String mine) {
        return new Location(Bukkit.getWorld(MinePlugin.getInstance().getConfig().getString("mines." + mine + ".world")),
                MinePlugin.getInstance().getConfig().getDouble("mines." + mine + ".x1"),
                MinePlugin.getInstance().getConfig().getDouble("mines." + mine + ".y1"),
                MinePlugin.getInstance().getConfig().getDouble("mines." + mine + ".z1"));
    }

    /**
     * Get the top corner of the mine's region (corner furthest from the lowerCorner)
     *
     * @param mine - name of the mine
     * @return the location of the top corner of the mine's region
     */
    public static Location getTopCorner(String mine) {
        return new Location(Bukkit.getWorld(MinePlugin.getInstance().getConfig().getString("mines." + mine + ".world")),
                MinePlugin.getInstance().getConfig().getDouble("mines." + mine + ".x2"),
                MinePlugin.getInstance().getConfig().getDouble("mines." + mine + ".y2"),
                MinePlugin.getInstance().getConfig().getDouble("mines." + mine + ".z2"));
    }

    /**
     * The material that the ore has a chance of spawning on
     *
     * @param mine - name of the mine
     * @return the material of the block
     */
    public static Material getReplacementType(String mine) {
        String type = MinePlugin.getInstance().getConfig().getString("mines." + mine + ".block");
        return Material.matchMaterial(type);
    }

    /**
     * How long (in seconds) it takes until a mine is reset
     *
     * @param mine - name of mine
     * @return the long that refers to the number of seconds it takes for a mine to reset
     */
    public static double getResetTime(String mine) {
        return MinePlugin.getInstance().getConfig().getLong("mines." + mine + ".reset-time");
    }

    /**
     * The ore for that specific mine
     *
     * @param mine - name of the mine
     * @return the material (ore) that the mine is going to be
     */
    public static Material getOreType(String mine) {
        String type = MinePlugin.getInstance().getConfig().getString("mines." + mine + ".ore");
        return Material.matchMaterial(type);
    }
}
