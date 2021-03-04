package frostfire.bminsrelics.games;

import frostfire.bminsrelics.Bminsrelics;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public abstract class Game {
    public int life;
    public String name;
    public boolean running = false;
    public boolean run_forever = false;
    public Bminsrelics plugin;
    public List<Player> players;
    public Game(Bminsrelics plug, String title, int lengthInTicks){
        name = title;
        life = lengthInTicks;
        plugin = plug;
    }
    public Game(Bminsrelics plug, String title, boolean forev){
        name = title;
        life = 1000;
        run_forever = forev;
        plugin = plug;
    }
    public void Update(){

    }
    public void RemovePlayer(Player p){
        players.remove(p);
    }
    public void Action(){

    }
    public void Init(long frequency, long delay){
        running = true;
        players = new ArrayList<Player>();
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Update();
            }
        }, delay, frequency);
        onStart();
    }
    public void onStart(){
    }
    public void End(){
        running = false;
        GameDirectory.activeGames.remove(this);
    }
}
