package frostfire.bminsrelics.item;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

//Super Class!
public abstract class Relic{
    public ItemStack item;
    public String directory;
    public Relic init() {
        return null;
    }
    public void Activate(PlayerInteractEvent event){};
    public void Activate(EntityShootBowEvent event){};
    public void Activate(EntityDamageByEntityEvent event){};
    public void Activate(BlockPlaceEvent event){};
    public void Activate(ProjectileHitEvent event){};
    public void Activate(PlayerInteractEntityEvent event){};
}
