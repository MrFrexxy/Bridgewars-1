package bridgewars.utils;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

public class Utils {
	
	public static int rand(int range) {
		Random rng = new Random();
		return rng.nextInt(range);
	}
	
	public static boolean isOutOfBounds(Location loc, int x, int y, int z) {
        return Math.abs(loc.getBlockX()) > x || loc.getBlockY() > y || Math.abs(loc.getBlockZ()) > z;
	}
	
	public static boolean isOutOfBounds(Location loc, int x, int y, int z, int a, int b, int c) {
        return Math.abs(loc.getBlockX()) > x || loc.getBlockY() > y || Math.abs(loc.getBlockZ()) > z;
	}
	public static boolean compareItemName(ItemStack item, String name){
		if(item == null)
			return false;
		if(!item.hasItemMeta())
			return false;
		if(!item.getItemMeta().hasDisplayName())
			return false;

        return item.getItemMeta().getDisplayName().equals(name);
    }
}
