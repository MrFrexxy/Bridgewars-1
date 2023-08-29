package bridgewars.item;

import bridgewars.utils.ISpawnableItem;
import bridgewars.utils.ItemBuilder;
import bridgewars.utils.Message;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class LifeforcePotion implements ISpawnableItem {
    @Override
    public Rarity getRarity() {
        return Rarity.RED;
    }

    @Override
    public ItemStack createItem(Player p) {
        ItemStack lifeforcePotion = new ItemStack(Material.POTION, 1);
        lifeforcePotion.setDurability((short) 8233);
        ItemMeta meta = lifeforcePotion.getItemMeta();
        PotionMeta effect = (PotionMeta) meta;
        effect.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0), true);
        effect.addCustomEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 4), true);
        meta = effect;
        lifeforcePotion.setItemMeta(meta);
        lifeforcePotion.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemBuilder.setName(lifeforcePotion, "&cLifeforce Potion");
        ItemBuilder.setLore(lifeforcePotion, Arrays.asList(Message.chat("&r&7Strength I (0:30)"),
                Message.chat("&r&7Absorption V (1:00)")));
        return lifeforcePotion;
    }
}
