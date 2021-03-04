package frostfire.bminsrelics.games;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GlowGame extends Game{
    public GlowGame() {
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
                EjectPlayer(event.getEntity());
                event.getEntity().setGlowing(false);
                event.getEntity().sendMessage("You are out of the glow game. Better luck next time!");
                if(players.size() == 1) {
                    event.getEntity().getKiller().sendMessage("You won yay!");
                    for (int i =0;i<100;i++) {
                        event.getEntity().getKiller().getWorld().spawn(event.getEntity().getKiller().getLocation(), ExperienceOrb.class);
                        End();
                    }
                }
                else{
                    event.getEntity().getKiller().sendMessage("You killed a glowing player!. " + String.valueOf(players.size() - 1) + " player to go.");
                }
            }
        }
    }
}
