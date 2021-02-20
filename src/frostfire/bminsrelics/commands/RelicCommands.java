package frostfire.bminsrelics.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.data.type.Bed.Part;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import frostfire.bminsrelics.Bminsrelics;
import frostfire.bminsrelics.item.ItemManager;
import frostfire.bminsrelics.item.Relic;
import net.minecraft.server.v1_16_R3.ParticleType;

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
            Bminsrelics.data.AddLocation(args[0].toLowerCase(), player.getLocation());
            player.sendMessage("Checkpoint Created!");
            return true;
        }
        else if(cmd.getName().equalsIgnoreCase("send")) {
            if(args[0].equalsIgnoreCase("@a")){
                for(Player players : Bukkit.getOnlinePlayers()){
                    if(!players.equals(player)){
                        SendCommand(players.getName(), args[1],randomParticle());
                    }
                    if((args.length<=2||args[2].equalsIgnoreCase("true"))&&!args[1].equalsIgnoreCase("back")) {
                        Bminsrelics.data.AddLocation("back."+players.getName(), players.getLocation());    
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
        otherPlayer.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, otherPlayer.getLocation(), 5);
        Bminsrelics.data.AddLocation("back."+from, otherPlayer.getLocation());
        end.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, end, 1);
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
        otherPlayer.getWorld().spawnParticle(particle, otherPlayer.getLocation(), 5);
        end.getWorld().spawnParticle(particle, end, 5);
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
