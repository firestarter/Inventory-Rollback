package me.danjono.inventoryrollback;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.danjono.inventoryrollback.commands.Commands;
import me.danjono.inventoryrollback.config.ConfigFile;
import me.danjono.inventoryrollback.listeners.ClickGUI;
import me.danjono.inventoryrollback.listeners.EventLogs;

public class InventoryRollback extends JavaPlugin {

    public static final Logger logger = Logger.getLogger("Minecraft");
    private static InventoryRollback instance;

    private static String packageVersion;
    
    public static InventoryRollback getInstance() {
        return instance;
    }
    
    public static String getPluginVersion() {
        return instance.getDescription().getVersion();  
    }
    
    public static String getPackageVersion() {
        return packageVersion;
    }
    
    @Override
    public void onEnable() {
        instance = this;
        packageVersion = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];

        startupTasks();

        this.getCommand("inventoryrollback").setExecutor(new Commands());

        this.getServer().getPluginManager().registerEvents(new ClickGUI(), instance);
        this.getServer().getPluginManager().registerEvents(new EventLogs(), instance);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static void startupTasks() {
        ConfigFile config = new ConfigFile();
        config.setVariables();
        config.createStorageFolders();
    }

    public enum VersionName {
        v1_8,
        v1_9_v1_12,
        v1_13_PLUS
    }
    
    private static VersionName version = VersionName.v1_13_PLUS;
    
    public static VersionName getVersion() {
        return version;
    }
}
