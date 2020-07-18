package plugins.BoBoBalloon.TerrificTransmutation;

import org.bukkit.plugin.java.JavaPlugin;

import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataSetCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.GetTomeCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.MasterCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Database.Database;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.OpenContainerListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerCreativeItemListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerJoinListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerPickupItemListener;

public class TerrificTransmutation extends JavaPlugin {
	
	//TO DO LIST:
	//creative listener broken fix later
	//make gui
	//make every player a list of strings that is the materials they unlocked and add a method to the EMCPlayer class that returns-
	//said list, call the path "PlayerData.UUID.UnlockedItems"
	//make it work
	//add transmutation table, new "block" you can place and works like tablet from there
	
	public static TerrificTransmutation PLUGIN;

	@Override
	public void onEnable() {
		PLUGIN = this;
		
		getConfig().options().copyHeader(true);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		new Database().createDatabaseConfig();
		
		new TransmutationTome();
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
	
}