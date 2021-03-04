package frostfire.bminsrelics.item.relic;

import frostfire.bminsrelics.item.Relic;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TrickSword extends Relic {

    @Override
    public Relic init() {
        directory = "tricksword";
        ItemStack temp = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6God's toothpick");
        List<String> lore = new ArrayList<>();
        lore.add("§3Epicly powerful");
        lore.add("§3Use it wisely");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.KNOCKBACK, 100, false);
        AttributeModifier mod = new AttributeModifier(UUID.randomUUID(),"generic.attack_damage",10000.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,mod);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        temp.setItemMeta(meta);
        item = temp;
        return this;
    }
    @Override
    public void Activate(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            player.getInventory().remove(((Player) event.getDamager()).getInventory().getItemInMainHand());
        }
    }
    
}
