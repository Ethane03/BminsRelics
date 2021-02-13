package frostfire.bminsrelics.item.relic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import frostfire.bminsrelics.item.Relic;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.SpectralArrow;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class RandomWand extends Relic{

    @Override
    public Relic init() {
        directory = "randwand";
        ItemStack temp = new ItemStack(Material.STICK);
        ItemMeta meta = temp.getItemMeta();
        meta.setDisplayName("§6Lucky Wand");
        List<String> lore = new ArrayList<>();
        lore.add("§3Even Bmin doesn't know what this will do.");
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
        if(event.getAction() == Action.RIGHT_CLICK_AIR||event.getAction() == Action.RIGHT_CLICK_BLOCK) {
        //Change for how many switch cases you have.
        int effects = 6;
        int rand = random.nextInt(effects-1); 
        switch(rand) {
            case 0:
                player.launchProjectile(Snowball.class,player.getEyeLocation().getDirection().multiply(3));
                break;
            case 1:
                player.launchProjectile(EnderPearl.class,player.getEyeLocation().getDirection().multiply(3));
                break;
            case 2:
                player.launchProjectile(SpectralArrow.class,player.getEyeLocation().getDirection().multiply(3));
                break;
            case 3:
                EntityType[] entities = new EntityType[]{EntityType.BAT,EntityType.BEE,EntityType.COW,EntityType.SHEEP,EntityType.WOLF,EntityType.CHICKEN};
                player.getWorld().spawnEntity(player.getLocation(), entities[random.nextInt(entities.length)]);
            case 4:
                EntityType[] baddies = new EntityType[]{EntityType.ZOMBIE,EntityType.SKELETON,EntityType.CREEPER,EntityType.SPIDER,EntityType.WITCH};
                player.getWorld().spawnEntity(player.getLocation(), baddies[random.nextInt(baddies.length)]);
                break;
            case 5:
                PotionEffectType[] potions = new PotionEffectType[] {PotionEffectType.ABSORPTION,PotionEffectType.BLINDNESS,PotionEffectType.DAMAGE_RESISTANCE,PotionEffectType.FAST_DIGGING,PotionEffectType.FIRE_RESISTANCE,PotionEffectType.GLOWING,PotionEffectType.HARM,PotionEffectType.HEAL,PotionEffectType.HUNGER,PotionEffectType.INVISIBILITY,PotionEffectType.LEVITATION};
                player.addPotionEffect(new PotionEffect(potions[random.nextInt(potions.length)],random.nextInt(100),random.nextInt(100)));
                break;
            default:
                player.sendMessage("Tell Bmin something went wrong with his Random Wand Relic.");
                break;
        }
    }
    }
    
}
