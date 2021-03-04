package frostfire.bminsrelics.commands;

import frostfire.bminsrelics.Bminsrelics;
import frostfire.bminsrelics.games.Game;
import frostfire.bminsrelics.games.GameDirectory;
import frostfire.bminsrelics.item.ItemManager;
import frostfire.bminsrelics.item.Relic;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class RelicCommandTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("relic")) {
            if(strings.length == 1){
                List<String> relics = new ArrayList<>();
                for (Relic relic : ItemManager.relics) {
                    relics.add(relic.directory);
                }
                return relics;
            }
        }
        else if(command.getName().equalsIgnoreCase("send")) {
            if(strings.length == 2) {
                List<String> list = Bminsrelics.data.GetLocations(strings[1]);
                return list;
            }
            else if(strings.length == 3) {
                List<String> bools = new ArrayList<>();
                bools.add("true");
                bools.add("false");
                return bools;
            }
        }
        else if(command.getName().equalsIgnoreCase("minigame")) {
            if(strings.length == 2) {
                List<String> list = new ArrayList<>();
                list.add("start");
                list.add("end");
                return list;
            }
            else if(strings.length == 3) {
                List<String> names = new ArrayList<>();
                for(Game g : GameDirectory.allGames){
                    names.add(g.name);
                }
                return names;
            }
        }
        return null;
    }
}
