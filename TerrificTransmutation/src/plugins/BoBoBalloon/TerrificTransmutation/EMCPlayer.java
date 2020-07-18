package plugins.BoBoBalloon.TerrificTransmutation;

import org.bukkit.entity.Player;

import plugins.BoBoBalloon.TerrificTransmutation.Database.Database;

public class EMCPlayer {

	public Player player;
	public int emc;
	public Database database = new Database();
	
	public EMCPlayer(Player player) {
		this.player = player;
		
		emc = database.getDatabase().getInt("PlayerData." + player.getUniqueId() + ".EMC");
	}
	
	public int getEMC() {
		return emc;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setEMC(int amount) {
		database.getDatabase().set("PlayerData." + player.getUniqueId() + ".EMC", amount);
		database.getDatabase().options().copyDefaults(true);
		database.getDatabase().options().copyHeader(true);
		database.saveDatabase();
		emc = amount;
	}
}