package frostfire.bminsrelics.item;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    public void Activate(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Random random = new Random();
        if(event.getAction() == Action.PHYSICAL) {
            event.getItem().setAmount(0);
        }
    }
    
}
