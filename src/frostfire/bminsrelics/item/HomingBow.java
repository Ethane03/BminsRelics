package frostfire.bminsrelics.item;

import org.apache.logging.log4j.core.config.ConfiguratonFileWatcher;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HomingBow extends Relic<EntityShootBowEvent> {

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
    //@Override
    public void Activate(EntityShootBowEvent event) {
        if(event.getProjectile() instanceof Arrow && event.getEntity() instanceof Player) {
            Player player = (Player)event.getEntity();
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
