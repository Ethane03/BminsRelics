package frostfire.bminsrelics.item.relic;

import java.util.ArrayList;
import java.util.List;

import frostfire.bminsrelics.item.Relic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LightningHammer extends Relic {
    @Override
    public Relic init() {
        directory = "lightning";
        ItemStack temp = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6Hammer of Zeus");
        List<String> lore = new ArrayList<>();
        lore.add("§3Smite your enemies.");
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
        if(event.getAction() == Action.LEFT_CLICK_AIR) {
            player.getWorld().strikeLightning(player.getTargetBlock(null, 100).getLocation());
        }
    }
}
