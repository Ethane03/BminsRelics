package frostfire.bminsrelics.item.relic;

import frostfire.bminsrelics.item.Relic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getLogger;

public class HomingBow extends Relic {

    @Override
    public Relic init() {
        directory = "skullbow";
        ItemStack temp = new ItemStack(Material.BOW);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6Skull Bow");
        List<String> lore = new ArrayList<>();
        lore.add("§3Feel the Wither");
        lore.add("§3Be the Wither");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        temp.setItemMeta(meta);
        item = temp;
        return this;
    }
    @Override
    public void Activate(EntityShootBowEvent event) {
        getLogger().info("well something worked");
        if(event.getProjectile() instanceof Arrow && event.getEntity() instanceof Player) {
            Arrow arrow = (Arrow) event.getProjectile();
            if ((event.getEntity() instanceof Player)) {
                ShulkerBullet s = event.getEntity().getWorld().spawn(arrow.getLocation(), ShulkerBullet.class);
                s.setVelocity(arrow.getVelocity());
                s.setShooter(arrow.getShooter());
                event.setProjectile(s);
            }
        }
    }
    
}
