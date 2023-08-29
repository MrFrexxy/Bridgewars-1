package bridgewars.item;

import bridgewars.utils.ISpawnableItem;
import bridgewars.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Shears implements ISpawnableItem {
    @Override
    public Rarity getRarity() {
        return Rarity.NONE;
    }
    @Override
    public ItemStack createItem(Player p) {
        ItemStack shears = new ItemStack(Material.SHEARS, 1);
        shears.addEnchantment(Enchantment.DIG_SPEED, 3);
        ItemBuilder.setUnbreakable(shears, true);
        return shears;
    }
}
