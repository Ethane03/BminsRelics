package frostfire.bminsrelics.games;

import frostfire.bminsrelics.Bminsrelics;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import static org.bukkit.Bukkit.getServer;

public abstract class Game {
    public int life;
    public String name;
    public boolean running = false;
    public boolean run_forever = false;
    public Bminsrelics plugin;
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
    public void Action(){

    }
    public void Start(long frequency, long delay){
        running = true;
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Update();
            }
        }, 0L, 20L);
    }
    public void End(){
        running = false;
    }
}
