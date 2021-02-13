package frostfire.bminsrelics.commands;

import frostfire.bminsrelics.item.ItemManager;
import frostfire.bminsrelics.item.Relic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class RelicCommandTabCompletion implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 1){
            List<String> relics = new ArrayList<>();
            for (Relic relic : ItemManager.relics) {
                relics.add(relic.directory);
            }
            return relics;
        }
        return null;
    }
}
