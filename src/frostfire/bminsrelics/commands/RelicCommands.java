package frostfire.bminsrelics.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import frostfire.bminsrelics.Bminsrelics;
import frostfire.bminsrelics.item.ItemManager;
import frostfire.bminsrelics.item.Relic;

public class RelicCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Be a player!");
            return true;
        }
        Player player = (Player)sender;
        if(cmd.getName().equalsIgnoreCase("relic")) {
            Relic relic = ItemManager.GetRelic(args[0]);
            if(relic!=null) {
                player.getInventory().addItem(relic.item);
            }
            else {
                player.sendMessage("I've never heard of a "+args[0]+" before.");
            }
            return true;
        }
        else if(cmd.getName().equalsIgnoreCase("checkpoint")) {
            Bminsrelics.data.AddLocation(args[0], player.getLocation());
        }
        else if(cmd.getName().equalsIgnoreCase("send")) {
                Location end = Bminsrelics.data.GetLocation(args[0]);
                Player otherPlayer = Bukkit.getPlayer(args[0]);
                if(otherPlayer==null) {
                    player.sendMessage("That player doesn't appear to be online.");
                    return false;
                }
                else if(end==null) {
                    player.sendMessage("I don't recognize that checkpoint");
                }
                otherPlayer.getWorld().strikeLightningEffect(otherPlayer.getLocation());
                player.getWorld().strikeLightning(end);
                otherPlayer.teleport(end);
            }
            else {
                player.sendMessage("I don't recognize that checkpoint.");
            }
        return false;
    }
}
