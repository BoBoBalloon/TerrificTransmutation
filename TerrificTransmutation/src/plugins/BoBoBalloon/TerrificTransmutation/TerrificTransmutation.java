package plugins.BoBoBalloon.TerrificTransmutation;

import org.bukkit.plugin.java.JavaPlugin;

import plugins.BoBoBalloon.TerrificTransmutation.Commands.GetTomeCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.MasterCommand;

public class TerrificTransmutation extends JavaPlugin {
	
	public static TerrificTransmutation PLUGIN;

	@Override
	public void onEnable() {
		PLUGIN = this;
		
		new TransmutationTome();
		
		new MasterCommand();
		new GetTomeCommand();
	}
	
	public static TerrificTransmutation getPlugin() {
		return PLUGIN;
	}
	
}
