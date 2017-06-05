package me.yonatanx.FreezePlus.cmds;

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
        if (commandSender.hasPermission("freezeplus.freeze")){
            if (strings.length == 1){
                Player player = Bukkit.getPlayer(strings[0]);

                if (!FreezeManager.isFrozen(player)) {
                    if (!player.hasPermission("freezeplus.freeze.bypass")) {
                        FreezeManager.freezePlayer(player, commandSender instanceof Player ? (Player) commandSender : null);
                        commandSender.sendMessage(ChatColor.GRAY + "You have froze " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + ".");
                    }
                    else commandSender.sendMessage(ChatColor.RED + "This player cannot be frozen.");
                }
                else {
                    FreezeManager.unfreezePlayer(player);
                    commandSender.sendMessage(ChatColor.GRAY + "You have unfroze " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + ".");
                }

            }
            else commandSender.sendMessage(ChatColor.RED + getSyntax());
        }
        else commandSender.sendMessage(ChatColor.RED + "No permission.");
        return true;
    }

    private String getSyntax(){
        return "/freeze <player>";
    }
}
