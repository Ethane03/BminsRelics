package frostfire.bminsrelics.games;

import frostfire.bminsrelics.Bminsrelics;
import org.bukkit.entity.Player;

public class GlowGame extends Game{
    public Player[] players;
    public GlowGame(String title, int lengthInTicks) {
        //so you're probably wonder what this is.
        //idk but its the only way I could figure out how to reference the plugin
        //I put it here and not in the game class to spread out the complexity
        super(Bminsrelics.getPlugin(Bminsrelics.class),"Glow Game", true);
    }
    @Override
    public void Update(){

    }
}
