package plugins.BoBoBalloon.TerrificTransmutation;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataSetCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.GetTomeCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.MasterCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.OpenContainerListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerCreativeItemListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerJoinListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerPickupItemListener;

public class TerrificTransmutation extends JavaPlugin {
	
	//TO DO LIST:
	//add search feature
	//add transmutation table, new "block" you can place and works like tablet from there
	
	public static TerrificTransmutation PLUGIN;
	public static File database;

	@Override
	public void onEnable() {
		PLUGIN = this;
		database = new File(TerrificTransmutation.getPlugin().getDataFolder() + "/database");
		
		getConfig().options().copyHeader(true);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		if (!database.exists()) {
			database.mkdir();
		}
		
		new EMCMenu();
		
		new PlayerCreativeItemListener();
		new PlayerJoinListener();
		new PlayerPickupItemListener();
		new OpenContainerListener();
		
		new MasterCommand();
		new EMCPlayerDataCommand();
		new EMCPlayerDataSetCommand();
		new GetTomeCommand();
	}

	public static TerrificTransmutation getPlugin() {
		return PLUGIN;
	}
	
	public static File getDatabase() {
		return database;
	}
}