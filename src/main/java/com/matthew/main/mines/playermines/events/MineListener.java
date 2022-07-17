package com.matthew.main.mines.playermines.events;

import com.matthew.main.mines.apis.mines.Mine;
import com.matthew.main.mines.apis.mines.MineManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class MineListener implements Listener {

    /**
     * Purpose:
     * - When a player moves inside the region of a specific mine the player is
     * added to the arraylist for that mine to show that they have entered
     */

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();

        for (Mine mine : MineManager.getMines()) {
            if (!(MineManager.getMine(mine.getName()).getPlayers().contains(player)) && mine.getMineLoc().contains(player.getLocation())) {
                MineManager.getMine(mine.getName()).addPlayer(player);
            } else {
                if (MineManager.getMine(mine.getName()).getPlayers().contains(player) && !mine.getMineLoc().contains(player.getLocation())) {
                    MineManager.getMine(mine.getName()).removePlayer(player);
                }
            }
        }
    }

    /**
     * Purpose:
     * - When a block is broken it is checked if a player is currently in one of the mines regions. If the condition
     * is met it is checked if the block that was broken was the ore for the specific mine. If the condition is met the block
     * broken is replaced with whatever the replacement block is for the mine and the player has x1 of the ore added to their inventory
     */

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        for (Mine mine : MineManager.getMines()) {
            if (MineManager.getMine(mine.getName()).getPlayers().contains(player) && MineManager.getMine(mine.getName()).getMineLoc().contains(player.getLocation())) {
                if (e.getBlock().getType().equals(mine.getOre())) {
                    e.setCancelled(true);
                    e.getBlock().setType(mine.getTargetResetType());
                    player.getInventory().addItem(new ItemStack(mine.getOre()));

                }
            }
        }
    }
}
