package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.PlayerInventory;

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
	public void onMove(InventoryInteractEvent event) {
		if (!TerrificTransmutation.getPlugin().getConfig().getBoolean("Moveable")) {
			if (event.getWhoClicked().getItemOnCursor().getType() != Material.AIR && Tome.isTome(event.getWhoClicked().getItemOnCursor()) && !(event.getInventory() instanceof PlayerInventory)) {
				event.setCancelled(true);
			}
		}
	}
}