package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;

public class PlayerJoinListener implements Listener {

	public PlayerJoinListener() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (!TerrificTransmutation.getPlugin().getConfig().contains("PlayerData." + event.getPlayer().getUniqueId())) {
			TerrificTransmutation.getPlugin().getConfig().set("PlayerData." + event.getPlayer().getUniqueId(), 0);
			TerrificTransmutation.getPlugin().getConfig().options().copyDefaults(true);
			TerrificTransmutation.getPlugin().saveConfig();
		}
	}
}
