package frostfire.bminsrelics.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RandomWand extends Relic {

    @Override
    public void init() {
        directory = "randwand";
        ItemStack temp = new ItemStack(Material.STICK, 1);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("&6Lucky Wand");
        List<String> lore = new ArrayList<>();
        lore.add("&3Even Bmin doesn't know what this will do.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        temp.setItemMeta(meta);
        item = temp;
    }
    @Override
    public void Activate(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Random random = new Random();
        //int rand = random. 
        player.getWorld().createExplosion(player.getLocation(), 5);
    }
    
}
