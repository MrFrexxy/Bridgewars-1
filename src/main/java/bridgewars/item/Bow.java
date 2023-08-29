package bridgewars.item;

import bridgewars.utils.ISpawnableItem;
import bridgewars.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Bow implements ISpawnableItem {
    @Override
    public Rarity getRarity() {
        return Rarity.NONE;
    }
    @Override
    public ItemStack createItem(Player p) {
        ItemStack bow = new ItemStack(Material.BOW, 1);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemBuilder.setUnbreakable(bow, true);
        String team = ItemBuilder.getTeamName(p);
        ItemBuilder.setName(bow, "&r" + team.substring(0, 1).toUpperCase() + team.substring(1) + " Bow");
        return bow;
    }
}
