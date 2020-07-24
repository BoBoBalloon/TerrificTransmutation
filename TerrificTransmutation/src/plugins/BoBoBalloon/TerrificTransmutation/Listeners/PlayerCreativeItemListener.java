package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCreativeEvent;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Objects.AddEMC;

public class PlayerCreativeItemListener implements Listener {

	public PlayerCreativeItemListener() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void getFromCreativeMenu(InventoryCreativeEvent event) {
		if (event.getClickedInventory() != null) {
			new AddEMC(event.getCursor()).setEMC();
		}
	}
}
