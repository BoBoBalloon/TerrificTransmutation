package plugins.BoBoBalloon.TerrificTransmutation.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.NormalUtils;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class Tome implements Listener {
	
	private static NamespacedKey tomeKey = new NamespacedKey(TerrificTransmutation.getPlugin(), "isTome");
	
	public Tome() {
		TerrificTransmutation.getPlugin().getServer().getPluginManager().registerEvents(this, TerrificTransmutation.getPlugin());
	}
	
	@EventHandler
	public void tomeOpen(PlayerInteractEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand() != null && 
				isTome(event.getPlayer().getInventory().getItemInMainHand())) {
			if (event.getPlayer().hasPermission("terrifictransmutation.usetome")) {
				EMCMenu.openInventory(event.getPlayer());
			}
			event.setCancelled(true);
		}
	}
	
	public static ItemStack tome() {
		ItemStack tome = NormalUtils.getHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2VlYTM0NTkwOGQxN2RjNDQ5NjdkMWRjZTQyOGYyMmYyYjE5Mzk3MzcwYWJlYjc3YmRjMTJlMmRkMWNiNiJ9fX0=");
		ItemMeta meta = tome.getItemMeta();
		NamespacedKey randomKey = new NamespacedKey(TerrificTransmutation.getPlugin(), "randomValue");
		String itemName = Strings.format(changeColorCodes(TerrificTransmutation.getPlugin().getConfig().getString("TransmutationTomeName")));
		
		meta.setDisplayName(itemName);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(Strings.format("&rClick this item to open your " + itemName));
		meta.setLore(lore);
		
		meta.getPersistentDataContainer().set(tomeKey, PersistentDataType.STRING, "true");
		meta.getPersistentDataContainer().set(randomKey, PersistentDataType.STRING, Strings.randomString());
		
		
		tome.setItemMeta(meta);
		return tome;
	}
	
	public static boolean isTome(ItemStack one) {
		if (one.hasItemMeta()) {
			if (!one.getItemMeta().getPersistentDataContainer().has(tomeKey, PersistentDataType.STRING)) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	private static String changeColorCodes(String string) {
		return string.replace("(DARKRED)", "&4")
				.replace("(RED)", "&c")
				.replace("(GOLD)", "&6")
				.replace("(YELLOW)", "&e")
				.replace("(DARKGREEN)", "&2")
				.replace("(GREEN)", "&a")
				.replace("(AQUA)", "&b")
				.replace("(DARKAQUA)", "&3")
				.replace("(DARKBLUE)", "&1")
				.replace("(BLUE)", "&9")
				.replace("(LIGHTPURPLE)", "&d")
				.replace("(DARKPURPLE)", "&5")
				.replace("(WHITE)", "&f")
				.replace("(GRAY)", "&7")
				.replace("(DARK_GRAY)", "&8")
				.replace("(BLACK)", "&0")
				.replace("(RESET)", "&r")
				.replace("(BOLD)", "&l")
				.replace("(ITALIC)", "&o")
				.replace("(UNDERLINE)", "&n")
				.replace("(STRIKE)", "&m")
				.replace("(RANDOM)", "&k");
	}
}
