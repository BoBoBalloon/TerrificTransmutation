package plugins.BoBoBalloon.TerrificTransmutation.Commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugins.BoBoBalloon.TerrificTransmutation.EMCMenu;
import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class GetTomeCommand implements CommandExecutor {
	
	public GetTomeCommand() {
		TerrificTransmutation.getPlugin().getCommand("gettome").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("terrifictransmutation.command.gettome")) {
				player.getInventory().addItem(EMCMenu.tome());
				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2F, 1F);
				return true;
			} else {
				player.sendMessage(Strings.format("&r&cYou dont have sufficient permissions to access this command!"));
			}
		} else {
			sender.sendMessage(Strings.format("&r&cOnly players can use this command!"));
		}
		return false;
	}
}