package frostfire.bminsrelics.games;

import frostfire.bminsrelics.Bminsrelics;
import org.bukkit.Bukkit;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class GameDirectory {
    public static Game[] allGames = {new GlowGame(Bminsrelics.world)};
    public static List<Game> activeGames = new ArrayList<Game>();
    public static boolean getActive(String game){
        for(int i = 0;i<activeGames.size();i++){
            if(activeGames.get(i).name == game){
                return true;
            }
        }
        return false;
    }
    public static boolean getExists(String game){
        for(int i = 0;i<allGames.length;i++){
            if(allGames[i].name == game){
                return true;
            }
        }
        return false;
    }
    public static Game getGame(String game){
        for(int i = 0;i<allGames.length;i++){
            if(allGames[i].name == game){
                return allGames[i];
            }
        }
        return null;
    }
    public static void StartGame(Game g){
        g.Init(20L,0L);
        activeGames.add(g);
    }
    public static void EndGame(Game g){
        g.End();
    }
}
