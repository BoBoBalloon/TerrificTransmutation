package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Database.Database;
import plugins.BoBoBalloon.TerrificTransmutation.Objects.AddEMC;

public class PlayerJoinListener implements Listener {

	public PlayerJoinListener() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Database database = new Database(event.getPlayer().getUniqueId().toString());
		if (database.getFile().length() == 0) {
			database.getConfig().set("EMC", 0);
			database.getConfig().set("UnlockedItems", new ArrayList<String>());
			database.saveConfig(); 
		}
		
		
		for (ItemStack item : event.getPlayer().getInventory().getContents()) {
			new AddEMC(item).setEMC();
		}
		
		
	}
	
}
