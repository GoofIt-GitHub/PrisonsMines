package com.matthew.main.mines.playermines;

import com.matthew.main.MinePlugin;
import com.matthew.main.mines.apis.Config;
import com.matthew.main.mines.apis.mines.Mine;
import com.matthew.main.mines.lists.MineState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class MineTimer extends BukkitRunnable {

    private Mine mine;
    private int seconds;

    public MineTimer(Mine mine) {
        this.mine = mine;
        seconds = (int) Config.getResetTime(mine.getName());
    }

    public void begin() {
        this.runTaskTimer(MinePlugin.getInstance(), 0, 20L);
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public void run() {
        if (seconds == 0 && mine.getMineState().equals(MineState.RUNNING)) {
            mine.resetMine();
            seconds = (int) Config.getResetTime(mine.getName());
            return;
        }

        if (mine.getMineState().equals(MineState.RUNNING)) {
            seconds--;
            if (seconds == 5) {
                Bukkit.broadcastMessage(ChatColor.BLUE + ">> " +  mine.getColor().toString() + ChatColor.BOLD + mine.getName() + ChatColor.GRAY + " mine is resetting in 5 seconds..." );
            }
        }
    }
}
