package me.yonatanx.FreezePlus.freeze;

import me.yonatanx.FreezePlus.FreezePlus;
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
    private static boolean frozen;
    private static Map<UUID, UUID> frozenPlayers;
    private static Map<UUID, ItemStack[]> oldInventories;
    private static int taskID;

    public static void init(){
        frozen = false;
        frozenPlayers = new HashMap<>();
        oldInventories = new HashMap<>();
    }

    public static void freezeServer(){
        frozen = true;
        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                FreezeUtils.getFrozenServerMessage().forEach(Bukkit::broadcastMessage
                );
            }
        }.runTaskTimer(FreezePlus.get(), 1L, 300);

        taskID = task.getTaskId();
    }

    public static void unfreezeServer(){
        frozen = false;
        Bukkit.getScheduler().cancelTask(taskID);

        FreezeUtils.getUnfrozenServerMessage().forEach(Bukkit::broadcastMessage
        );
    }

    public static void freezePlayer(Player player, Player freezer) {
        frozenPlayers.put(player.getUniqueId(), freezer == null ? null : freezer.getUniqueId());
        oldInventories.put(player.getUniqueId(), player.getInventory().getContents());

        player.getInventory().clear();

        for (int i = 0; i < FrozenInventory.getBottomInv().length; i++)
            if (FrozenInventory.getBottomInv()[i] != null)
                player.getInventory().setItem(i, FrozenInventory.getBottomInv()[i]);

        player.openInventory(FrozenInventory.getTopInv());

    }

    public static void unfreezePlayer(Player player){
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

    public static boolean isFrozen(Player player){
        return frozenPlayers.containsKey(player.getUniqueId());
    }
    public static boolean isServerFrozen(){
        return frozen;
    }

    public static UUID getFreezer(UUID playerUUID){
        return frozenPlayers.get(playerUUID);
    }

    public static Player[] getFrozenPlayers(){
        return (Player[]) frozenPlayers.keySet().toArray();
    }

}
