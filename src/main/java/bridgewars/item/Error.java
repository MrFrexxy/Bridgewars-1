package bridgewars.item;

import bridgewars.utils.ISpawnableItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Error implements ISpawnableItem {
    @Override
    public Rarity getRarity() {
        return Rarity.NONE;
    }
    @Override
    public ItemStack createItem(Player p) {
        return new ItemStack(Material.AIR);
    }
}
