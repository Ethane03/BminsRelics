package frostfire.bminsrelics.item.relic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import frostfire.bminsrelics.item.Relic;

public class SwapBow extends Relic{
    @Override
    public Relic init() {
        directory = "swapbow";
        ItemStack temp = new ItemStack(Material.BOW);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6Swapper Bow");
        List<String> lore = new ArrayList<>();
        lore.add("§3See if the grass is greener");
        lore.add("§3on the other side");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        temp.setItemMeta(meta);
        item = temp;
        return this;
    }
    @Override
    public void Activate(EntityDamageByEntityEvent event) {
            Player player = (Player)((Arrow)event.getDamager()).getShooter();
            Entity ent = event.getEntity();
            Location old = player.getLocation();
            player.teleport(ent.getLocation());
            ent.teleport(old);
            player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 20);
            ent.getWorld().spawnParticle(Particle.PORTAL, ent.getLocation(), 20);
    }
}
