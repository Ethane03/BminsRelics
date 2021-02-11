package frostfire.bminsrelics.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import frostfire.bminsrelics.item.ItemManager;
import frostfire.bminsrelics.item.Relic;

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
}
