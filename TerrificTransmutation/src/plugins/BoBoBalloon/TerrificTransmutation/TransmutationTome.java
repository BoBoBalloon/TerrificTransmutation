package plugins.BoBoBalloon.TerrificTransmutation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import plugins.BoBoBalloon.TerrificTransmutation.Database.Database;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.NormalUtils;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class TransmutationTome implements Listener {
	
	public static ItemStack item;
	public NamespacedKey key;
	
	public Inventory inventory;
	public static String inventory_name;
	public int inv_boxes = 6;
	public int rows = inv_boxes * 9;
	
	public TransmutationTome() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
		initializeUI();
		tomeItem();
	}
	
	public static String getInventoryName() {
		return inventory_name;
	}
	
	public static ItemStack getItem() {
		return item;
	}
	
	public NamespacedKey getKey() {
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
			if (isTome(event.getPlayer().getInventory().getItemInMainHand()) && 
					event.getPlayer().hasPermission("terrifictransmutation.usetome")) {
				event.getPlayer().openInventory(GUI(event.getPlayer()));
				event.setCancelled(true);
			}
		}
	}
	
	 private boolean inventorySpaceFull(Player player) {
	    	boolean full = true;
	    	for (ItemStack item:player.getInventory().getStorageContents()) {
	    		if (item == null) {
	        		full = false;
	        		break;
	        		}
	    		}
	    	return full;
	    }
	
	private ItemStack backround(Material mat) {
		ItemStack item = new ItemStack(mat, 1);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(Strings.format("&r"));
		
		meta.setCustomModelData(69);
		
		item.setItemMeta(meta);
		return item;
	}
	
	private ItemStack EMCValue(EMCPlayer player) {
		ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(Strings.format("&aYour EMC is &c" + player.getEMC()));
		
		meta.setCustomModelData(69);
		
		item.setItemMeta(meta);
		return item;
	}
	
	private ItemStack itemConverter() {
		ItemStack item = new ItemStack(Material.HOPPER, 1);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(Strings.format("&eEMC Converter"));
		
		meta.setCustomModelData(69);
		
		item.setItemMeta(meta);
		return item;
	}
	
	private void initializeUI() {
		inventory_name = Strings.format("&r&dTransmutation");
		
		inventory = Bukkit.createInventory(null, rows);
	}
	
	private Inventory GUI(Player player) {
		Inventory mainMenu = Bukkit.createInventory(player, rows, inventory_name);
		EMCPlayer p = new EMCPlayer(player);

		inventory.setItem(1 - 1, EMCValue(p));
		inventory.setItem(9 - 1, backround(Material.BEDROCK)); //put sign here(placeholder)
		inventory.setItem(50 - 1, itemConverter());
		
		inventory.setItem(2 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(3 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(4 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(5 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(6 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(7 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(8 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(10 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(11 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(17 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(18 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(19 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(20 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(26 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(27 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(28 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(29 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(35 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(36 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(37 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(38 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(44 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(45 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(46 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(47 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(48 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(49 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(51 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(52 - 1, backround(Material.PURPLE_STAINED_GLASS_PANE));
		inventory.setItem(53 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		inventory.setItem(54 - 1, backround(Material.MAGENTA_STAINED_GLASS_PANE));
		
		reloadSlotsAlpha(p);
		fillSlots();
		
		mainMenu.setContents(inventory.getContents());
		return mainMenu;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase(inventory_name) && event.getWhoClicked() instanceof Player) {
			if (event.getCurrentItem() != null) {
				if (event.getClickedInventory() instanceof PlayerInventory && !isTome(event.getCurrentItem())) return;
				event.setCancelled(true);
			} else {
				if (!(event.getClickedInventory() instanceof PlayerInventory)) { //if broken add back in at start: event.getCursor().getType() != Material.AIR && 
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void claimItem(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase(inventory_name) && event.getWhoClicked() instanceof Player) {
				if (event.getCurrentItem() != null) {
					EMCPlayer player = new EMCPlayer((Player)event.getWhoClicked());
					if (!event.getCurrentItem().getItemMeta().hasCustomModelData() &&
							!(event.getClickedInventory() instanceof PlayerInventory)) {
						if (event.isLeftClick()) {
							if (player.getEMC() < player.getValue(event.getCurrentItem().getType())) return;
						} else if (event.isRightClick()) {
							if (player.getEMC() < player.getValue(event.getCurrentItem().getType()) * event.getCurrentItem().getMaxStackSize()) return;
						}
					if (!inventorySpaceFull((Player)event.getWhoClicked())) {
						if (event.isLeftClick()) {
							player.setEMC(player.getEMC() - TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + event.getCurrentItem().getType().name()));
							player.getPlayer().getInventory().addItem(new AddEMC(new ItemStack(event.getCurrentItem().getType(), 1)).setEMCToItem());
						} else if (event.isRightClick()) {
							player.setEMC(player.getEMC() - TerrificTransmutation.getPlugin().getConfig().getInt("EMCValue." + event.getCurrentItem().getType().name()) * event.getCurrentItem().getMaxStackSize());
							player.getPlayer().getInventory().addItem(new AddEMC(new ItemStack(event.getCurrentItem().getType(), event.getCurrentItem().getMaxStackSize())).setEMCToItem());
						}
						event.getView().setItem(1 - 1, EMCValue(player));
						player.getPlayer().updateInventory();
					} else {
						player.getPlayer().sendMessage(Strings.format("&cPlease clear space in your inventory!"));
					}
				}
			}
		}
	}
	
	@EventHandler
	public void convertItem(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase(inventory_name) && event.getWhoClicked() instanceof Player) {
				if (event.getRawSlot() == 50 - 1) {
					EMCPlayer player = new EMCPlayer((Player)event.getWhoClicked());
					if (player.getValue(event.getCursor().getType()) == -1) return;
					Database database = new Database(player.getPlayer().getUniqueId().toString());
					if (!database.getConfig().getStringList("UnlockedItems").isEmpty()) {
						if (!database.getConfig().getStringList("UnlockedItems").contains(event.getCursor().getType().name())) {
							List<String> list = database.getConfig().getStringList("UnlockedItems");
							list.add(event.getCursor().getType().name());
							player.setRawUnlockedMaterials(list);
							reloadSlotsBeta(player, event.getView());
						}
					} else {
						List<String> list = new ArrayList<String>();
						list.add(event.getCursor().getType().name());
						player.setRawUnlockedMaterials(list);
					}
					player.setEMC(player.getEMC() + player.getValue(event.getCursor().getType()) * event.getCursor().getAmount());
					event.getView().setItem(1 - 1, EMCValue(player));
					player.getPlayer().setItemOnCursor(new ItemStack(Material.AIR));
					player.getPlayer().updateInventory();
			}
		}
	}
	
	
	public boolean isTome(ItemStack one) {
		if (one.hasItemMeta()) {
			if (!one.getItemMeta().getPersistentDataContainer().has(getKey(), PersistentDataType.STRING)) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	private void reloadSlotsAlpha(EMCPlayer p) {
		if (p.getUnlockedMaterials() != null) {
			clearSlots();
			for (ItemStack item : p.getUnlockedItems()) {
				if (nextSlot() != -1) {
					inventory.setItem(nextSlot(), item);
				} else {
					break;
				}
			}
		}
	}
	
	private void reloadSlotsBeta(EMCPlayer p, InventoryView view) {
		if (p.getUnlockedMaterials() != null) {
			if (nextSlot() != -1) {
				view.setItem(nextSlot(), p.getUnlockedItems().get(p.getUnlockedItems().size() - 1));
				inventory.setItem(nextSlot(), p.getUnlockedItems().get(p.getUnlockedItems().size() - 1));
			}
		}
	}
	
	private void fillSlots() {
		int index = 0;
		for (ItemStack item : inventory.getContents()) {
			if (item == null) {
				inventory.setItem(index, backround(Material.BLACK_STAINED_GLASS_PANE));
			}
			index++;
		}
	}
	
	private int nextSlot() {
		if (inventory.getItem(12 - 1) == null || inventory.getItem(12 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 12 - 1;
		} else if (inventory.getItem(13 - 1) == null || inventory.getItem(13 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 13 - 1;
		} else if (inventory.getItem(14 - 1) == null || inventory.getItem(14 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 14 - 1;
		} else if (inventory.getItem(15 - 1) == null || inventory.getItem(15 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 15 - 1;
		} else if (inventory.getItem(16 - 1) == null || inventory.getItem(16 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 16 - 1;
		} else if (inventory.getItem(21 - 1) == null || inventory.getItem(21 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 21 - 1;
		} else if (inventory.getItem(22 - 1) == null || inventory.getItem(22 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 22 - 1;
		} else if (inventory.getItem(23 - 1) == null || inventory.getItem(23 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 23 - 1;
		} else if (inventory.getItem(24 - 1) == null || inventory.getItem(24 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 24 - 1;
		} else if (inventory.getItem(25 - 1) == null || inventory.getItem(25 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 25 - 1;
		} else if (inventory.getItem(30 - 1) == null || inventory.getItem(30 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 30 - 1;
		} else if (inventory.getItem(31 - 1) == null || inventory.getItem(31 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 31 - 1;
		} else if (inventory.getItem(32 - 1) == null || inventory.getItem(32 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 32 - 1;
		} else if (inventory.getItem(33 - 1) == null || inventory.getItem(33 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 33 - 1;
		} else if (inventory.getItem(34 - 1) == null || inventory.getItem(34 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 34 - 1;
		} else if (inventory.getItem(39 - 1) == null || inventory.getItem(39 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 39 - 1;
		} else if (inventory.getItem(40 - 1) == null || inventory.getItem(40 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 40 - 1;
		} else if (inventory.getItem(41 - 1) == null || inventory.getItem(41 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 41 - 1;
		} else if (inventory.getItem(42 - 1) == null || inventory.getItem(42 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 42 - 1;
		} else if (inventory.getItem(43 - 1) == null || inventory.getItem(43 - 1).getType() == Material.BLACK_STAINED_GLASS_PANE) {
			return 43 - 1;
		}
		return -1;
	}
	
	private void clearSlots() {
		if (inventory.getItem(12 - 1) != null && inventory.getItem(12 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(12 - 1, null);
		
		if (inventory.getItem(13 - 1) != null && inventory.getItem(13 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(13 - 1, null);
		
		if (inventory.getItem(14 - 1) != null && inventory.getItem(14 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(14 - 1, null);
		
		if (inventory.getItem(15 - 1) != null && inventory.getItem(15 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(15 - 1, null);
		
		if (inventory.getItem(16 - 1) != null && inventory.getItem(16 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(16 - 1, null);
		
		if (inventory.getItem(21 - 1) != null && inventory.getItem(21 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(21 - 1, null);
		
		if (inventory.getItem(22 - 1) != null && inventory.getItem(22 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(22 - 1, null);
		
		if (inventory.getItem(23 - 1) != null && inventory.getItem(23 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(23 - 1, null);
		
		if (inventory.getItem(24 - 1) != null && inventory.getItem(24 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(24 - 1, null);
		
		if (inventory.getItem(25 - 1) != null && inventory.getItem(25 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(25 - 1, null);
		
		if (inventory.getItem(30 - 1) != null && inventory.getItem(30 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(30 - 1, null);
		
		if (inventory.getItem(31 - 1) != null && inventory.getItem(31 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(31 - 1, null);
		
		if (inventory.getItem(32 - 1) != null && inventory.getItem(32 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(32 - 1, null);
		
		if (inventory.getItem(33 - 1) != null && inventory.getItem(33 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(33 - 1, null);
		
		if (inventory.getItem(34 - 1) != null && inventory.getItem(34 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(34 - 1, null);
		
		if (inventory.getItem(39 - 1) != null && inventory.getItem(39 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(39 - 1, null);
		
		if (inventory.getItem(40 - 1) != null && inventory.getItem(40 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(40 - 1, null);
		
		if (inventory.getItem(41 - 1) != null && inventory.getItem(41 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(41 - 1, null);
		
		if (inventory.getItem(42 - 1) != null && inventory.getItem(42 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(42 - 1, null);
		
		if (inventory.getItem(43 - 1) != null && inventory.getItem(43 - 1).getType() != Material.BLACK_STAINED_GLASS_PANE) inventory.setItem(43 - 1, null);
	}
}
