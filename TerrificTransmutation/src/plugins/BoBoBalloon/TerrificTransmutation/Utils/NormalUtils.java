package plugins.BoBoBalloon.TerrificTransmutation.Utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class NormalUtils {
	
	/**
	 * Method by
	 *
	 * @author xigsag, SBPrime
	 */
	@SuppressWarnings("deprecation")
	public static ItemStack getHead(String base64Value) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        UUID hashAsId = new UUID(base64Value.hashCode(), base64Value.hashCode());
        return Bukkit.getUnsafe().modifyItemStack(skull, "{SkullOwner:{Id:\"" + hashAsId + "\",Properties:{textures:[{Value:\"" + base64Value + "\"}]}}}");
    }
	
	public static boolean isNormal(ItemStack item) {
		
		if (item.hasItemMeta()) {
			if (item.getItemMeta().hasLore()) return false;
			if (item.getItemMeta().hasCustomModelData()) return false;
			if (item.getItemMeta().hasEnchants()) return false;
			if (hasItemFlags(item.getItemMeta())) return false;
			if (isDamaged(item.getItemMeta())) return false;
		}
		
		return true;
	}
	
	public static boolean hasItemFlags(ItemMeta meta) {
		for (ItemFlag flag : ItemFlag.values()) {
			if (meta.hasItemFlag(flag)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean isDamaged(ItemMeta meta) {
		if (meta instanceof Damageable) {
			if (((Damageable)meta).hasDamage()) return true;
		}
		
		return false;
	}
}
