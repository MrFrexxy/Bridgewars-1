package bridgewars.commands;

import bridgewars.item.ItemPoolManager;
import bridgewars.utils.ISpawnableItem;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import bridgewars.Main;
import bridgewars.utils.Message;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GiveItem implements CommandExecutor, TabCompleter {

	private ArrayList<String> allItems;
	
	public GiveItem(Main plugin) {
		plugin.getCommand("giveitem").setExecutor(this);
		allItems = ItemPoolManager.getItemNames();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		
		Player p = (Player) sender;
		
		if(p.isOp()) {
			
			if(args.length == 0)
				return false;
			
			int amount;
			try {
				amount = Integer.parseInt(args[1]);
			}
			catch (Exception e) { amount = 1; }

			for(int i = 0; i < allItems.size(); i++){
				if(args[0].equals(allItems.get(i))){
					ItemStack itemStack = ItemPoolManager.getItem(i).createItem(p);
					itemStack.setAmount(amount);
					p.getInventory().addItem(itemStack);
					p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 0.6F, 1.8F);
					return false;
				}
			}
			p.sendMessage(Message.chat("&cThis item does not exist!"));
		}
		
		else
			p.sendMessage(Message.chat("&cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error."));
		
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(args.length == 1){
			return allItems;
		}
		return null;
	}
}
