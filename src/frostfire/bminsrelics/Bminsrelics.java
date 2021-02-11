package frostfire.bminsrelics;

import frostfire.bminsrelics.commands.RelicCommands;
import frostfire.bminsrelics.item.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Bminsrelics extends JavaPlugin {
    public String version = "0.0.1";
    public String updatemsg = "";
    @Override
    public void onEnable() {
        getCommand("relic").setExecutor(new RelicCommands());
        getLogger().info("Ethan and Brendon's epic SMP plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Ethan and Brendon's epic SMP plugin has been disabled.");
    }
}
