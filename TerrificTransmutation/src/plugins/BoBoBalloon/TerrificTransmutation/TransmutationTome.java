package plugins.BoBoBalloon.TerrificTransmutation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import plugins.BoBoBalloon.TerrificTransmutation.Utils.NormalUtils;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class TransmutationTome implements Listener {
	
	public static ItemStack item;
	public static NamespacedKey key;
	
	public static Inventory inventory;
	public static String inventory_name;
	public static int inv_boxes = 6;
	public static int rows = inv_boxes * 9;
	
	public TransmutationTome() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
		initializeUI();
		tomeItem();
	}
	
	public static ItemStack getItem() {
		return item;
	}
	
	public static NamespacedKey getKey() {
		return key;
	}
	
	private void tomeItem() {
		ItemStack tome = NormalUtils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2VlYTM0NTkwOGQxN2RjNDQ5NjdkMWRjZTQyOGYyMmYyYjE5Mzk3MzcwYWJlYjc3YmRjMTJlMmRkMWNiNiJ9fX0=");
		ItemMeta meta = tome.getItemMeta();
		NamespacedKey tomeKey = new NamespacedKey(TerrificTransmutation.getPlugin(), "isTome");
		
		meta.setDisplayName(Strings.format("&dTransmutation Tome"));
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(Strings.format("&rClick this item to open your &dtransmutation tome"));
		meta.setLore(lore);
		
		meta.getPersistentDataContainer().set(tomeKey, PersistentDataType.STRING, "true");
		
		
		tome.setItemMeta(meta);
		key = tomeKey;
		item = tome;
	}
	
	@EventHandler
	public void tomeOpen(PlayerInteractEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand() != null) {
			if (isTome(event.getPlayer().getInventory().getItemInMainHand())) {
				event.getPlayer().sendMessage("is item");
				event.getPlayer().openInventory(GUI());
				event.setCancelled(true);
			}
		}
	}
	
	private ItemStack backround() {
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(Strings.format("&r"));
		
		meta.setCustomModelData(69);
		
		item.setItemMeta(meta);
		return item;
	}
	
	private void initializeUI() {
		inventory_name = Strings.format("&r&7Anvil");
		
		inventory = Bukkit.createInventory(null, rows);
	}
	
	private Inventory GUI() {
		Inventory mainMenu = Bukkit.createInventory(null, rows, inventory_name);

		//menu of all items here but im too lazy
		inventory.setItem(1 - 1, backround());
		inventory.setItem(2 - 1, new ItemStack(Material.DIRT, 1)); //remove later
		
		mainMenu.setContents(inventory.getContents());
		return mainMenu;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase(inventory_name)) {
				if (event.getCurrentItem() != null) {
					if (!event.getCurrentItem().getItemMeta().hasCustomModelData() &&
							!(event.getClickedInventory() instanceof PlayerInventory)) {
						//remove emc value
						event.getWhoClicked().getInventory().addItem(new ItemStack(event.getCurrentItem().getType(), (event.getCurrentItem().getAmount())));
					}
					event.setCancelled(true);
			}
		}
	}
	
	
	public static boolean isTome(ItemStack one) {
		if (one.hasItemMeta()) {
			if (!one.getItemMeta().getPersistentDataContainer().has(TransmutationTome.getKey(), PersistentDataType.STRING)) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
}
