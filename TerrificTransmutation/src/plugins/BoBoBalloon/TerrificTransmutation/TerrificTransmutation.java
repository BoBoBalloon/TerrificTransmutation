package plugins.BoBoBalloon.TerrificTransmutation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.EMCPlayerDataSetCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.GetTomeCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.GiveTomeCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Commands.MasterCommand;
import plugins.BoBoBalloon.TerrificTransmutation.Items.EMCMenu;
import plugins.BoBoBalloon.TerrificTransmutation.Items.Tome;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.OnDeathListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.OnDropOrMoveListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.OpenContainerListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerCreativeItemListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerJoinListener;
import plugins.BoBoBalloon.TerrificTransmutation.Listeners.PlayerPickupItemListener;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class TerrificTransmutation extends JavaPlugin {
	
	//TO DO LIST:
	//make it so you cant add in items that have enchantments
	//get staff to work on config.yml
	
	//maybe later:
	//add transmutation table, new "block" you can place and works like tome from there
	//dont use more then one type of listener
	
	
	
	private static TerrificTransmutation PLUGIN;
	private static Connection connection;
	private String host, database, username, password;
	private int port;


	@Override
	public void onEnable() {
		//initializing
		PLUGIN = this;
		
		mySQLSettup();
		
		//files
		getConfig().options().copyHeader(true);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		
		//items & blocks
		new Tome();
		
		//menu
		new EMCMenu();
		
		//listeners
		new PlayerCreativeItemListener();
		new PlayerJoinListener();
		new PlayerPickupItemListener();
		new OpenContainerListener();
		new OnDeathListener();
		new OnDropOrMoveListener();
		
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
	
	private void mySQLSettup() {
		host = PLUGIN.getConfig().getString("Host");
		port = PLUGIN.getConfig().getInt("Port");
		database = PLUGIN.getConfig().getString("DatabaseName");
		username = PLUGIN.getConfig().getString("Username");
		password = PLUGIN.getConfig().getString("Password");
		
		try {
			synchronized(this) {
				if (getConnection() != null && !getConnection().isClosed()) return;
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
				Bukkit.getConsoleSender().sendMessage(Strings.format("&rTerrificTransmutation:&a MySQL connection enabled!"));
			}
		
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static Statement getStatement() {
		try {
			return connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}