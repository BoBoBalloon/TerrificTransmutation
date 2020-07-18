package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import plugins.BoBoBalloon.TerrificTransmutation.AddEMC;
import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;

public class PlayerPickupItemListener implements Listener {

	public PlayerPickupItemListener() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void onPlayerPickupItem(EntityPickupItemEvent event) {
		if (event.getEntity() instanceof Player) {
			new AddEMC(event.getItem().getItemStack()).setEMC();
		}
	}
}
