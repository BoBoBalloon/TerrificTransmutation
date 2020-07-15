package plugins.BoBoBalloon.TerrificTransmutation.Utils;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

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
}
