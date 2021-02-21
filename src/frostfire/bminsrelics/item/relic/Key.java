package frostfire.bminsrelics.item.relic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import frostfire.bminsrelics.item.Relic;

public class Key extends Relic {
    @Override
    public Relic init() {
        directory = "key";
        ItemStack temp = new ItemStack(Material.BEDROCK);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6Key");
        List<String> lore = new ArrayList<>();
        lore.add("§3Collect 20 and you could unlock");
        lore.add("§3UNLIMITED POWER!");
        lore.add("§8(Don't place. Bad juju.)");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        temp.setItemMeta(meta);
        item = temp;
        return this;
    }
}
