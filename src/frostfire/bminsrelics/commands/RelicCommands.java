package frostfire.bminsrelics.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
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
            if(args[0].equalsIgnoreCase("@a")){
                for(Player players : Bukkit.getOnlinePlayers()){
                    SendCommand(players.getName(), args[1]);
                }
                return true;
            }
            else {
                return SendCommand(args[0], args[1]);
            }
        }
        return false;
    }

    private boolean SendCommand(String player, String name) {
        Location end;
        if(name.equals("back")) {
            end = Bminsrelics.data.GetLocation("back."+player);
        }
        else {
            end = Bminsrelics.data.GetLocation(name);
        }
        Player otherPlayer = Bukkit.getPlayer(player);
        if(otherPlayer==null) {
            return false;
        }
        else if(end==null) {
            return false;
        }
        otherPlayer.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, otherPlayer.getLocation(), 5);
        Bminsrelics.data.AddLocation("back."+player, otherPlayer.getLocation());
        end.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, end, 1);
        otherPlayer.teleport(end);
        return true;
    }
}
