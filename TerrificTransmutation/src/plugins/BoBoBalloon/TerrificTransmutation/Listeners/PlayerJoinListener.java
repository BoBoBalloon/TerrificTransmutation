package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import plugins.BoBoBalloon.TerrificTransmutation.AddEMC;
import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Database.Database;

public class PlayerJoinListener implements Listener {

	public PlayerJoinListener() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Database database = new Database();
		if (!database.getDatabase().contains("PlayerData." + event.getPlayer().getUniqueId() + ".EMC")) {
			database.getDatabase().set("PlayerData." + event.getPlayer().getUniqueId() + ".EMC", 0);
			database.getDatabase().options().copyDefaults(true);
			database.saveDatabase();
		}
		
		
		for (ItemStack item : event.getPlayer().getInventory().getContents()) {
			new AddEMC(item).setEMC();
		}
		
		
	}
}
