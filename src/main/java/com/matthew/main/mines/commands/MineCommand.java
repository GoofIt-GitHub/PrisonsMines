package com.matthew.main.mines.commands;

import com.matthew.main.mines.apis.mines.Mine;
import com.matthew.main.mines.apis.mines.MineManager;
import com.matthew.main.mines.lists.MineState;
import com.matthew.main.mines.utils.MessageUtils;
import com.matthew.main.mines.utils.MineUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp() || player.hasPermission("mineplugin.commands")) {
                if (args.length == 2) {
                    switch (args[0]) {
                        case "reset":
                            if (MineUtils.isValidMine(args[1])) {
                                    MineManager.getMine(args[1]).resetMine();
                            } else {
                                MessageUtils.incorrectUsage(player);
                            }
                            break;

                        case "stop":
                            if (MineUtils.isValidMine(args[1])) {
                                Mine mine = MineManager.getMine(args[1]);
                                if(!mine.getMineState().equals(MineState.STOPPED)) {
                                    MineManager.getMine(args[1]).stopMine();
                                } else {
                                    player.sendMessage(ChatColor.BLUE + ">> " + mine.getColor().toString() + ChatColor.BOLD + mine.getName() + ChatColor.GRAY + " mine is already stopped");
                                }
                            } else {
                                MessageUtils.incorrectUsage(player);
                            }
                            break;

                        case "start":
                            if (MineUtils.isValidMine(args[1])) {
                                Mine mine = MineManager.getMine(args[1]);
                                if(!mine.getMineState().equals(MineState.RUNNING)) {
                                    MineManager.getMine(args[1]).startMine();
                                } else {
                                    player.sendMessage(ChatColor.BLUE + ">> " + mine.getColor().toString() + ChatColor.BOLD + mine.getName() + ChatColor.GRAY + " mine is already running");
                                }
                            } else {
                                MessageUtils.incorrectUsage(player);
                            }
                            break;

                        default:
                            MessageUtils.incorrectUsage(player);
                    }
                } else if (args.length == 1 && args[0].equalsIgnoreCase("list")){
                    MessageUtils.minesList(player);
                } else {
                    MessageUtils.incorrectUsage(player);
                }
            }
        }
        return false;
    }
}
