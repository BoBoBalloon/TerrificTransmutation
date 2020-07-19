package plugins.BoBoBalloon.TerrificTransmutation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import plugins.BoBoBalloon.TerrificTransmutation.Utils.NormalUtils;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class AddEMC {
	
	public ItemStack item;
	public NamespacedKey key = new NamespacedKey(TerrificTransmutation.getPlugin(), "EMCValue");
	
	public AddEMC(ItemStack item) {
		this.item = item;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public NamespacedKey getKey() {
		return key;
	}
	
	public void setEMC() {
		if (item != null && 
				item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
			if (item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.INTEGER) !=
					TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name())) {
				ItemMeta meta = item.getItemMeta();
				List<String> lore = new ArrayList<String>();
				lore.add(Strings.format("&eEMC&r: " + TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name())));
				meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name()));
				meta.setLore(lore);
				item.setItemMeta(meta);
			}
		} else {
			addEMC();
		}
	}

	private void addEMC() {
		if (item != null && NormalUtils.isNormal(item) && 
				TerrificTransmutation.getPlugin().getConfig().getBoolean("IsEnabled." + item.getType().name())) {
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add(Strings.format("&eEMC&r: " + TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name())));
			meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name()));
			meta.setLore(lore);
			item.setItemMeta(meta);
		}
	}
	
	public ItemStack setEMCToItem() {
		if (item != null && 
				item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
			if (item.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.INTEGER) !=
					TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name())) {
				ItemMeta meta = item.getItemMeta();
				List<String> lore = new ArrayList<String>();
				lore.add(Strings.format("&eEMC&r: " + TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name())));
				meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name()));
				meta.setLore(lore);
				item.setItemMeta(meta);
				return item;
			}
		} else {
			return addEMCToItem();
		}
		return null;
	}
	
	private ItemStack addEMCToItem() {
		if (item != null && NormalUtils.isNormal(item) && 
				TerrificTransmutation.getPlugin().getConfig().getBoolean("IsEnabled." + item.getType().name())) {
			ItemMeta meta = item.getItemMeta();
			List<String> lore = new ArrayList<String>();
			lore.add(Strings.format("&eEMC&r: " + TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name())));
			meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + item.getType().name()));
			meta.setLore(lore);
			item.setItemMeta(meta);
			return item;
		}
		return null;
	}
}
