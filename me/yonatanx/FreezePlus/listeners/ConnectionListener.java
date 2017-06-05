package me.yonatanx.FreezePlus.listeners;

import me.yonatanx.FreezePlus.freeze.FreezeManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Yonatan.
 */

public class ConnectionListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        if (FreezeManager.isFrozen(event.getPlayer())){
            for (Player player : Bukkit.getServer().getOnlinePlayers()){
                if (player.hasPermission("freezeplus.notify"))
                   player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "" + ChatColor.BOLD + "!" + ChatColor.RESET + ChatColor.GRAY + "] " + ChatColor.BOLD + event.getPlayer().getName() + ChatColor.RESET + ChatColor.GRAY + " has disconnected while frozen!");
            }
        }
    }
}
