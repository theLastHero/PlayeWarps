package Managers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

import PlayerWarps.PlayerWarps;

public class chestManager {

	static PlayerWarps plugin;

	public chestManager(PlayerWarps PlayerWarps) {
		plugin = PlayerWarps;
	}

	private static chestManager cm = new chestManager(plugin);

	// ------------------------------------------------------
	// getController
	// ------------------------------------------------------
	public static chestManager chestManager() {
		return cm;
	}

	public static void openGUI(Player player, int page) {

		int startNum = 0; // decalre variable
		int pageNum = page; // what page to start from
		int pageSize = 53; // size of pages
		boolean showNext = true; // to show next page icon or not

		// setup icon for playerwarps to show as
		Wool PlayerWarpIcon = new Wool(DyeColor.LIGHT_BLUE);
		ItemStack NextPlayerWool = PlayerWarpIcon.toItemStack(1);
		ItemMeta NextPlayerMeta = NextPlayerWool.getItemMeta();
		
		// set nextPage icon
		Wool wool = new Wool(DyeColor.LIME);
		ItemStack NextPage = wool.toItemStack(1);
		ItemMeta NextPageMeta = NextPage.getItemMeta();

		// create inventory
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.DARK_RED + "PlayerWarps");

		// create array list of warps by name from hashmap
		ArrayList<String> PlayerWarpList = new ArrayList<String>();
		PlayerWarpList = hashMapToArray(PlayerWarps.playerWarps);

		// set start
		startNum = pageNum * pageSize;

		// calculate loop size
		int loopSize = startNum + 53;

		// check if page size is smaller then max pageSize
		// if not then set to actual size, and set showNext to false
		if (loopSize > (PlayerWarpList.size())) {
			loopSize = ((PlayerWarpList.size()) - startNum);
			showNext = false;
		}

		// loop through all for the page
		for (int i = 0; i < loopSize; i++) {
			NextPlayerMeta.setDisplayName(ChatColor.GOLD + PlayerWarpList.get(i + startNum));
			NextPlayerWool.setItemMeta(NextPlayerMeta);

			// add to invetory
			inv.setItem(i, NextPlayerWool);
		}

		//if going to show nextPage icon or not
		if (showNext) {
			NextPageMeta.setDisplayName(ChatColor.GREEN + "Next Page: " + (pageNum + 1));
			NextPage.setItemMeta(NextPageMeta);

			inv.setItem(53, NextPage);
		}

		//Show the inventory
		player.openInventory(inv);
	}

	// convert hashMap to an ArrayList
	public static ArrayList<String> hashMapToArray(HashMap<String, String> hashMap) {

		ArrayList<String> arrayList = new ArrayList<String>();

		for (Entry<String, String> entry : hashMap.entrySet()) {
			String key = entry.getKey();
			arrayList.add(key);
		}

		return arrayList;
	}

}
