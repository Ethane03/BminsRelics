package frostfire.bminsrelics.item;

import org.bukkit.GameMode;
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


public class Infinidirt extends Relic {

    @Override
    public Relic init() {
        directory = "infinidirt";
        ItemStack temp = new ItemStack(Material.DIRT);
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
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK&&player.getGameMode()!=GameMode.ADVENTURE) {
            player.getInventory().addItem(item);
        }
    }
}
}
