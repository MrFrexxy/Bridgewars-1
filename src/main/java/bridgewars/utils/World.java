package bridgewars.utils;

import bridgewars.item.ItemPoolManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class World {
	
	public static void fill(Location origin, int x, int y, int z) {
	}
	
	public static void attemptItemSpawn(int chance, boolean showParticles) {
		boolean validLocation = false;
		boolean success = false;
		
		int attempts = 0;
		int x = 0, y = 0, z = 0;
		
		 if(Utils.rand(chance) + 1 == chance)
			 success = true;
		
		while(!validLocation && success) {
			x = Utils.rand(45) - 22;
			z = Utils.rand(45) - 22;
			
			for(y = 0; y < 24; y++)
				if(Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), x, y, z)).getType() != Material.AIR
				&& Bukkit.getWorld("world").getBlockAt(new Location(Bukkit.getWorld("world"), x, y + 1, z)).getType() == Material.AIR) {
					validLocation = true;
					break;
				}
			attempts++;
			if(attempts == 300)
				break;
		}
		
		if(validLocation) {
			Item item;
			int r = -255, g = -255, b = -255;
			ISpawnableItem spawnableItem = ItemPoolManager.getRandomItem();
			switch(spawnableItem.getRarity()){
				case WHITE:
					r = 255;
					g = 255;
					b = 255;
					break;
				case GREEN:
					g = 255;
					break;
				case RED:
					r = 255;
					g = 0;
					b = 0;
					break;
				case BLUE:
					r = 0;
					g = 0;
					b = 255;
			}
			item = Bukkit.getWorld("world").dropItem(new Location(Bukkit.getWorld("world"), x + 0.5, y + 1, z + 0.5),spawnableItem.createItem(null));
			item.setVelocity(item.getVelocity().setX(0).setY(0).setZ(0));
			if(showParticles)
				new Particle(item, EnumParticle.REDSTONE, 0, 1, 0, r, g, b, 1F, 0, 3000, false).runTaskTimer(Bukkit.getPluginManager().getPlugin("bridgewars"), 0L, 1L);
		}
	}
}
