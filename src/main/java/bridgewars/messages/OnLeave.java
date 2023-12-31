package bridgewars.messages;

import bridgewars.items.SadRoom;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import bridgewars.Main;
import bridgewars.game.CustomScoreboard;
import bridgewars.game.GameState;

public class OnLeave implements Listener {
	
	private final CustomScoreboard cs;
	
	public OnLeave(Main plugin) {
		Bukkit.getPluginManager().registerEvents(this, plugin);
		cs = new CustomScoreboard();
	}
	
	@EventHandler
	public void onJoin(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		cs.resetTeam(p, false);
		cs.removePlayerFromTimer(p);
		SadRoom.removePlayerFromSadRoom(p);
		if(Bukkit.getOnlinePlayers().isEmpty())
			if(GameState.isState(GameState.EDIT))
				GameState.setState(GameState.INACTIVE);
	}
}
