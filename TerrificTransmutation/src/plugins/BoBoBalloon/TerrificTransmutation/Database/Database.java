package plugins.BoBoBalloon.TerrificTransmutation.Database;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;

public class Database {

	private File databaseFile;
	private FileConfiguration databaseConfig;
	
	public Database(String name) {
		try {
			databaseFile = new File(TerrificTransmutation.getDatabase() + "/" + name + ".yml");
			
			if (!databaseFile.createNewFile()) {
				databaseFile = new File(TerrificTransmutation.getDatabase(), name + ".yml");
			}
			
			databaseConfig = YamlConfiguration.loadConfiguration(databaseFile);
			
			databaseConfig.load(databaseFile);
		} catch (IOException | InvalidConfigurationException error) {
			error.printStackTrace();
		}
	}
	
	public FileConfiguration getConfig() {
		return databaseConfig;
	}
	
	public File getFile() {
		return databaseFile;
	}
	
	public void saveConfig() {
		try {
			databaseConfig.save(databaseFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
