package me.yonatanx.FreezePlus.listeners;

import me.yonatanx.FreezePlus.FreezePlus;
import me.yonatanx.FreezePlus.freeze.FreezeManager;
import me.yonatanx.FreezePlus.freeze.FrozenInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by Yonatan.
 */

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if (FreezeManager.isFrozen((Player) event.getPlayer()))
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().openInventory(FrozenInventory.getTopInv());
                }
            }.runTaskLater(FreezePlus.get(), 1L);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player) {
            if (FreezeManager.isServerFrozen() && !event.getWhoClicked().hasPermission("freezeplus.freezeserver.bypass")){
                event.setCancelled(true);
            } else {
                if (FreezeManager.isFrozen((Player) event.getWhoClicked())) {
                    if (event.getCurrentItem() != null) {
                        if (event.getCurrentItem().equals(FrozenInventory.iDontHaveTeamSpeak())) {
                            ((Player) event.getWhoClicked()).chat("I don't have TeamSpeak, where can I download it?");
                        } else if (event.getCurrentItem().equals(FrozenInventory.imComing())) {
                            ((Player) event.getWhoClicked()).chat("Okay, I'm coming!");

                        }
                    }
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event){
        if (FreezeManager.isFrozen(event.getPlayer()))
            event.setCancelled(true);
        else if (FreezeManager.isServerFrozen() && !event.getPlayer().hasPermission("freezeplus.freezeserver.bypass"))
            event.setCancelled(true);
    }
}
