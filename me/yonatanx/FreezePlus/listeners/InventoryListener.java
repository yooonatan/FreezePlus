package me.yonatanx.FreezePlus.listeners;

import me.yonatanx.FreezePlus.FreezePlus;
import me.yonatanx.FreezePlus.freeze.FreezeManager;
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

    private FreezeManager fm;

    public InventoryListener(){
        fm = FreezePlus.get().getFreezeManager();
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if (fm.isFrozen((Player) event.getPlayer()))
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getPlayer().openInventory(fm.getFrozenInventory().getTopInv());
                }
            }.runTaskLater(FreezePlus.get(), 1L);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if (event.getWhoClicked() instanceof Player) {
            if (fm.isServerFrozen() && !event.getWhoClicked().hasPermission("freezeplus.freezeserver.bypass")){
                event.setCancelled(true);
            } else {
                if (fm.isFrozen((Player) event.getWhoClicked())) {
                    if (event.getCurrentItem() != null) {
                        if (event.getCurrentItem().equals(fm.getFrozenInventory().iDontHaveTeamSpeak())) {
                            ((Player) event.getWhoClicked()).chat("I don't have TeamSpeak, where can I download it?");
                        } else if (event.getCurrentItem().equals(fm.getFrozenInventory().imComing())) {
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
        if (fm.isFrozen(event.getPlayer()))
            event.setCancelled(true);
        else if (fm.isServerFrozen() && !event.getPlayer().hasPermission("freezeplus.freezeserver.bypass"))
            event.setCancelled(true);
    }
}
