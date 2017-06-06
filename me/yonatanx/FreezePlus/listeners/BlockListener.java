package me.yonatanx.FreezePlus.listeners;

import me.yonatanx.FreezePlus.FreezePlus;
import me.yonatanx.FreezePlus.freeze.FreezeManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Created by Yonatan.
 */

public class BlockListener implements Listener {

    private FreezeManager fm;

    public BlockListener(){
        fm = FreezePlus.get().getFreezeManager();
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if (fm.isFrozen(event.getPlayer()))
            event.setCancelled(true);
        else if (fm.isServerFrozen() && !event.getPlayer().hasPermission("freezeplus.freezeserver.bypass")){
            event.getPlayer().sendMessage(ChatColor.RED + "The server is currently frozen.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        if (fm.isFrozen(event.getPlayer()))
            event.setCancelled(true);
        else if (fm.isServerFrozen() && !event.getPlayer().hasPermission("freezeplus.freezeserver.bypass")){
            event.getPlayer().sendMessage(ChatColor.RED + "The server is currently frozen.");
            event.setCancelled(true);
        }
    }
}
