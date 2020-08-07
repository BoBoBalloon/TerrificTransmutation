package plugins.BoBoBalloon.TerrificTransmutation.Listeners;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Objects.AddEMC;

public class PlayerJoinListener implements Listener {

	public PlayerJoinListener() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (!containsPlayer(event.getPlayer().getUniqueId())) {
			try {
				TerrificTransmutation.getStatement().executeUpdate("INSERT INTO tp_users ( uuid, emc ) VALUES ( '" + event.getPlayer().getUniqueId() + "', 0 );");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		for (ItemStack item : event.getPlayer().getInventory().getContents()) {
			new AddEMC(item).setEMC();
		}
		
		
	}
	
	private boolean containsPlayer(UUID uuid) {
		boolean value = false;
		try {
			ResultSet result = TerrificTransmutation.getStatement().executeQuery("select uuid from tp_users where uuid='" + uuid +"';");
			while (result.next()) {
				if (!result.getString(1).isEmpty()) {
					value = true;
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
}
