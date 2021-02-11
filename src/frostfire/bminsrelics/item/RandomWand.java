package frostfire.bminsrelics.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class RandomWand extends Relic {

    @Override
    public Relic init() {
        directory = "randwand";
        ItemStack temp = new ItemStack(Material.WOODEN_SWORD);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("&6Lucky Wand");
        List<String> lore = new ArrayList<>();
        lore.add("&3Even Bmin doesn't know what this will do.");
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
        //Change for how many switch cases you have.
        int effects = 3;
        int rand = random.nextInt(effects-1); 
        switch(rand) {
            case 0:
                player.launchProjectile(Snowball.class,player.getEyeLocation().getDirection().multiply(3));
                break;
            case 1:
                player.launchProjectile(Fireball.class,player.getEyeLocation().getDirection().multiply(3));
                break;
            case 2:
                player.launchProjectile(SpectralArrow.class,player.getEyeLocation().getDirection().multiply(3));
                break;
            case 3:
                EntityType[] entities = new EntityType[]{EntityType.BAT,EntityType.BEE,EntityType.COW,EntityType.SHEEP,EntityType.WOLF,EntityType.CHICKEN};
                player.getWorld().spawnEntity(player.getLocation(), entities[random.nextInt(entities.length)]);
            default:
                player.sendMessage("Tell Bmin something went wrong with his Random Wand Relic.");
                break;
        }
    }
    
}
