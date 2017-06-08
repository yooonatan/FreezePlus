package me.yonatanx.FreezePlus.cmds;

import me.yonatanx.FreezePlus.FreezePlus;
import me.yonatanx.FreezePlus.freeze.FreezeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Yonatan.
 */

public class FreezeCmd implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!commandSender.hasPermission("freezeplus.freeze")){
            commandSender.sendMessage(ChatColor.RED + "No permission.");
            return true;
        }

        if (strings.length != 1){
            commandSender.sendMessage(ChatColor.RED + getSyntax());
            return true;
        }

        Player player = Bukkit.getPlayer(strings[0]);
        FreezeManager fm = FreezePlus.get().getFreezeManager();

        if (player == null){
            commandSender.sendMessage(ChatColor.RED + "Player could not be found.");
            return true;
        }

        if (player.hasPermission("freezeplus.freeze.bypass")){
            commandSender.sendMessage(ChatColor.RED + "This player cannot be frozen.");
            return true;
        }

        if (!fm.isFrozen(player)){
            fm.freezePlayer(player, commandSender instanceof Player ? (Player) commandSender : null);
            commandSender.sendMessage(ChatColor.GRAY + "You have froze " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + ".");
        }
        else {
            fm.unfreezePlayer(player);
            commandSender.sendMessage(ChatColor.GRAY + "You have unfroze " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + ".");
        }

        return true;
    }

    private String getSyntax(){
        return "/freeze <player>";
    }
}
