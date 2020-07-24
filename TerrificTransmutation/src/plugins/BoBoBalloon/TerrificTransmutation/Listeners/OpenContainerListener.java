package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Objects.AddEMC;

public class OpenContainerListener implements Listener {

	public OpenContainerListener() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void checkIfValidItem(InventoryOpenEvent event) {
		if (event.getInventory().getType() == InventoryType.CHEST ||
				event.getInventory().getType() == InventoryType.ENDER_CHEST ||
				event.getInventory().getType() == InventoryType.SHULKER_BOX ||
				event.getInventory().getType() == InventoryType.DISPENSER ||
				event.getInventory().getType() == InventoryType.DROPPER ||
				event.getInventory().getType() == InventoryType.BARREL ||
				event.getInventory().getType() == InventoryType.HOPPER) {
			for (ItemStack item:event.getInventory().getContents()) {
				if (item != null) {
					new AddEMC(item).setEMC();
				}
			}
		}
	}
}