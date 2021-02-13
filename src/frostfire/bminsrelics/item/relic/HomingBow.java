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
        directory = "homingbow";
        ItemStack temp = new ItemStack(Material.BOW);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6Homing Bow");
        List<String> lore = new ArrayList<>();
        lore.add("§3Swarm your enemies with magical cubes from the end");
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
                WitherSkull s = event.getEntity().getWorld().spawn(arrow.getLocation(), WitherSkull.class);
                s.setVelocity(arrow.getVelocity());
                s.setShooter(arrow.getShooter());
                s.setVelocity(arrow.getVelocity());
                event.setProjectile(s);
            }
        }
    }
    
}
