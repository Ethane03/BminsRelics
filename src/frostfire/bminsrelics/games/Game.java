package frostfire.bminsrelics.games;

import frostfire.bminsrelics.Bminsrelics;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.bukkit.Bukkit.getServer;

public abstract class Game implements Listener{
    public int life;
    public String name;
    boolean run_forever = false;
    Bminsrelics plugin;
    List<OfflinePlayer> players;
    int TaskId;
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
        getServer().getPluginManager().registerEvents(this, plugin);
        Bminsrelics.data.SetGameActive(name, true);
        for(Player p : Bukkit.getOnlinePlayers()) {
            AddPlayer(p);
        }
        OnReload();
        onStart();
    }
    public void OnReload() {
        BukkitScheduler scheduler = getServer().getScheduler();
        TaskId = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Update();
            }
        }, 20L, 0);
        UpdatePlayerList();
    }
    void onStart(){}
    void onJoin(Player p) {}
    void onLeave(Player p) {
        p.setGlowing(false);
    }
    public void End(){
        HandlerList.unregisterAll(this);
        GameDirectory.activeGames.remove(this);
        Bminsrelics.data.SetGameActive(name, false);
        getServer().getScheduler().cancelTask(TaskId);
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
        UpdatePlayerList();
    }
    public void EjectPlayer(Player p) {
        Bminsrelics.data.SetPlayerParticipation(name, p, false);
        onLeave(p);
        UpdatePlayerList();
    }
    public Double GetDouble(String v,Double def) {
        return (Double)Bminsrelics.data.GetGameVariable(name, v, def);
    }
    public Double GetDouble(String v) {
        return GetDouble(v, 0.0);
    }
    public String GetString(String v,String def) {
        return (String)Bminsrelics.data.GetGameVariable(name, v, def);
    }
    public String GetString(String v) {
        return GetString(v, "");
    }
    public void Set(String v, Object i) {
        Bminsrelics.data.SetGameVariable(name, v, i);
    }
    public void Get(String v, Object i) {
        Bminsrelics.data.GetGameVariable(name, v, i);
    }
}
