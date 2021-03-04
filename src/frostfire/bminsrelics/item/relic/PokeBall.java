package frostfire.bminsrelics.item.relic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import frostfire.bminsrelics.item.Relic;

public class PokeBall extends Relic {
    public Player shooter;
    @Override
    public Relic init() {
        directory = "poke";
        ItemStack temp = new ItemStack(Material.SNOWBALL);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6Poke Ball");
        List<String> lore = new ArrayList<>();
        lore.add("§3Gotta Catch Them All!");
        lore.add("§8(Right click to catch, left click to send out.)");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        temp.setItemMeta(meta);
        item = temp;
        return this;
    }
    @Override
    public void Activate(ProjectileLaunchEvent event){
        event.getEntity().setCustomName("poke");
    }
    @Override
    public void Activate(ProjectileHitEvent event) {
        if(event.getHitEntity() != null) {
            ItemStack i = item;
            List<String> lore = i.getItemMeta().getLore();
            EntityType type = event.getHitEntity().getType();
            if (type == EntityType.PLAYER) return;
            String e = type.toString();
            if (lore.size() == 2) {
                lore.add(e);
            } else {
                return;
            }
            ItemMeta meta = i.getItemMeta();
            meta.setLore(lore);
            i.setItemMeta(meta);
            Player pl = (Player) event.getEntity().getShooter();
            pl.spawnParticle(Particle.PORTAL, event.getHitEntity().getLocation(), 100);
            event.getHitEntity().remove();
            event.getHitEntity().getWorld().dropItem(event.getHitEntity().getLocation(),i);
        }
        else if (event.getHitBlock() != null){
            ItemStack i = item;
            event.getHitEntity().getWorld().dropItem(event.getHitEntity().getLocation(),i);
        }
    }
    @Override
    public void Activate(PlayerInteractEvent event) {
        if(event.getAction()!=Action.LEFT_CLICK_BLOCK)return;
        ItemStack i = event.getPlayer().getInventory().getItemInMainHand();
        List<String> lore = i.getItemMeta().getLore();
        if(lore.size()==2)return;
        EntityType type = EntityType.valueOf(lore.get(2));
        lore.remove(2);
        ItemMeta meta = i.getItemMeta();
        meta.setLore(lore);
        i.setItemMeta(meta);
        event.getPlayer().sendMessage("Go "+type.toString()+"!");
        event.getPlayer().getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0, 1, 0),type);
        event.getPlayer().spawnParticle(Particle.PORTAL, event.getClickedBlock().getLocation().add(0, 1, 0), 100);
    }
}
