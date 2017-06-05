package me.yonatanx.FreezePlus.listeners;

import me.yonatanx.FreezePlus.freeze.FreezeManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Yonatan.
 */

public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){
        if (FreezeManager.isServerFrozen() && !event.getPlayer().hasPermission("freezeplus.freezeserver.bypass")) {
            event.getPlayer().sendMessage(ChatColor.RED + "The server is currently frozen.");
            event.setCancelled(true);
        }
    }
}
