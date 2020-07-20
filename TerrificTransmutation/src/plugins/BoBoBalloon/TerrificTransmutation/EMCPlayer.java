package plugins.BoBoBalloon.TerrificTransmutation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import plugins.BoBoBalloon.TerrificTransmutation.Database.Database;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class EMCPlayer {

	private Player player;
	private int emc;
	private List<String> unlockedMaterials;
	private Database database;
	
	public EMCPlayer(Player player) {
		this.player = player;
		
		database = new Database(player.getUniqueId().toString());
		
		emc = database.getConfig().getInt("EMC");
		
		if (!database.getConfig().getStringList("UnlockedItems").isEmpty()) {
			unlockedMaterials = database.getConfig().getStringList("UnlockedItems");
		} else {
			unlockedMaterials = null;
		}
	}
	
	public int getEMC() {
		return emc;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setEMC(int amount) {
		database.getConfig().set("EMC", amount);
		database.getConfig().options().copyDefaults(true);
		database.getConfig().options().copyHeader(true);
		database.saveConfig();
		emc = amount;
	}
	
	public List<String> getRawUnlockedMaterials() {
		return unlockedMaterials;
	}
	
	public void setRawUnlockedMaterials(List<String> list) {
		database.getConfig().set("UnlockedItems", list);
		database.getConfig().options().copyDefaults(true);
		database.getConfig().options().copyHeader(true);
		database.saveConfig();
		unlockedMaterials = list;
	}
	
	public List<Material> getUnlockedMaterials() {
		if (unlockedMaterials == null) return null;
		List<Material> materials = new ArrayList<Material>();
		for (String string : unlockedMaterials) {
			materials.add(Material.getMaterial(string));
		}
		return materials;
	}
	
	public List<ItemStack> getUnlockedItems() {
		if (getUnlockedMaterials() == null) return null;
		List<ItemStack> items = new ArrayList<ItemStack>();
		
		for (Material material : getUnlockedMaterials()) {
			items.add(getValueItemStack(material));
		}
		
		return items;
	}
	
	public int getValue(Material material) {
		if (!TerrificTransmutation.getPlugin().getConfig().getBoolean("IsEnabled." + material.name())) return -1;
		return TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + material.name());
	}
	
	private ItemStack getValueItemStack(Material material) {
		ItemStack item = new ItemStack(material, 1);
		if (TerrificTransmutation.getPlugin().getConfig().getBoolean("IsEnabled." + material.name())) {
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add(Strings.format("&eEMC&r: " + TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name())));
			meta.setLore(lore);
			item.setItemMeta(meta);
		}
		if (item.getItemMeta().hasLore()) return item;
		return null;
	}
	
}