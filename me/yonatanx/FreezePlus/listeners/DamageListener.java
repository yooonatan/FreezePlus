package me.yonatanx.FreezePlus.listeners;

import me.yonatanx.FreezePlus.freeze.FreezeManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Created by Yonatan.
 */

public class DamageListener implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player)
            if (FreezeManager.isFrozen((Player)event.getEntity()) || FreezeManager.isServerFrozen())
                event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player){
            if (!FreezeManager.isServerFrozen()) {
                if (event.getEntity() instanceof Player && FreezeManager.isFrozen((Player) event.getEntity())) {
                    ((Player) event.getDamager()).sendMessage(ChatColor.RED + "This player is currently frozen.");
                }
            } else ((Player) event.getDamager()).sendMessage(ChatColor.RED + "The server is currently frozen.");
        }
    }
}
