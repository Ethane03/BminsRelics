package frostfire.bminsrelics.item;

import frostfire.bminsrelics.item.relic.*;
import org.bukkit.inventory.ItemStack;

public class ItemManager {
    public static Relic[] relics = {new RandomWand().init(), new Infinidirt().init(),new LightningHammer().init(),new TrickSword().init(),new HomingBow().init(),new Bonk().init()
    ,new SwapBow().init(),new Key().init(),new OldPoke().init(),new PokeBall().init()};
    public static Relic GetRelic(String register) {
        for (Relic relic : relics) {
            if(relic.directory.equals(register)) {
                return relic;
            }
        }
        return null;
    }
    public static Relic GetRelic(ItemStack itemStack) {
        for (Relic relic : relics) {
            if(relic.item.getItemMeta().getDisplayName().equals(itemStack.getItemMeta().getDisplayName())) {
                return relic;
            }
        }
        return null;
    }
}
