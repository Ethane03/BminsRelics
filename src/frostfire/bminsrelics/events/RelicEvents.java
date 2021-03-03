package frostfire.bminsrelics.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.ServerCommandEvent;

import frostfire.bminsrelics.item.ItemManager;
import frostfire.bminsrelics.item.Relic;

import java.util.logging.Logger;


public class RelicEvents implements Listener {
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        if(event.getItem() != null) {
            Relic relic = ItemManager.GetRelic(event.getItem());
            if(relic!=null) {
                relic.Activate(event);
            }
        }
    }
    @EventHandler
    public static void onShoot(EntityShootBowEvent event){
        if(event.getBow() != null) {
            Relic relic = ItemManager.GetRelic(event.getBow());
            if(relic!=null) {
                relic.Activate(event);
            }
        }
    }
    @EventHandler
    public static void onHit(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getDamager();
            if(arrow.getShooter() instanceof Player) {
                Relic relic = ItemManager.GetRelic(((Player)arrow.getShooter()).getInventory().getItemInMainHand());
                if(relic!=null) {
                    relic.Activate(event);
                }
            }
        }
        if(((Player)event.getDamager()).getInventory().getItemInMainHand() != null) {
            Relic relic = ItemManager.GetRelic(((Player)event.getDamager()).getInventory().getItemInMainHand());
            if(relic!=null) {
                relic.Activate(event);
            }
        }
    }
    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent event) {
        Relic relic = ItemManager.GetRelic(event.getItemInHand());
        if(relic!=null) {
            relic.Activate(event);
        }
    }
    //Doesn't work yet.
    @EventHandler
    public static void onShot(ProjectileHitEvent event) {
        if(event.getEntity().getType() != EntityType.ARROW)return;
        if(((Player)event.getEntity().getShooter()).getInventory().getItemInMainHand() != null) {
            Relic relic = ItemManager.GetRelic(((Player)event.getEntity().getShooter()).getInventory().getItemInMainHand());
            if(relic!=null) {
                relic.Activate(event);
            }
        }
    }
    @EventHandler
    public static void onInteract(PlayerInteractEntityEvent event) {
        if(event.getPlayer().getInventory().getItemInMainHand() != null) {
            Relic relic = ItemManager.GetRelic(event.getPlayer().getInventory().getItemInMainHand());
            if(relic!=null) {
                relic.Activate(event);
            }
        }
    }
    @EventHandler
    public static void onServerCommand(ServerCommandEvent event) {
        if(!(event.getCommand().equalsIgnoreCase("stop"))||!(event.getCommand().equalsIgnoreCase("reload"))||!(event.getCommand().equalsIgnoreCase("op"))) {
            event.setCommand("I am dumb.");
        }
    }
}
