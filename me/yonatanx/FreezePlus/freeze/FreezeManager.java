package me.yonatanx.FreezePlus.freeze;

import me.yonatanx.FreezePlus.utils.FreezeUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Yonatan.
 */

public class FreezeManager {
    private FrozenInventory frozenInventory;
    private boolean frozen;
    private Map<UUID, UUID> frozenPlayers;
    private Map<UUID, ItemStack[]> oldInventories;
    private int taskID;

    public FreezeManager(){
        frozen = false;
        frozenPlayers = new HashMap<>();
        oldInventories = new HashMap<>();
        frozenInventory = new FrozenInventory();
    }

    public void freezeServer(){
        frozen = true;
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                FreezeUtils.getFrozenServerMessage().forEach(Bukkit::broadcastMessage
                );
            }
        }.runTaskTimer(Bukkit.getPluginManager().getPlugin("FreezePlus"), 1L, 300);

        taskID = task.getTaskId();
    }

    public void unfreezeServer(){
        frozen = false;
        Bukkit.getScheduler().cancelTask(taskID);

        FreezeUtils.getUnfrozenServerMessage().forEach(Bukkit::broadcastMessage
        );
    }

    public void freezePlayer(Player player, Player freezer) {
        frozenPlayers.put(player.getUniqueId(), freezer == null ? null : freezer.getUniqueId());
        oldInventories.put(player.getUniqueId(), player.getInventory().getContents());

        player.getInventory().clear();

        for (int i = 0; i < frozenInventory.getBottomInv().length; i++)
            if (frozenInventory.getBottomInv()[i] != null)
                player.getInventory().setItem(i, frozenInventory.getBottomInv()[i]);

        player.openInventory(frozenInventory.getTopInv());

    }

    public void unfreezePlayer(Player player){
        if (oldInventories.containsKey(player.getUniqueId())){
            player.getInventory().clear();
            for (int i = 0; i < player.getInventory().getSize(); i++) {
                if (oldInventories.get(player.getUniqueId())[i] != null)
                    player.getInventory().setItem(i, oldInventories.get(player.getUniqueId())[i]);
            }
        }

        frozenPlayers.remove(player.getUniqueId());
        oldInventories.remove(player.getUniqueId());

        player.updateInventory();
        player.closeInventory();
    }

    public boolean isFrozen(Player player){
        return frozenPlayers.containsKey(player.getUniqueId());
    }
    public boolean isServerFrozen(){
        return frozen;
    }

    public UUID getFreezer(UUID playerUUID){
        return frozenPlayers.get(playerUUID);
    }

    public Player[] getFrozenPlayers(){
        return (Player[]) frozenPlayers.keySet().toArray();
    }

    public FrozenInventory getFrozenInventory() {
        return frozenInventory;
    }
}
