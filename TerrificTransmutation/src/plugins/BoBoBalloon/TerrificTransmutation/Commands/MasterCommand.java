package plugins.BoBoBalloon.TerrificTransmutation.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class MasterCommand implements CommandExecutor {
	
	public MasterCommand() {
		TerrificTransmutation.getPlugin().getCommand("terrifictransmutation").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage(Strings.format("&r&5------------------------"));
			sender.sendMessage(Strings.format("&r&dTerrific Transmutation"));
			sender.sendMessage("");
			sender.sendMessage(Strings.format("&r&7-&r&a /gettome - get a Transmutation Tome"));
			sender.sendMessage(Strings.format("&r&5------------------------"));
			return true;
		} else if (args.length == 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args[0].equalsIgnoreCase("reload")) {
					if (player.hasPermission("terrifictransmutation.command.reload")) {
						TerrificTransmutation.getPlugin().reloadConfig();
						player.sendMessage(Strings.format("&aReload complete!"));
						return true;
					} else {
						player.sendMessage(Strings.format("&r&cYou dont have sufficient permissions to access this command!"));
					}
				} else {
					player.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
					player.sendMessage(Strings.format("&r&c/terrifictransmutation {action/null}"));
				}
			} else {
				if (args[0].equalsIgnoreCase("reload")) {
					TerrificTransmutation.getPlugin().reloadConfig();
					sender.sendMessage(Strings.format("&aReload complete!"));
					return true;
				} else {
					sender.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
					sender.sendMessage(Strings.format("&r&c/terrifictransmutation {action/null}"));
				}
			}
		}
		return false;
	}
}