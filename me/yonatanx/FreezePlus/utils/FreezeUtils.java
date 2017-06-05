package me.yonatanx.FreezePlus.utils;

import me.yonatanx.FreezePlus.FreezePlus;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by Yonatan.
 */

public class FreezeUtils {

    public static String getTeamSpeak(){
        return FreezePlus.get().getConfig().getString("teamspeak_ip");
    }

    public static String getInventoryTitle(){
        return ChatColor.translateAlternateColorCodes('&', FreezePlus.get().getConfig().getString("inventory_title"));
    }

    public static ItemStack applyFrozenIM(ItemStack itemStack){
        ItemStack itemStack1 = itemStack;
        ItemMeta itemMeta = itemStack1.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', FreezePlus.get().getConfig().getString("frozen_item.name")));
        itemMeta.setLore(withColor(FreezePlus.get().getConfig().getStringList("frozen_item.lore")));
        itemStack1.setItemMeta(itemMeta);
        return itemStack1;
    }

    private static List<String> withColor(List<String> list){
        List<String> list1 = list;
        for (int i = 0; i < list1.size(); i++) {
            list1.set(i, ChatColor.translateAlternateColorCodes('&', list1.get(i)).replaceAll("%teamspeak_ip%", getTeamSpeak()));
        }
        return list1;
    }

    public static List<String> getFrozenServerMessage(){
        return withColor(FreezePlus.get().getConfig().getStringList("frozen_server_message"));
    }

    public static List<String> getUnfrozenServerMessage(){
        return withColor(FreezePlus.get().getConfig().getStringList("unfrozen_server_message"));
    }
}
