package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Items.Tome;

public class OnDropOrMoveListener implements Listener {

	public OnDropOrMoveListener() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if (!TerrificTransmutation.getPlugin().getConfig().getBoolean("Moveable") && Tome.isTome(event.getItemDrop().getItemStack())) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onMove(InventoryClickEvent event) {
		if (!TerrificTransmutation.getPlugin().getConfig().getBoolean("Moveable")) {
		     Inventory clicked = event.getClickedInventory();
		     if (event.getInventory().getHolder() instanceof Container) {
		        if (clicked == event.getWhoClicked().getInventory()) {
		           if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && Tome.isTome(event.getCurrentItem())) {
		               event.setCancelled(true);
		           }
		        }
		    }
		}
	}
}