package com.matthew.main.mines.apis.mines;

import com.matthew.main.mines.apis.Config;
import com.matthew.main.mines.apis.Cuboid;
import com.matthew.main.mines.lists.MineState;
import com.matthew.main.mines.lists.Mines;
import com.matthew.main.mines.playermines.MineTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Mine {

    private ArrayList<Player> players;

    private Mines mines;
    private MineState mineState;
    private Cuboid mineLoc;
    private Material targetResetType;
    private Material ore;
    private MineTimer mineTimer;

    //init is used to check if the timer has already been initially started or not
    private boolean init;


    /**
     * Constructs a new mine object when called, which is assigned a name as a unique identifier
     * Each mine has an arraylist of players that is filled with players who are currently in the specific mine's region
     * Once a mine is created it is to automatically presume the state of stopped
     *
     * @param name - name of the mine corresponding to the enum values in Mines.
     * @param mineLoc - region of the mine
     */
    public Mine(String name, Cuboid mineLoc) {
        players = new ArrayList<>();
        mines = Mines.valueOf(name);
        mineState = MineState.STOPPED;
        this.mineLoc = mineLoc;
        targetResetType = Config.getReplacementType(name);
        ore = Config.getOreType(name);
        mineTimer = new MineTimer(this);
        init = false;

    }

    /**
     * Get the name of the mine
     *
     * @return the name of the enum value
     */
    public String getName() {
        return mines.getName();
    }

    /**
     * Get the chatcolor of the mine
     *
     * @return the chatcolor of the enum value
     */
    public ChatColor getColor() {
        return mines.getColor();
    }

    /**
     * Upon player add, announce in chat to the specified player which mine they are entering
     *
     * @param player - player to be added to the mine
     */
    public void addPlayer(Player player) {
        players.add(player);
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Now entering " + mines.getColor() + ChatColor.BOLD.toString() + "" + mines.getName() + ChatColor.GRAY + " mine");
    }

    /**
     * Upon removing a player, announce in chat to the specified player which mine they are leaving
     *
     * @param player - player to be removed from the mine
     */
    public void removePlayer(Player player) {
        players.remove(player);
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Now leaving " + mines.getColor() + ChatColor.BOLD.toString() + "" + mines.getName() + ChatColor.GRAY + " mine");
    }

    /**
     * Get all of the players currently in the mine
     *
     * @return the arraylist of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Get the region of the mine
     *
     * @return the region of the mine
     */
    public Cuboid getMineLoc() {
        return mineLoc;
    }

    /**
     * Get the state that the mine is currently in
     *
     * @return the enum value referring to the state the mine is in (Running, stopped)
     */
    public MineState getMineState() {
        return mineState;
    }

    /**
     * Set the state of the mine
     *
     * @param mineState - the MineState that the mine is to be set to
     */
    public void setMineState(MineState mineState) {
        this.mineState = mineState;
    }

    /**
     * Get the ore type of the mine
     *
     * @return the ore
     */
    public Material getOre() {
        return ore;
    }

    /**
     * Get the block type that is used for choosing where a ore may spawn
     *
     * @return the block type
     */
    public Material getTargetResetType() {
        return targetResetType;
    }

    /**
     * Send a message to everyone in the mine
     *
     * @param message - message to send to the players
     */
    public void sendMessage(String message) {
        for (Player p : players) {
            p.sendMessage(message);
        }
    }

    /**
     * Stop the mine by setting the state to "STOPPED" and announce to everyone that the mine has been stopped.
     * This will cause the MineTimer to not count down the amount of seconds until it is to be reset, so it
     * simply pauses the counter
     */
    public void stopMine() {
        mineState = MineState.STOPPED;
        Bukkit.broadcastMessage(ChatColor.BLUE + ">> " + mines.getColor() + ChatColor.BOLD.toString() + mines.getName() + "" + ChatColor.GRAY + " mine has been stopped!");
    }

    /**
     * Begin the MineTimer and set the state of the mine to running. Additionally announce to everyone that the mine has been started
     */
    public void startMine() {
        if (!init) {
            mineTimer.begin();
            init = true;
        }
        mineState = MineState.RUNNING;
        Bukkit.broadcastMessage(ChatColor.BLUE + ">> " + mines.getColor() + ChatColor.BOLD.toString() + mines.getName() + "" + ChatColor.GRAY + " mine has been started!");

    }

    /**
     * reset the mine and seconds until the mine is to be reset
     */
    public void resetMine() {

        ArrayList<Block> targetBlocks = new ArrayList<>();

        /**
         * Set all Ore to targetBlock
         */
        for (Block block : mineLoc.getBlocks()) {
            if (block.getType().equals(ore)) {
                block.setType(targetResetType);
            }
        }

        /**
         * Add all target blocks in the mine region to targetBlocks list
         */
        for (Block block : mineLoc.getBlocks()) {
            if (block.getType().equals(targetResetType)) {
                targetBlocks.add(block);
            }
        }

        /**
         * Randomly select 3/4 of the targetBlock to be turned into the ore
         */
        for (int i = 0; i < targetBlocks.size() * 0.75; i++) {
            int randomIndex = (int) (Math.random() * targetBlocks.size());
            targetBlocks.get(randomIndex).setType(ore);
        }

        mineState = MineState.RUNNING;
        mineTimer.setSeconds((int) Config.getResetTime(getName()));
        Bukkit.broadcastMessage(ChatColor.BLUE + ">> " + mines.getColor() + ChatColor.BOLD.toString() + mines.getName() + "" + ChatColor.GRAY + " mine has reset!");

    }

}
