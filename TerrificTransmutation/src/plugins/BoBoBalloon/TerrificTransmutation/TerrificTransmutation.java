package plugins.BoBoBalloon.TerrificTransmutation;

import org.bukkit.plugin.java.JavaPlugin;

import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataSetCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.GetTomeCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.MasterCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerJoinListener;

public class TerrificTransmutation extends JavaPlugin {
	
	//TO DO LIST:
	//make gui look nice and work
	
	
	public static TerrificTransmutation PLUGIN;

	@Override
	public void onEnable() {
		PLUGIN = this;
		
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		new TransmutationTome();
		new PlayerJoinListener();
		
		new MasterCommand();
		new EMCPlayerDataCommand();
		new EMCPlayerDataSetCommand();
		new GetTomeCommand();
	}
	
	public static TerrificTransmutation getPlugin() {
		return PLUGIN;
	}
	
}