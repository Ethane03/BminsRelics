package frostfire.bminsrelics.item;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    public static ItemStack wand;

    public static void init(){
        CreateWand();
    }
    public static void CreateWand(){
        ItemStack item = new ItemStack(Material.STICK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Pogwand");
        List<String> lore = new ArrayList<>();
        lore.add("§3This is the oldest weapon known to minecraft");
        lore.add("§5It is super pog");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.KNOCKBACK,10,false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        wand = item;
    }
}
