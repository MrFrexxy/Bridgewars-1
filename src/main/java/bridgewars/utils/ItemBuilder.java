package bridgewars.utils;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import bridgewars.game.CustomScoreboard;

public class ItemBuilder {

	private static CustomScoreboard cs = new CustomScoreboard();
	
	public static ItemStack hideFlags(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack setName(ItemStack item, String name) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Message.chat(name));
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack setLore(ItemStack item, List<String> lore) {
		ItemMeta meta = item.getItemMeta();
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack setUnbreakable(ItemStack item, boolean state) {
		ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(state);
		item.setItemMeta(meta);
		item.setDurability((short)0);
		return item;
	}
	
	public static void setLeatherColor(Player p, ItemStack item, String s) {
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(getColor(cs.getTeam(p)));
		item.setItemMeta(meta);
	}
	
	private static Color getColor(String s) {
		switch(s) {
		case "red":
			return Color.fromRGB(255, 0, 0);
		case "blue":
			return Color.fromRGB(0, 255, 255);
		case "green":
			return Color.fromRGB(0, 255, 0);
		case "yellow":
			return Color.fromRGB(255, 255, 0);
		}
		return Color.fromRGB(255, 255, 255);
	}
	public static String getTeamName(Player p){
		return cs.getTeam(p);
	}
}
