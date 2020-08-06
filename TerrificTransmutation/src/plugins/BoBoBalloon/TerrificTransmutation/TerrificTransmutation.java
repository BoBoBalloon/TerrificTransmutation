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
	//change from ymls to a MySQL database
	//add keepondeath boolean in config to make it so if true, the tome is not dropped on death and when player respawns gives it back
	//add moveable boolean in config to make it if true, the tome cannot be moved out of the inventory/dropped on the ground
	//make the name of the transmutation tome configurable
	//when crafting/smelting an item put result through the AddEMC class
	//get staff to work on config.yml
	
	//maybe later:
	//add transmutation table, new "block" you can place and works like tome from there
	//dont use more then one type of listener
	
	
	//CHANGELOG:
	//name of transmutation tome is now customizable
	
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