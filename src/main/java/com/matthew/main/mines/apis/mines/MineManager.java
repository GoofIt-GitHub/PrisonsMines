package com.matthew.main.mines.apis.mines;

import com.matthew.main.mines.apis.Config;
import com.matthew.main.mines.apis.Cuboid;
import com.matthew.main.mines.apis.mines.Mine;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MineManager {

    private static ArrayList<Mine> mines;

    /**
     * Constructs a new MineManager object when called. The mines arraylist is initialized and a mine is
     * created and added to the mines arraylist.
     */

    public MineManager() {
        mines = new ArrayList<>();
        mines.add(new Mine("DIAMOND", new Cuboid(Config.getLowerCorner("DIAMOND"), Config.getTopCorner("DIAMOND"))));
        mines.add(new Mine("GOLD", new Cuboid(Config.getLowerCorner("GOLD"), Config.getTopCorner("GOLD"))));
        mines.add(new Mine("IRON", new Cuboid(Config.getLowerCorner("IRON"), Config.getTopCorner("IRON"))));
        mines.add(new Mine("REDSTONE", new Cuboid(Config.getLowerCorner("REDSTONE"), Config.getTopCorner("REDSTONE"))));
        mines.add(new Mine("COAL", new Cuboid(Config.getLowerCorner("COAL"), Config.getTopCorner("COAL"))));


    }

    /**
     * Get all of the mines in the mines arraylist
     *
     * @return the mines arraylist
     */

    public static ArrayList<Mine> getMines() {
         return mines;
    }

    /**
     * Get the mine specified by name
     *
     * @param name - name of the mine (must be in all caps)
     * @return the specified mine if it exists
     */

    public static Mine getMine(String name) {

        for(Mine m: mines) {
            if(m.getName().equals(name)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Get the mine that a player is currently in
     *
     * @param player - the player that is used for checking which mine they are currently in, if any
     * @return the mine the player is currently in
     */
    public static Mine getMine(Player player) {
        for(Mine m: mines) {
            if(m.getPlayers().contains(player)) {
                return m;
            }
        }
        return null;
    }
}
