package plugins.BoBoBalloon.TerrificTransmutation;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataSetCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.GetTomeCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.GiveTomeCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.MasterCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Items.EMCMenu;
import plugins.BoBoBalloon.TerrificTransmutation.Items.Tome;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.OpenContainerListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerCreativeItemListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerJoinListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerPickupItemListener;

public class TerrificTransmutation extends JavaPlugin {
	
	//TO DO LIST:
	
	//maybe later:
	//add transmutation table, new "block" you can place and works like tablet from there
	
	
	//Dependencies:
	//ProtocolLib
	
	public static TerrificTransmutation PLUGIN;
	public static File database;

	@Override
	public void onEnable() {
		//initializing
		PLUGIN = this;
		database = new File(PLUGIN.getDataFolder() + "/database");
		
		//files
		getConfig().options().copyHeader(true);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		if (!database.exists()) database.mkdir();
		
		//items & blocks
		new Tome();
		
		//menu
		new EMCMenu();
		
		//listeners
		new PlayerCreativeItemListener();
		new PlayerJoinListener();
		new PlayerPickupItemListener();
		new OpenContainerListener();
		
		//commands
		new MasterCommand();
		new EMCPlayerDataCommand();
		new EMCPlayerDataSetCommand();
		new GetTomeCommand();
		new GiveTomeCommand();
	}
	
	public static TerrificTransmutation getPlugin() {
		return PLUGIN;
	}
	
	public static File getDatabase() {
		return database;
	}
}