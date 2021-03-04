package frostfire.bminsrelics.games;

import frostfire.bminsrelics.Bminsrelics;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public abstract class Game implements Listener{
    public int life;
    public String name;
    boolean running = false;
    boolean run_forever = false;
    Bminsrelics plugin;
    List<OfflinePlayer> players;
    public Game(String title, int lengthInTicks){
        name = title;
        life = lengthInTicks;
        plugin = Bminsrelics.getPlugin(Bminsrelics.class);
    }
    public Game(String title){
        name = title;
        life = 1000;
        run_forever = true;
        plugin = Bminsrelics.getPlugin(Bminsrelics.class);
    }
    void Update(){

    }
    public void UpdatePlayerList() {
        List<OfflinePlayer> temp = new ArrayList<>();
        for(String p : Bminsrelics.data.getPlayerList(name)) {
            temp.add(Bukkit.getOfflinePlayer(UUID.fromString(p)));
        }
        players=temp;
    }
    List<Player> OnlinePlayers() {
        List<Player> players = new ArrayList<>();
        for(OfflinePlayer op : players) {
            Player p = Bukkit.getPlayer(op.getUniqueId());
            if(p!=null) {
                players.add(p);
            }
        }
        return players;
    }
    public void Init(long frequency, long delay){
        running = true;
        getServer().getPluginManager().registerEvents(this, plugin);
        BukkitScheduler scheduler = getServer().getScheduler();
        for(Player p : Bukkit.getOnlinePlayers()) {
            AddPlayer(p);
        }
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Update();
            }
        }, delay, frequency);
        UpdatePlayerList();
        onStart();
    }
    void onStart(){}
    void onJoin(Player p) {}
    void onLeave(Player p) {}
    public void End(){
        HandlerList.unregisterAll(this);
        running = false;
        GameDirectory.activeGames.remove(this);
    }
    @EventHandler
    public void OnJoinGame(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if(!Bminsrelics.data.hasParticipated(name, p)) {
            AddPlayer(p);
        }
        else if(Bminsrelics.data.GetParticipating(name, p)) {
            onJoin(p);
        }
    }
    public void AddPlayer(Player p) {
        Bminsrelics.data.SetPlayerParticipation(name, p, true);
        onJoin(p);
    }
    public void EjectPlayer(Player p) {
        Bminsrelics.data.SetPlayerParticipation(name, p, false);
        onLeave(p);
    }
}
