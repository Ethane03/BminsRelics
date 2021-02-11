package frostfire.bminsrelics.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import frostfire.bminsrelics.item.ItemManager;

public class RelicCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Be a player!");
            return true;
        }
        Player player = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("relic")) {
            player.getInventory().addItem(ItemManager.wand);
        }
        return true;
    }
}
