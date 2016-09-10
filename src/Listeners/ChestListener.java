package Listeners;

import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Wool;

import Managers.chestManager;
import PlayerWarps.PlayerWarps;

public class ChestListener implements Listener {

	private final PlayerWarps plugin;

	public ChestListener(PlayerWarps plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}

	@SuppressWarnings("resource")
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onInventoryClick(InventoryClickEvent e) {

		if (e.getWhoClicked() instanceof Player) {
			if (e.getInventory().getName().contains("PlayerWarps")) {
				

				Wool wool = new Wool(DyeColor.LIME);
				ItemStack NextPage = wool.toItemStack(1);
				

				Wool wool2 = new Wool(DyeColor.LIGHT_BLUE);
				ItemStack PlayerWarp = wool.toItemStack(1);
				
				e.setCancelled(true);
				
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Next")) {

					Scanner in = new Scanner(e.getCurrentItem().getItemMeta().getDisplayName()).useDelimiter("[^0-9]+");
					int nextPageNum = in.nextInt();
					// PlayerWarps.chestman.openGUI((Player) e.getWhoClicked(), 2);
					Bukkit.broadcastMessage("Clicked next page: "+ nextPageNum);
					
					Player p = (Player) e.getWhoClicked();
					e.getWhoClicked().closeInventory();
					chestManager.openGUI(p, nextPageNum);
					return;
				}

				e.getWhoClicked().closeInventory();
			}
			// e.getCurrentItem().getItemMeta();
			// 

		}

	}

}
