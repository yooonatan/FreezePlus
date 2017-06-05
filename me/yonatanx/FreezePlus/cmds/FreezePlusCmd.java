package me.yonatanx.FreezePlus.cmds;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Yonatan.
 */

public class FreezePlusCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("freezeplus.help")){
            commandSender.sendMessage(ChatColor.GRAY + "------------------------------------------------------------");
            commandSender.sendMessage(ChatColor.AQUA + "/freeze <player> - Freeze/Unfreeze a player.");
            commandSender.sendMessage(ChatColor.AQUA + "/unfreeze <player> - Unfreeze a player.");
            commandSender.sendMessage(ChatColor.AQUA + "/freezeserver - Freeze everyone on the entire server.");
            commandSender.sendMessage(ChatColor.AQUA + "/fpreload - Reloads the config.");
            commandSender.sendMessage(ChatColor.GRAY + "------------------------------------------------------------");
        }
        else commandSender.sendMessage(ChatColor.RED + "No permission.");
        return true;
    }
}
