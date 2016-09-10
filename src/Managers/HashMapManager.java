package Managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import PlayerWarps.PlayerWarps;
import Utils.SLAPI;

public class HashMapManager {

	
	private final PlayerWarps plugin;

	public HashMapManager(PlayerWarps plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}
	
	
	public void saveHashMap(){
		try {
			SLAPI.save(PlayerWarps.playerWarps, this.plugin.playerDataFolderPath+"warps.bin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loadHashMap(){
		try {
			PlayerWarps.playerWarps = SLAPI.load(this.plugin.playerDataFolderPath+"warps.bin");
		} catch (Exception e) {
			// handle the exception
			e.printStackTrace();
		}
	}
	
	public void putIntoHashMap(Player player, Location loc){
		String hashLocation = loc2str(loc);
		PlayerWarps.playerWarps.put(player.getName(), hashLocation);
	}
	
	public void removeFromHashMap(Player player){
		if(PlayerWarps.playerWarps.containsKey(player.getName())){
			String p = player.getName();
			PlayerWarps.playerWarps.remove(p);
		}
	}
	
	public boolean checkHashMap(Player player){
		if(PlayerWarps.playerWarps.containsKey(player.getName())){
			return true;
		}
		return false;
		
	}
	
	public Location getWarpLocation(String name){
		
		String loc = (String) PlayerWarps.playerWarps.get(name);
		return str2loc(loc);
		
	}

    Location parseLoc(String str){
	    String[] arg = str.split(",");
	    double[] parsed = new double[3];
	    for (int a = 0; a < 3; a++) {
	    parsed[a] = Double.parseDouble(arg[a+1]);
	    }
	     
	    Location location = new Location (plugin.getServer().getWorld(arg[0]), parsed[0], parsed[1], parsed[2]);
	    return location;
    }
    
    public Location str2loc(String str){
    	 
        String str2loc[]=str.split("\\:");
     
        Location loc = new Location(Bukkit.getServer().getWorld(str2loc[0]),0,0,0);
     
        loc.setX(Double.parseDouble(str2loc[1]));
     
        loc.setY(Double.parseDouble(str2loc[2]));
     
        loc.setZ(Double.parseDouble(str2loc[3]));
     
        return loc;
     
    }
     
     
     
    public String loc2str(Location loc){
     
        return loc.getWorld().getName()+":"+loc.getBlockX()+":"+loc.getBlockY()+":"+loc.getBlockZ();
     
    }
	     
}
