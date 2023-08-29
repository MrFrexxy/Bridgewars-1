package bridgewars.item;

import bridgewars.utils.ISpawnableItem;
import bridgewars.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GigaShears implements ISpawnableItem {
    @Override
    public Rarity getRarity() {
        return Rarity.NONE;
    }
    @Override
    public ItemStack createItem(Player p) {
        ItemStack gigaShears = new ItemStack(Material.SHEARS, 1);
        gigaShears.addEnchantment(Enchantment.DIG_SPEED, 5);
        ItemBuilder.setUnbreakable(gigaShears, true);
        return gigaShears;
    }
}
