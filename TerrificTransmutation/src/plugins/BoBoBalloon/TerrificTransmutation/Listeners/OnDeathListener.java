package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Items.Tome;

public class OnDeathListener implements Listener {
	
	private List<Player> giveOnRespawn;

	public OnDeathListener() {
		giveOnRespawn = new ArrayList<Player>();
		
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if (TerrificTransmutation.getPlugin().getConfig().getBoolean("KeepOnDeath") && !event.getKeepInventory()) {
			boolean addOnRespawn = false;
			List<ItemStack> drops = new ArrayList<ItemStack>();
			for (ItemStack item : event.getDrops()) drops.add(item);
			
			for (ItemStack item : drops) {
				if (Tome.isTome(item)) {
					event.getDrops().remove(item);
					addOnRespawn = true;
				}
			}
			if (addOnRespawn) giveOnRespawn.add(event.getEntity());
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent event) {
		if (TerrificTransmutation.getPlugin().getConfig().getBoolean("KeepOnDeath") && giveOnRespawn.contains(event.getPlayer())) {
			event.getPlayer().getInventory().addItem(Tome.tome());
			giveOnRespawn.remove(event.getPlayer());
		}
	}
}