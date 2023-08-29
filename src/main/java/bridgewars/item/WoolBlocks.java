package bridgewars.item;

import bridgewars.utils.ISpawnableItem;
import bridgewars.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WoolBlocks implements ISpawnableItem {
    @Override
    public Rarity getRarity() {
        return Rarity.NONE;
    }
    @Override
    public ItemStack createItem(Player p) {
        ItemStack item = new ItemStack(Material.WOOL, 64);
        switch(ItemBuilder.getTeamName(p)) {
            case "red":
                item.setDurability((short) 14);
                break;
            case "blue":
                item.setDurability((short) 3);
                break;
            case "green":
                item.setDurability((short) 5);
                break;
            case "yellow":
                item.setDurability((short) 4);
                break;
        }
        return item;
    }
}
