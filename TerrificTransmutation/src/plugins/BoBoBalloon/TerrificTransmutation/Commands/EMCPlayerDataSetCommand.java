package plugins.BoBoBalloon.TerrificTransmutation.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugins.BoBoBalloon.TerrificTransmutation.EMCPlayer;
import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class EMCPlayerDataSetCommand implements CommandExecutor {

	public EMCPlayerDataSetCommand() {
		TerrificTransmutation.getPlugin().getCommand("emcset").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("terrifictransmutation.command.emcset")) {
				if (args.length == 2) {
					EMCPlayer target = new EMCPlayer(Bukkit.getPlayer(args[0]));
					if (Bukkit.getServer().getPlayer(args[0]) != null) {
						try {
							int number = Integer.parseInt(args[1]);
							target.setEMC(number);
							player.sendMessage(Strings.format("&a " + target.getPlayer().getName() + "'s EMC is now: &c" + number));
							player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2F, 1F);
							return true;
						} catch (NumberFormatException error) {
							player.sendMessage(Strings.format("&r&cPlease enter a valid number!"));
							player.sendMessage(Strings.format("&r&c/emcset {player} {number}"));
						}
					}
				} else {
					player.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
					player.sendMessage(Strings.format("&r&c/emcset {player} {number}"));
				}
			} else {
				player.sendMessage(Strings.format("&r&cYou dont have sufficient permissions to access this command!"));
			}
		} else {
			if (args.length == 2) {
				EMCPlayer target = new EMCPlayer(Bukkit.getPlayer(args[0]));
				if (Bukkit.getServer().getPlayer(args[0]) != null) {
					try {
						int number = Integer.parseInt(args[1]);
						target.setEMC(number);
						sender.sendMessage(Strings.format("&a " + target.getPlayer().getName() + "'s EMC is now: &c" + number));
						return true;
					} catch (NumberFormatException error) {
						sender.sendMessage(Strings.format("&r&cPlease enter a valid number!"));
						sender.sendMessage(Strings.format("&r&c/emcset {player} {number}"));
					}
				}
			} else {
				sender.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
				sender.sendMessage(Strings.format("&r&c/emcset {player} {number}"));
			}
		}
		return false;
	}
}