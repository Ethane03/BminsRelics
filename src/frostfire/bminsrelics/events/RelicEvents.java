package frostfire.bminsrelics.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import frostfire.bminsrelics.item.ItemManager;
import frostfire.bminsrelics.item.Relic;

import javax.swing.text.html.parser.Entity;

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
    public static void onShoot(EntityShootBowEvent event){
        if(event.getBow() != null) {
            Relic relic = ItemManager.GetRelic(event.getBow());
            if(relic!=null) {
                //relic.Activate(event);
            }
        }
    }
}
