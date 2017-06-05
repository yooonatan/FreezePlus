package me.yonatanx.FreezePlus.listeners;

import me.yonatanx.FreezePlus.freeze.FreezeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Yonatan.
 */

public class MovementListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (FreezeManager.isFrozen(event.getPlayer()) || FreezeManager.isServerFrozen() && !event.getPlayer().hasPermission("freezeplus.freezeserver.bypass"))
            event.setTo(event.getFrom());
    }
}
