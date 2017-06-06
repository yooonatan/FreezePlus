package me.yonatanx.FreezePlus.listeners;

import me.yonatanx.FreezePlus.FreezePlus;
import me.yonatanx.FreezePlus.freeze.FreezeManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Yonatan.
 */

public class MovementListener implements Listener {

    private FreezeManager fm;

    public MovementListener(){
        fm = FreezePlus.get().getFreezeManager();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        if (fm.isFrozen(event.getPlayer()) || fm.isServerFrozen() && !event.getPlayer().hasPermission("freezeplus.freezeserver.bypass"))
            event.setTo(event.getFrom());
    }
}
