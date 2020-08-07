package plugins.BoBoBalloon.TerrificTransmutation.Objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class EMCPlayer {

	private Player player;
	private int emc;
	private List<String> unlockedMaterials;
	private String tp_users, tp_user_material;
	
	public EMCPlayer(Player player) {
		this.player = player;
		
		tp_users = TerrificTransmutation.getPlugin().getConfig().getString("UserTableName");
		tp_user_material = TerrificTransmutation.getPlugin().getConfig().getString("MaterialTableName");
		
		try {
			ResultSet emcValue = TerrificTransmutation.getStatement().executeQuery("select * from " + tp_users + " where uuid='" + player.getUniqueId() + "';");
			while (emcValue.next()) {
				emc = emcValue.getInt("emc");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			emc = 0;
		}
		
		unlockedMaterials = new ArrayList<String>();
		try {
			ResultSet materials = TerrificTransmutation.getStatement().executeQuery("select material from " + tp_user_material + " where uuid='" + player.getUniqueId() +"';");
			while (materials.next()) {
				unlockedMaterials.add(materials.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		try {
			TerrificTransmutation.getStatement().executeUpdate("update " + tp_users + " set emc=" + amount + " where uuid='" + player.getUniqueId() +"';");
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		
		emc = amount;
	}
	
	public List<String> getRawUnlockedMaterials() {
		return unlockedMaterials;
	}
	
	public void setRawUnlockedMaterials(List<String> list) {
		
			try {
				TerrificTransmutation.getStatement().executeUpdate("delete from " + tp_user_material + " where uuid='" + player.getUniqueId() +"';");
			} catch (SQLException e) {
				e.printStackTrace();
				return;
			}
		
		for (String string : list) {
			try {
				TerrificTransmutation.getStatement().executeUpdate("INSERT INTO " + tp_user_material + " ( uuid, material ) VALUES ( '" + player.getUniqueId() +"', '" + string +"' );");
			} catch (SQLException e) {
				unlockedMaterials = null;
				e.printStackTrace();
				return;
			}
		}
		unlockedMaterials = list;
	}
	
	public List<Material> getUnlockedMaterials() {
		if (unlockedMaterials == null) return null;
		List<Material> materials = new ArrayList<Material>();
		for (String string : unlockedMaterials) {
			Material mat = Material.getMaterial(string);
			if (AddEMC.getValue(mat) != -1) materials.add(mat);
		}
		if (materials.isEmpty()) return null;
		return materials;
	}
	
	public List<Material> getUnlockedMaterialsInOrder() {
		if (getUnlockedMaterials() == null) return null;
		Map<Material, Integer> matMap = new HashMap<Material, Integer>();
		List<Material> availableMats = new ArrayList<Material>();
		
		for (Material mat : getUnlockedMaterials()) {
			matMap.put(mat, TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + mat.name()));
			availableMats.add(mat);
		}
				
		List<Integer> values = new ArrayList<>(matMap.values());
		Collections.sort(values, Collections.reverseOrder());

		
		
		List<Material> materials = new ArrayList<Material>();
		for (int integer : values) {
			for (Material mat : getUnlockedMaterials()) {
				if (availableMats.contains(mat)) {
					if (matMap.get(mat) == integer) {
						materials.add(mat);
						availableMats.remove(mat);
					}
				}
			}
		}
		return materials;
	}
	
	public List<ItemStack> getUnlockedItems() {
		if (getUnlockedMaterialsInOrder() == null) return null;
		List<ItemStack> items = new ArrayList<ItemStack>();
		
		for (Material material : getUnlockedMaterialsInOrder()) {
			items.add(getValueItemStack(material));
		}
		
		return items;
	}
	
	public List<ItemStack> searchItems(String string) {
		if (getUnlockedItems() == null) return null;
		List<ItemStack> items = new ArrayList<ItemStack>();
		List<ItemStack> rawItems = getUnlockedItems();
		
		for (ItemStack item : rawItems) {
			if (item.getType().name().toLowerCase().replace("_", " ").contains(ChatColor.stripColor(string.toLowerCase()))) {
				items.add(item);
			}
		}
		
		return items;
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