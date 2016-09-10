package Managers;

import java.util.ArrayList;

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
    	
    	ArrayList<String> PlayerWarpList = new ArrayList<String>();
    	PlayerWarpList.add("Player1");
    	PlayerWarpList.add("Player2");
    	PlayerWarpList.add("Player3");
    	PlayerWarpList.add("Player4");
    	PlayerWarpList.add("Player5");
    	PlayerWarpList.add("Player6");
    	PlayerWarpList.add("Player7");
    	PlayerWarpList.add("Player8");
    	PlayerWarpList.add("Player9");
    	PlayerWarpList.add("Player10");
    	PlayerWarpList.add("Player11");
    	PlayerWarpList.add("Player12");
    	PlayerWarpList.add("Player13");
    	PlayerWarpList.add("Player14");
    	PlayerWarpList.add("Player15");
    	PlayerWarpList.add("Player16");
    	PlayerWarpList.add("Player17");
    	PlayerWarpList.add("Player18");
    	PlayerWarpList.add("Player19");
    	PlayerWarpList.add("Player20");
    	PlayerWarpList.add("Player21");
    	PlayerWarpList.add("Player22");
    	PlayerWarpList.add("Player23");
    	PlayerWarpList.add("Player24");
    	PlayerWarpList.add("Player25");
    	PlayerWarpList.add("Player26");
    	PlayerWarpList.add("Player27");
    	PlayerWarpList.add("Player28");
    	PlayerWarpList.add("Player29");
    	PlayerWarpList.add("Player30");
    	PlayerWarpList.add("Player31");
    	PlayerWarpList.add("Player32");
    	PlayerWarpList.add("Player33");
    	PlayerWarpList.add("Player34");
    	PlayerWarpList.add("Player35");
    	PlayerWarpList.add("Player36");
    	PlayerWarpList.add("Player37");
    	PlayerWarpList.add("Player38");
    	PlayerWarpList.add("Player39");
    	PlayerWarpList.add("Player30");
    	PlayerWarpList.add("Player41");
    	PlayerWarpList.add("Player42");
    	PlayerWarpList.add("Player43");
    	PlayerWarpList.add("Player44");
    	PlayerWarpList.add("Player45");
    	PlayerWarpList.add("Player46");
    	PlayerWarpList.add("Player47");
    	PlayerWarpList.add("Player48");
    	PlayerWarpList.add("Player49");
    	PlayerWarpList.add("Player50");
    	PlayerWarpList.add("Player51");
    	PlayerWarpList.add("Player52");
    	PlayerWarpList.add("Player53");
    	PlayerWarpList.add("Player54");
    	PlayerWarpList.add("Player55");
    	PlayerWarpList.add("Player56");
    	PlayerWarpList.add("Player57");
    	PlayerWarpList.add("Player58");
    	PlayerWarpList.add("Player59");
    	PlayerWarpList.add("Player60");
    	PlayerWarpList.add("Player61");
    	PlayerWarpList.add("Player62");
    	PlayerWarpList.add("Player63");
    	PlayerWarpList.add("Player64");
    	PlayerWarpList.add("Player65");
    	PlayerWarpList.add("Player66");
    	PlayerWarpList.add("Player67");
    	PlayerWarpList.add("Player68");
    	PlayerWarpList.add("Player69");
    	PlayerWarpList.add("Player70");
    	PlayerWarpList.add("Player71");
    	PlayerWarpList.add("Player72");
    	PlayerWarpList.add("Player73");
    	PlayerWarpList.add("Player74");
    	PlayerWarpList.add("Player75");
    	PlayerWarpList.add("Player76");
    	PlayerWarpList.add("Player77");
    	PlayerWarpList.add("Player78");
    	PlayerWarpList.add("Player79");
    	PlayerWarpList.add("Player80");
    	

        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD + "PlayerWarps");
        
        int startNum = 0;
        int finishNumber = 0;
        int pageSize = 30;
        int pageNum = page;
        
        startNum = pageNum * 53;
        Bukkit.broadcastMessage("startNum: "+startNum);
        finishNumber = startNum + 53;
        Bukkit.broadcastMessage("finNum: "+finishNumber);
        
        /*
         * 
         * page 1
         * 
         *  start = 53
         *  finish = 106
         *  list = 60
         * 
         *  really = 7
         * 
         * 
         * 
         * 
         */
        
        if(finishNumber > (PlayerWarpList.size())){
        	finishNumber = (PlayerWarpList.size() - startNum);
        	Bukkit.broadcastMessage("finNum2: "+finishNumber);
        }
        
    	for (int i = startNum; i < finishNumber; i++) {
    		Wool PlayerWool = new Wool(DyeColor.LIGHT_BLUE);
            ItemStack NextPlayerWool = PlayerWool.toItemStack(1);
            ItemMeta NextPlayerMeta = NextPlayerWool.getItemMeta();
     
            NextPlayerMeta.setDisplayName(ChatColor.GOLD + PlayerWarpList.get(i));
            NextPlayerWool.setItemMeta(NextPlayerMeta);
            
            inv.setItem(i, NextPlayerWool);
		}
    	
    
    	
        Wool wool = new Wool(DyeColor.LIME);
        ItemStack NextPage = wool.toItemStack(1);
        ItemMeta NextPageMeta = NextPage.getItemMeta();
 
        NextPageMeta.setDisplayName(ChatColor.GREEN + "Next Page: " + (pageNum + 1));
        NextPage.setItemMeta(NextPageMeta);
        
        inv.setItem(53, NextPage);
 
        player.openInventory(inv);
        }
	
}
