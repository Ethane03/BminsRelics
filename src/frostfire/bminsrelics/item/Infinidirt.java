package frostfire.bminsrelics.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Infinidirt extends Relic {

    @Override
    public Relic init() {
        directory = "randwand";
        ItemStack temp = new ItemStack(Material.STICK);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6Infinidirt");
        List<String> lore = new ArrayList<>();
        lore.add("§3All the dirt you could ever want");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        temp.setItemMeta(meta);
        item = temp;
        return this;
    }
    @Override
    public void Activate(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Random random = new Random();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        //Change for how many switch cases you have.
        event.getItem().setAmount(1);
    }
    }
    
}
