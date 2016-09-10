package Listeners;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import PlayerWarps.PlayerWarps;
import Utils.SLAPI;

public class CommandListener implements CommandExecutor {
	private final PlayerWarps plugin;

	public CommandListener(PlayerWarps plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	
	@SuppressWarnings("static-access")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
	           return false;
	        }
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("playerwarps")){
		
			if((args.length < 1) || (args[0].equalsIgnoreCase("list"))){
				//do chest open gui here
				player.sendMessage(ChatColor.GOLD+"GUI OPENS HERE");
				PlayerWarps.chestman.openGUI(player, 0);
			}
			
			
				
		}
		
	
	        // do something
	        return false;
		
	}
}