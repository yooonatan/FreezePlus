package me.yonatanx.FreezePlus.freeze;

import me.yonatanx.FreezePlus.utils.FreezeUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Yonatan.
 */

public class FrozenInventory {

    public static Inventory getTopInv(){
        Inventory inventory = Bukkit.createInventory(null, 54, FreezeUtils.getInventoryTitle());

        fillWithPane(inventory, DyeColor.WHITE, 0, 4);
        fillWithPane(inventory, DyeColor.RED, 4, 1);
        fillWithPane(inventory, DyeColor.WHITE, 5, 4);

        fillWithPane(inventory, DyeColor.WHITE, 9, 3);
        fillWithPane(inventory, DyeColor.RED, 12, 1);
        fillWithPane(inventory, DyeColor.BLACK, 13, 1);
        fillWithPane(inventory, DyeColor.RED, 14, 1);
        fillWithPane(inventory, DyeColor.WHITE, 15, 3);

        fillWithPane(inventory, DyeColor.WHITE, 18, 2);
        fillWithPane(inventory, DyeColor.RED, 20, 1);
        fillWithPane(inventory, DyeColor.YELLOW, 21, 1);
        fillWithPane(inventory, DyeColor.BLACK, 22, 1);
        fillWithPane(inventory, DyeColor.YELLOW, 23, 1);
        fillWithPane(inventory, DyeColor.RED, 24, 1);
        fillWithPane(inventory, DyeColor.WHITE, 25, 2);

        fillWithPane(inventory, DyeColor.WHITE, 27, 2);
        fillWithPane(inventory, DyeColor.RED, 29, 1);
        fillWithPane(inventory, DyeColor.YELLOW, 30, 1);
        fillWithPane(inventory, DyeColor.BLACK, 31, 1);
        fillWithPane(inventory, DyeColor.YELLOW, 32, 1);
        fillWithPane(inventory, DyeColor.RED, 33, 1);
        fillWithPane(inventory, DyeColor.WHITE, 34, 2);

        fillWithPane(inventory, DyeColor.WHITE, 36, 1);
        fillWithPane(inventory, DyeColor.RED, 37, 1);
        fillWithPane(inventory, DyeColor.YELLOW, 38, 5);
        fillWithPane(inventory, DyeColor.RED, 43, 1);
        fillWithPane(inventory, DyeColor.WHITE, 44, 1);

        fillWithPane(inventory, DyeColor.RED, 45, 1);
        fillWithPane(inventory, DyeColor.YELLOW, 46, 3);
        fillWithPane(inventory, DyeColor.BLACK, 49, 1);
        fillWithPane(inventory, DyeColor.YELLOW, 50, 3);
        fillWithPane(inventory, DyeColor.RED, 53, 1);

        return inventory;
    }

    public static ItemStack[] getBottomInv(){
        return new ItemStack[]{imComing(), iDontHaveTeamSpeak()};
    }

    public static ItemStack imComing(){
        ItemStack itemStack = new ItemStack(Material.WOOL, 1, DyeColor.LIME.getData());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "I'm coming!");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack iDontHaveTeamSpeak(){
        ItemStack itemStack = new ItemStack(Material.WOOL, 1, DyeColor.RED.getData());
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "I don't have TeamSpeak!");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private static void fillWithPane(Inventory inventory, DyeColor color, int start, int count){
        ItemStack itemStack = new ItemStack(Material.STAINED_GLASS_PANE, 1, color.getData());
        itemStack.setItemMeta(FreezeUtils.applyFrozenIM(itemStack).getItemMeta());

        for (int i = 0; i < count; i++)
            inventory.setItem(start + i, itemStack);
    }
}
