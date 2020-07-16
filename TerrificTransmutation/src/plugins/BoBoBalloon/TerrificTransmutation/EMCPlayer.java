package plugins.BoBoBalloon.TerrificTransmutation;

import org.bukkit.entity.Player;

public class EMCPlayer {

	public Player player;
	public int emc;
	
	public EMCPlayer(Player player) {
		this.player = player;
		
		emc = TerrificTransmutation.getPlugin().getConfig().getInt("PlayerData." + player.getUniqueId());
	}
	
	public int getEMC() {
		return emc;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setEMC(int amount) {
		TerrificTransmutation.getPlugin().getConfig().set("PlayerData." + player.getUniqueId(), amount);
		TerrificTransmutation.getPlugin().getConfig().options().copyDefaults(true);
		TerrificTransmutation.getPlugin().saveConfig();
		emc = amount;
	}
	
	public void addEMC(int amount) {
		TerrificTransmutation.getPlugin().getConfig().set("PlayerData." + player.getUniqueId(), emc + amount);
		TerrificTransmutation.getPlugin().getConfig().options().copyDefaults(true);
		TerrificTransmutation.getPlugin().saveConfig();
		emc = emc + amount;
	}
	
	public void subtractEMC(int amount) {
		TerrificTransmutation.getPlugin().getConfig().set("PlayerData." + player.getUniqueId(), emc - amount);
		TerrificTransmutation.getPlugin().getConfig().options().copyDefaults(true);
		TerrificTransmutation.getPlugin().saveConfig();
		emc = emc - amount;
	}
}