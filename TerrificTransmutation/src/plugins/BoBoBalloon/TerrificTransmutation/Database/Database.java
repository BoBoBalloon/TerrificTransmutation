package plugins.BoBoBalloon.TerrificTransmutation.Database;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;

public class Database {

	public File databaseFile = new File(TerrificTransmutation.getPlugin().getDataFolder(), "database.yml");
	public FileConfiguration databaseConfig = YamlConfiguration.loadConfiguration(databaseFile);
	
	public FileConfiguration getDatabase() {
		return databaseConfig;
	}
	
	public File getFile() {
		return databaseFile;
	}
	
	public void saveDatabase() {
		try {
			getDatabase().save(databaseFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createDatabaseConfig() {
        if (!databaseFile.exists()) {
        	databaseFile.getParentFile().mkdirs();
        	TerrificTransmutation.getPlugin().saveResource("database.yml", false);
         }

        try {
        	databaseConfig.load(databaseFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
	
}
