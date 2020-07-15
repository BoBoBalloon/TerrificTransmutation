package plugins.BoBoBalloon.TerrificTransmutation.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIUtils {
	
	/**
	 * Made by some youtuber from a tutorial video, forgot who tho
	 */
	
	public static ItemStack createItem(Inventory inv, String materialString, int amount, int invSlot, String displayName, String... loreString) {
		
		ItemStack item;
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> lore = new ArrayList();
		
		item = new ItemStack(Material.matchMaterial(materialString), amount);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Strings.format(displayName));
		for (String string : loreString) {
			lore.add(Strings.format(string));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		inv.setItem(invSlot - 1, item);
		return item;
		
	}
	@SuppressWarnings("deprecation")
	public static ItemStack createItemByte(Inventory inv, int byteId, String materialString, int amount, int invSlot, String displayName, String... loreString) {
		
		ItemStack item;
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<String> lore = new ArrayList();
		
		item = new ItemStack(Material.matchMaterial(materialString), amount, (short) byteId);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Strings.format(displayName));
		for (String string : loreString) {
			lore.add(Strings.format(string));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		inv.setItem(invSlot - 1, item);
		return item;
		
	}
	public static ItemStack createItemNoLore(Inventory inv, String materialString, int amount, int invSlot, String displayName) {
		
		ItemStack item;
		
		item = new ItemStack(Material.matchMaterial(materialString), amount);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Strings.format(displayName));
		
		item.setItemMeta(meta);
		
		inv.setItem(invSlot - 1, item);
		return item;
		
	}
    @SuppressWarnings("deprecation")
	public static ItemStack createItemByteNoLore(Inventory inv, int byteId, String materialString, int amount, int invSlot, String displayName) {
		
		ItemStack item;
		
		item = new ItemStack(Material.matchMaterial(materialString), amount, (short) byteId);
		
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(Strings.format(displayName));
		
		item.setItemMeta(meta);
		
		inv.setItem(invSlot - 1, item);
		return item;
	}
}
