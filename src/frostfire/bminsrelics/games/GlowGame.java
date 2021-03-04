package frostfire.bminsrelics.games;

import frostfire.bminsrelics.Bminsrelics;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class GlowGame extends Game{
    public World world;
    public GlowGame(World w) {
        //so you're probably wonder what this is.
        //idk but its the only way I could figure out how to reference the plugin
        //I put it here and not in the game class to spread out the complexity
        super(Bminsrelics.getPlugin(Bminsrelics.class),"glow game", true);
        world = w;
    }
    @Override
    public void onStart(){
        players = world.getPlayers();
    }
    @Override
    public void Update(){
        for(int i =0;i<(players.size()-1);i++){
            players.get(i).setGlowing(true);
        }
    }

}
