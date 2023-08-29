package bridgewars.items;

import bridgewars.Main;
import bridgewars.utils.ItemBuilder;
import bridgewars.utils.Message;
import bridgewars.utils.ICustomItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.Arrays;

public class BlackHole implements ICustomItem, Listener {
    public BlackHole(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onThrow(ProjectileLaunchEvent e) {
        if(e.getEntity() instanceof Snowball) {
            if(!(e.getEntity().getShooter() instanceof Player))
                return;
            Vector v = e.getEntity().getVelocity();
            v.multiply(1.5);
            e.getEntity().setVelocity(v);

            new bridgewars.effects.BlackHole(e.getEntity(), 1).runTaskTimer(Bukkit.getPluginManager().getPlugin("bridgewars"), 0L, 0L);
        }
    }
    @Override
    public Rarity getRarity() {
        return Rarity.RED;
    }

    @Override
    public ItemStack createItem(Player p) {
        ItemStack out = new ItemStack(Material.SNOW_BALL, 1);
        ItemBuilder.setName(out, "&cBlack Hole");
        ItemBuilder.setLore(out, Arrays.asList(Message.chat("&r&7A throwable black hole that eats"),
                Message.chat("&r&7every block in its path")));
        return out;
    }
}
