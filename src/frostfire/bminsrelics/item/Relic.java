package frostfire.bminsrelics.item;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

//Super Class!
public abstract class Relic<T extends Event> {
    public ItemStack item;
    public String directory;
    public Relic init() {
        return null;
    }
    public void Activate(T event){};
}
