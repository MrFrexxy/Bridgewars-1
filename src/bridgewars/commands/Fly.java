package bridgewars.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import bridgewars.Main;
import bridgewars.game.GameState;
import bridgewars.utils.Utils;

public class Fly implements CommandExecutor {
	
	public static List<Player> allowFlight = new ArrayList<>();

	public Fly(Main plugin) {
		plugin.getCommand("fly").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		
		if(GameState.isState(GameState.ACTIVE)
		&& !Utils.isOutOfBounds(p.getLocation(), 200, 40, 200)) {
			p.sendMessage(Utils.chat("&cYou can't fly while in a game!"));
		}
		else {
			if(allowFlight.contains(p)) {
				p.setAllowFlight(false);
				p.setFlying(false);
				p.sendMessage(Utils.chat("&6Turned off flight"));
				allowFlight.remove(p);
			}
			else {
				p.setAllowFlight(true);
				p.sendMessage(Utils.chat("&6Turned on flight"));
				allowFlight.add(p);
			}
		}
		
		return false;
	}
}
