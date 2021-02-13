package frostfire.bminsrelics;

import frostfire.bminsrelics.commands.RelicCommandTabCompletion;
import frostfire.bminsrelics.commands.RelicCommands;
import frostfire.bminsrelics.data.Data;
import frostfire.bminsrelics.events.RelicEvents;
import org.bukkit.plugin.java.JavaPlugin;

public class Bminsrelics extends JavaPlugin {
    public String version = "0.0.1";
    public String updatemsg = "";
    public static Data data;
    @Override
    public void onEnable() {
        data = new Data(getDataFolder());
        getCommand("relic").setExecutor(new RelicCommands());
        getCommand("relic").setTabCompleter(new RelicCommandTabCompletion());
        getCommand("checkpoint").setExecutor(new RelicCommands());
        getCommand("send").setExecutor(new RelicCommands());
        getServer().getPluginManager().registerEvents(new RelicEvents(), this);
        getLogger().info("Ethan and Brendon's epic SMP plugin has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Ethan and Brendon's epic SMP plugin has been disabled.");
    }
}
