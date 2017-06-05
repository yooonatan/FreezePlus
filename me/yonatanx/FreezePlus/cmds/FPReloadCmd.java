package me.yonatanx.FreezePlus.cmds;

import me.yonatanx.FreezePlus.FreezePlus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Yonatan.
 */

public class FPReloadCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("freezeplus.reloadcfg")){
            FreezePlus.get().reloadConfig();
            commandSender.sendMessage(ChatColor.GREEN + "FreezePlus config has been reloaded.");
        }
        else commandSender.sendMessage(ChatColor.RED + "No permission.");
        return true;
    }
}
