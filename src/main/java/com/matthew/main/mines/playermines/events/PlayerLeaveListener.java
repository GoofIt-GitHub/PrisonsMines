package com.matthew.main.mines.playermines.events;

import com.matthew.main.mines.apis.mines.MineManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    /**
     * Player Quit -
     * Occurs if a player is still in a mine upon quitting
     * Removes them from the mine arraylist they are currently in
     */

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if(MineManager.getMines().contains(e.getPlayer())) {
            MineManager.getMine(e.getPlayer()).removePlayer(e.getPlayer());
        }
    }


}
