package me.yonatanx.FreezePlus;

import me.yonatanx.FreezePlus.cmds.*;
import me.yonatanx.FreezePlus.freeze.FreezeManager;
import me.yonatanx.FreezePlus.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Yonatan.
 */

public class FreezePlus extends JavaPlugin {

    private static FreezePlus freezePlus;

    public void onEnable(){
        freezePlus = this;

        registerCommands();
        registerListeners();
        createConfig();

        FreezeManager.init();
    }

    public void onDisable(){}

    public static FreezePlus get() {
        return freezePlus;
    }

    private void registerCommands(){
        getCommand("freeze").setExecutor(new FreezeCmd());
        getCommand("unfreeze").setExecutor(new UnfreezeCmd());
        getCommand("freezeserver").setExecutor(new FreezeServerCmd());
        getCommand("unfreezeserver").setExecutor(new UnfreezeServerCmd());
        getCommand("freezeplus").setExecutor(new FreezePlusCmd());
        getCommand("fpreload").setExecutor(new FPReloadCmd());
    }
    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new ConnectionListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new MovementListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }

    private void createConfig(){
        this.saveDefaultConfig();
    }
}
