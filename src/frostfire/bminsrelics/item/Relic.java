package frostfire.bminsrelics.item;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

//Super Class!
public abstract class Relic {
    public ItemStack item;
    public String directory;
    public abstract void init();
    public abstract void Activate(PlayerInteractEvent event);
}
