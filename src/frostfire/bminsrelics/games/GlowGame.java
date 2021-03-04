package frostfire.bminsrelics.games;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GlowGame extends Game{
    public GlowGame() {
        //so you're probably wonder what this is.
        //idk but its the only way I could figure out how to reference the plugin
        //I put it here and not in the game class to spread out the complexity
        
        //I put it back in the game class so we can keep complexity to a minimum.
        //- Brendon
        super("glowgame");
    }
    @Override
    void onJoin(Player p) {
        p.setGlowing(true);
    }
    @Override
    void Update(){
        for(Player p : OnlinePlayers()) {
            p.setGlowing(true);
        }
    }
    @EventHandler
    void onGameDeath(PlayerDeathEvent event){
        if(event.getEntity().getKiller() != null) {
            if (players.contains(event.getEntity()) && players.contains(event.getEntity().getKiller())) {
                players.remove(event.getEntity());
                event.getEntity().setGlowing(false);
                event.getEntity().sendMessage("You are out of the glow game. Better luck next time!");
                event.getEntity().getKiller().sendMessage("One more down");
            }
        }
    }
}
