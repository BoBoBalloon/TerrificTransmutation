package plugins.BoBoBalloon.TerrificTransmutation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.Lists;

import plugins.BoBoBalloon.TerrificTransmutation.Database.Database;
import plugins.BoBoBalloon.TerrificTransmutation.Items.Tome;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.SignMenuFactory;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class EMCMenu implements Listener {

	private static Map<UUID, Inventory> inventoryList;
	private static final String inventory_name = Strings.format("&r&dTransmutation");
	private static SignMenuFactory signGUI;
	
	public EMCMenu() {
		inventoryList = new HashMap<>();
		
		signGUI = new SignMenuFactory(TerrificTransmutation.getPlugin());
		
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	public static Map<UUID, Inventory> getInventories() {
		return inventoryList;
	}
	
	private void addInventory(Player player, Inventory inventory) {
		inventoryList.put(player.getUniqueId(), inventory);
	}
	
	private void removeInventory(Player player) {
		if (containsInventory(player)) inventoryList.remove(player.getUniqueId());
	}
	
	private static boolean containsInventory(Player player) {
		if (inventoryList.containsKey(player.getUniqueId())) return true;
		return false;	
	}
	
	public static void openInventory(Player player) {
		if (containsInventory(player)) {
			Inventory inventory = inventoryList.get(player.getUniqueId());
			setContents(player, inventory);
			player.openInventory(inventory);
		} else {
			player.openInventory(createInventory(player));
		}
	}
	/*
	public static void openInventorySearch(Player player, String string) {
		if (!containsInventory(player)) return;
			Inventory inventory = inventoryList.get(player.getUniqueId());
			
			player.openInventory(inventory); //if still broken put back at the bottem
			
			inventory.setItem(9 - 1, itemSearch(string));
			reloadSlotsSearch(new EMCPlayer(player), inventory, string);
			fillSlots(inventory);
	}
	*/
	
	private static Inventory createInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 54, inventory_name);
		
		setContents(player, inventory);
		
		return inventory;
	}
	
	private static void setContents(Player player, Inventory inventory) {
		EMCPlayer p = new EMCPlayer(player);
		
		inventory.setItem(1 - 1, EMCValue(p));
		inventory.setItem(9 - 1, itemSearch(null));
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
		
		reloadSlots(p, inventory);
		fillSlots(inventory);
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase(inventory_name)) addInventory((Player)event.getPlayer(), event.getInventory());
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		removeInventory((Player)event.getPlayer());
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase(inventory_name) && event.getWhoClicked() instanceof Player) {
			if (event.getCurrentItem() != null) {
				if (event.getClickedInventory() instanceof PlayerInventory && !Tome.isTome(event.getCurrentItem())) return;
				event.setCancelled(true);
			} else {
				if (!(event.getClickedInventory() instanceof PlayerInventory)) {
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
						//event.getView().setItem(1 - 1, EMCValue(player));
						event.getClickedInventory().setItem(1 - 1, EMCValue(player));
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
					if (!database.getConfig().getStringList("UnlockedItems").contains(event.getCursor().getType().name())) {
						List<String> list = database.getConfig().getStringList("UnlockedItems");
						list.add(event.getCursor().getType().name());
						player.setRawUnlockedMaterials(list);
						//event.getView().setItem(9 - 1, itemSearch(null));
						event.getClickedInventory().setItem(9 - 1, itemSearch(null));
						reloadSlots(player, event.getClickedInventory());
					}
					player.setEMC(player.getEMC() + player.getValue(event.getCursor().getType()) * event.getCursor().getAmount());
					//event.getView().setItem(1 - 1, EMCValue(player));
					event.getClickedInventory().setItem(1 - 1, EMCValue(player));
					player.getPlayer().setItemOnCursor(new ItemStack(Material.AIR));
					player.getPlayer().updateInventory();
			}
		}
	}
	
	@EventHandler
	public void searchItem(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase(inventory_name) && event.getWhoClicked() instanceof Player) {
				if (event.getRawSlot() == 9 - 1) {
					EMCPlayer emcPlayer = new EMCPlayer((Player)event.getWhoClicked());
					emcPlayer.getPlayer().closeInventory();
		
					signGUI
		             .newMenu(Lists.newArrayList("&r", "&r^^^^^^^^^^^^^^^", "&rPlease imput the", "&rname of an item!"))
		             .reopenIfFail()
		             .response((player, lines) -> {
		            	new SearchTask(player, lines[0]).runTask(TerrificTransmutation.getPlugin());
		                return true;
		             })
		             .open(emcPlayer.getPlayer());
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
	
	private static ItemStack backround(Material mat) {
		ItemStack item = new ItemStack(mat, 1);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(Strings.format("&r"));
		
		meta.setCustomModelData(69);
		
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack EMCValue(EMCPlayer player) {
		ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(Strings.format("&aYour EMC is &c" + player.getEMC()));
		
		meta.setCustomModelData(69);
		
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack itemConverter() {
		ItemStack item = new ItemStack(Material.HOPPER, 1);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(Strings.format("&eEMC Converter"));
		
		meta.setCustomModelData(69);
		
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack itemSearch(String string) {
		ItemStack item = new ItemStack(Material.OAK_SIGN, 1);
		ItemMeta meta = item.getItemMeta();
		
		if (string != null) {
			meta.setDisplayName(Strings.format("&rSearch: " + string));
		} else {
			meta.setDisplayName(Strings.format("&rSearch:"));
		}
		
		
		meta.setCustomModelData(69);
		
		item.setItemMeta(meta);
		return item;
	}
	
	private static void reloadSlots(EMCPlayer p, Inventory inventory) {
		if (p.getUnlockedMaterials() != null) {
			clearSlots(inventory);
			for (ItemStack item : p.getUnlockedItems()) {
				if (nextSlot(inventory) != -1) {
					inventory.setItem(nextSlot(inventory), item);
				} else {
					break;
				}
			}
		}
	}
	
	public static void reloadSlotsSearch(EMCPlayer p, Inventory inventory, String string) {
		if (p.searchItems(string) != null) {
			clearSlots(inventory);
			for (ItemStack item : p.searchItems(string)) {
				if (nextSlot(inventory) != -1) {
					inventory.setItem(nextSlot(inventory), item);
				} else {
					break;
				}
			}
		}
	}
	
	public static void fillSlots(Inventory inventory) {
		int index = 0;
		for (ItemStack item : inventory.getContents()) {
			if (item == null) {
				inventory.setItem(index, backround(Material.BLACK_STAINED_GLASS_PANE));
			}
			index++;
		}
	}
	
	private static int nextSlot(Inventory inventory) {
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
	
	private static void clearSlots(Inventory inventory) {
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

class SearchTask extends BukkitRunnable {

	private Player player;
	private String output;
	
	public SearchTask(Player player, String output) {
		this.player = player;
		this.output = output;
	}
	
	@Override
	public void run() {
		Inventory inventory = EMCMenu.getInventories().get(player.getUniqueId());
			
 		inventory.setItem(9 - 1, EMCMenu.itemSearch(output));
 		EMCMenu.reloadSlotsSearch(new EMCPlayer(player), inventory, output);
 		EMCMenu.fillSlots(inventory);
 			
 		player.openInventory(inventory); //error here
	}
	
}
