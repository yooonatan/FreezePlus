package me.yonatanx.FreezePlus.cmds;

import me.yonatanx.FreezePlus.FreezePlus;
import me.yonatanx.FreezePlus.freeze.FreezeManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Yonatan.
 */

public class UnfreezeServerCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("freezeplus.unfreezeserver")){
            commandSender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }

        FreezeManager fm = FreezePlus.get().getFreezeManager();
        if (fm.isServerFrozen()) fm.unfreezeServer();
        else commandSender.sendMessage(ChatColor.RED + "Server isn't frozen!");

        return true;
    }
}
