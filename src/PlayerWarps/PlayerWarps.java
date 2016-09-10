package PlayerWarps;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import Listeners.ChestListener;
import Listeners.CommandListener;
import Managers.chestManager;
import Utils.SLAPI;

public class PlayerWarps extends JavaPlugin{
	
	public static Economy econ = null;
    public static Permission perms = null;
    public static PlayerWarps instance;
    public static chestManager chestman = null;
    public String playerDataFolderPath = this.getDataFolder() + File.separator + "player_warps"+ File.separator;
    public static HashMap<String, String> playerWarps = new HashMap<String, String>();// namecolor list hashmap
    
    public static Plugin getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onDisable()
	 */
	@Override
	public void onDisable() {
		try {
			SLAPI.save(playerWarps, playerDataFolderPath+"warps.bin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.bukkit.plugin.java.JavaPlugin#onEnable()
	 */
	@Override
	public void onEnable() {
		
		instance = this;
		
		this.getCommand("playerwarps").setExecutor(new CommandListener(this));
		Bukkit.getServer().getPluginManager().registerEvents(new ChestListener(null), this);
		chestman = new chestManager(this);
		
		setupEconomy();
		setupPermissions();
		
		try {
			playerWarps = SLAPI.load(playerDataFolderPath+"warps.bin");
		} catch (Exception e) {
			// handle the exception
			e.printStackTrace();
		}
		
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

}
