package frostfire.bminsrelics.item.relic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import frostfire.bminsrelics.item.Relic;

public class PokeBall extends Relic {
    @Override
    public Relic init() {
        directory = "poke";
        ItemStack temp = new ItemStack(Material.HEART_OF_THE_SEA);
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
    public void Activate(PlayerInteractEntityEvent event) {
        ItemStack i = event.getPlayer().getInventory().getItemInMainHand();
        List<String> lore = i.getItemMeta().getLore();
        EntityType type = event.getRightClicked().getType();
        if(type==EntityType.PLAYER)return;
        String e = type.toString();
        if(lore.size()!=2)return;
        ItemMeta meta = i.getItemMeta();
        lore.add(e);
        meta.setLore(lore);
        i.setItemMeta(meta);
        event.getPlayer().spawnParticle(Particle.PORTAL, event.getRightClicked().getLocation(), 100);
        event.getRightClicked().remove();
        event.getPlayer().sendMessage(e+" has been caught!");
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
