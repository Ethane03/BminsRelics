package frostfire.bminsrelics.item.relic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import frostfire.bminsrelics.Bminsrelics;
import frostfire.bminsrelics.commands.RelicCommands;
import frostfire.bminsrelics.item.Relic;

public class Bonk extends Relic {
    @Override
    public Relic init() {
        directory = "bonk";
        ItemStack temp = new ItemStack(Material.STICK);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6Bonk");
        List<String> lore = new ArrayList<>();
        lore.add("§3Send the horny to their rightful place.");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        temp.setItemMeta(meta);
        item = temp;
        return this;
    }
    @Override
    public void Activate(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player) {
            Player victim = (Player) event.getEntity();
            Bukkit.getServer().broadcastMessage(victim.getName()+" has been sent to horny jail.");
            RelicCommands.SendCommand(victim.getName(),"horny");
        }
    }
}
