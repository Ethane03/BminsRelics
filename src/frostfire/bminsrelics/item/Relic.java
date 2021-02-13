package frostfire.bminsrelics.item;

import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

//Super Class!
public abstract class Relic {
    public ItemStack item;
    public String directory;
    public Relic init() {
        return null;
    }
    public void Activate(PlayerInteractEvent event){};
}
