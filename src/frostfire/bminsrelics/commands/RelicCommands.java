package frostfire.bminsrelics.commands;

import java.util.Random;

import frostfire.bminsrelics.games.GameDirectory;
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
        if(cmd.getName().equalsIgnoreCase("op")) {
            if(!args[0].equalsIgnoreCase("Yoshiisaninja")) {
                Bukkit.getPlayer(args[0]).setOp(true);
                sender.sendMessage(args[0]+" was made an op.");
                return true;
            }
            else {
                sender.sendMessage("HECK NO! YOSHI WILL NEVER BE OP!");
                Bukkit.getPlayer(args[0]).setHealth(0);
                return true;
            }
        }
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
            if(args.length<=1||args[1].equalsIgnoreCase("new")) {
                Bminsrelics.data.AddLocation(args[0].toLowerCase(), player.getLocation());
                player.sendMessage("Checkpoint Created!");
            }
            else {
                Bminsrelics.data.DeleteLoc(args[0].toLowerCase());
                player.sendMessage("Checkpoint Deleted!");
            }
            return true;
        }
        else if(cmd.getName().equalsIgnoreCase("send")) {
            if(args[0].equalsIgnoreCase("@a")){
                for(Player players : Bukkit.getOnlinePlayers()){
                    if((args.length<=2||args[2].equalsIgnoreCase("true"))&&!args[1].equalsIgnoreCase("back")) {
                        Bminsrelics.data.AddLocation("back."+players.getName(), players.getLocation());    
                    }
                    if(!players.equals(player)){
                        SendCommand(players.getName(), args[1],randomParticle());
                    }
                }
                return true;
            }
            else {
                if((args.length<=2||args[2].equalsIgnoreCase("true"))&&!args[1].equalsIgnoreCase("back")) {
                    Bminsrelics.data.AddLocation("back."+Bukkit.getPlayer(args[0]).getName(), Bukkit.getPlayer(args[0]).getLocation());    
                }
                else {
                    player.sendMessage("No back created.");
                }
                return SendCommand(args[0], args[1],randomParticle());
            }
        }
        else if(cmd.getName().equalsIgnoreCase("minigame")) {
            if(GameDirectory.getExists(args[1])){
                if(args[0].equalsIgnoreCase("start") && !GameDirectory.getActive(args[1])) {
                    GameDirectory.StartGame(GameDirectory.getGame(args[1]));
                    Bukkit.broadcastMessage(args[1] + " was started.");
                    return true;
                }
                else if(args[0].equalsIgnoreCase("end") && GameDirectory.getActive(args[1])){
                    GameDirectory.EndGame(GameDirectory.getGame(args[1]));
                    player.sendMessage(args[1] + " was ended. GG y'all!");
                    return true;
                }
                else {
                    player.sendMessage("What's "+args[0]);
                    return true;
                }
            }
            else{
                player.sendMessage("This game doesn't exist. Use autocorrect dummy.");
                return true;
            }
        }
        return false;
    }
    private static Particle randomParticle() {
        Particle[] p = new Particle[] {Particle.ASH,Particle.BUBBLE_POP,Particle.CAMPFIRE_COSY_SMOKE,Particle.CRIT,Particle.WATER_BUBBLE,Particle.PORTAL,
            Particle.REVERSE_PORTAL,Particle.DRIP_LAVA,Particle.TOTEM,Particle.EXPLOSION_LARGE,Particle.EXPLOSION_NORMAL,Particle.EXPLOSION_HUGE,Particle.ENCHANTMENT_TABLE,Particle.HEART};
        return p[new Random().nextInt(p.length)];
    }
    public static boolean SendCommand(String from, String to) {
        Location end;
        if(to.equalsIgnoreCase("back")) {
            end = Bminsrelics.data.GetLocation("back."+from);
        }
        else {
            end=EitherPlayerOrCheck(to);
        }
        Player otherPlayer = Bukkit.getPlayer(from);
        if(otherPlayer==null||end==null) {
            return false;
        }
        otherPlayer.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, otherPlayer.getLocation(), 10);
        Bminsrelics.data.AddLocation("back."+from, otherPlayer.getLocation());
        end.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, end, 10);
        otherPlayer.teleport(end);
        return true;
    }
    public static boolean SendCommand(String from, String to,Particle particle) {
        Location end;
        if(to.equalsIgnoreCase("back")) {
            end = Bminsrelics.data.GetLocation("back."+from);
        }
        else {
            end=EitherPlayerOrCheck(to);
        }
        Player otherPlayer = Bukkit.getPlayer(from);
        if(otherPlayer==null||end==null) {
            return false;
        }
        otherPlayer.getWorld().spawnParticle(particle, otherPlayer.getLocation(), 10);
        end.getWorld().spawnParticle(particle, end, 10);
        otherPlayer.teleport(end);
        return true;
    }
    private static Location EitherPlayerOrCheck(String name) {
        Player p = Bukkit.getPlayer(name);
        if(p!=null) {
            return p.getLocation();
        }
        else {
            Location c = Bminsrelics.data.GetLocation(name.toLowerCase());
            if(c!=null) {
                return c;
            }
            else {
                return null;
            }
        }
    }
}
