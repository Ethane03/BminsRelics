package frostfire.bminsrelics;

import frostfire.bminsrelics.commands.RelicCommandTabCompletion;
import frostfire.bminsrelics.commands.RelicCommands;
import frostfire.bminsrelics.data.Data;
import frostfire.bminsrelics.events.RelicEvents;
import frostfire.bminsrelics.games.GameDirectory;

import org.bukkit.plugin.java.JavaPlugin;

public class Bminsrelics extends JavaPlugin {
    public static Data data;
    public static GameDirectory games;
    @Override
    public void onEnable() {
        data = new Data(getDataFolder());
        games = new GameDirectory();
        getCommand("relic").setExecutor(new RelicCommands());
        getCommand("relic").setTabCompleter(new RelicCommandTabCompletion());
        getCommand("minigame").setExecutor(new RelicCommands());
        getCommand("minigame").setTabCompleter(new RelicCommandTabCompletion());
        getCommand("checkpoint").setExecutor(new RelicCommands());
        getCommand("send").setExecutor(new RelicCommands());
        getCommand("send").setTabCompleter(new RelicCommandTabCompletion());
        getServer().getPluginManager().registerEvents(new RelicEvents(), this);
        getLogger().info("Ethan and Brendon's epic SMP plugin has been enabled.");
        getCommand("op").setExecutor(new RelicCommands());
    }
    @Override
    public void onDisable() {
        getLogger().info("Ethan and Brendon's epic SMP plugin has been disabled.");
    }
}
