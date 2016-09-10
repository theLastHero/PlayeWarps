package Listeners;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

import me.ryanhamshire.GriefPrevention.ClaimsMode;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import Managers.HashMapManager;
import PlayerWarps.PlayerWarps;
import Utils.SLAPI;

public class CommandListener implements CommandExecutor {
	private final PlayerWarps plugin;

	public CommandListener(PlayerWarps plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;
		}

		Player player = (Player) sender;

		if (cmd.getName().equalsIgnoreCase("playerwarps")) {

			if ((args.length < 1) || (args[0].equalsIgnoreCase("list"))) {
				// do chest open gui here
				player.sendMessage(ChatColor.GOLD + "GUI OPENS HERE");
				PlayerWarps.chestman.openGUI(player, 0);
				return true;
			}

			if ((args.length == 1) && (args[0].equalsIgnoreCase("set"))) {
				if (!PlayerWarps.perms.has("world", player.getName(), "PlayerWarps.set")) {
					player.sendMessage(ChatColor.GREEN + "You do not have the permission to set a /pwarp" );
					return true;
				}
				// do chest open gui here
				//if (player.getWorld().equals(Bukkit.getWorld("world"))) {
				//	player.sendMessage(ChatColor.GREEN + "You can't set a warp in this world");
				//	return true;
				//}
				if (PlayerWarps.hashMan.checkHashMap(player)) {
					player.sendMessage(ChatColor.GREEN + "You already have a pwarp set. You must delete " + ChatColor.RED + "[/pwarps delete]" + ChatColor.GREEN + " before setting a new warp location");
					return true;
				}

				me.ryanhamshire.GriefPrevention.Claim isClaim = PlayerWarps.gp.dataStore.getClaimAt(player.getLocation(), false, null);
				if ((isClaim == null) || !(isClaim.getOwnerName().equalsIgnoreCase(player.getName()))) {
					
					player.sendMessage(ChatColor.GREEN + "You can only set warps inside your own claim");
					return true;
					
				}

				player.sendMessage(ChatColor.GOLD + "Set warp...");
				PlayerWarps.hashMan.putIntoHashMap(player, player.getLocation());
				PlayerWarps.hashMan.saveHashMap();
				return true;
			}

			if ((args.length == 1) && (args[0].equalsIgnoreCase("delete"))) {
				if (!PlayerWarps.hashMan.checkHashMap(player)) {
					player.sendMessage(ChatColor.GREEN + "You dont have a pwarp to delete");
					return true;
				}
				player.sendMessage(ChatColor.GOLD + "deleted warp...");
				PlayerWarps.hashMan.removeFromHashMap(player);
				PlayerWarps.hashMan.saveHashMap();
				return true;
			}

		}

		// do something
		return false;

	}
}