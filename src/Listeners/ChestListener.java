package Listeners;

import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;
import org.bukkit.plugin.Plugin;

import Managers.chestManager;
import PlayerWarps.PlayerWarps;

public class ChestListener implements Listener {

	private final PlayerWarps plugin;

	public ChestListener(PlayerWarps plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	@SuppressWarnings("resource")
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onInventoryClick(final InventoryClickEvent e) {

		// was it a player
		if (e.getWhoClicked() instanceof Player) {

			// does it match the right inventory name
			if (e.getInventory().getName().contains("PlayerWarps")) {

				// sert player
				final Player p = (Player) e.getWhoClicked();

				// cancel event, prevent player from removing the item
				e.setCancelled(true);

				// was it a next page icon clicked
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Next")) {

					// get next page number
					Scanner in = new Scanner(e.getCurrentItem().getItemMeta().getDisplayName()).useDelimiter("[^0-9]+");
					int nextPageNum = in.nextInt();
					
					//close inventory
					e.getWhoClicked().closeInventory();
					
					//open new inventory from next page
					chestManager.openGUI(p, nextPageNum);
					
					// exit 
					return;
				}

				//close inventory
				e.getWhoClicked().closeInventory();
				
				//send message to player
				p.sendMessage(ChatColor.GREEN + "Warping to :" + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.GREEN + " in 3 seconds");

				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(PlayerWarps.getInstance(), new Runnable() {
					@Override
					public void run() {
						p.sendMessage("Done");
					}
				}, 60);

			}
			// e.getCurrentItem().getItemMeta();
			//

		}

	}

}
