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

public class EMCPlayerDataCommand implements CommandExecutor {

	public EMCPlayerDataCommand() {
		TerrificTransmutation.getPlugin().getCommand("emcdata").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("terrifictransmutation.command.emcdata")) {
				if (args.length == 1) {
					EMCPlayer target = new EMCPlayer(Bukkit.getPlayer(args[0]));
					if (Bukkit.getServer().getPlayer(args[0]) != null) {
						player.sendMessage(Strings.format("&a" + target.getPlayer().getName() + "'s EMC equals: &c" + target.getEMC()));
						player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2F, 1F);
						return true;
					}
				} else {
					player.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
					player.sendMessage(Strings.format("&r&c/emcdata {player}"));
				}
			} else {
				player.sendMessage(Strings.format("&r&cYou dont have sufficient permissions to access this command!"));
			}
		} else {
			if (args.length == 1) {
				EMCPlayer target = new EMCPlayer(Bukkit.getPlayer(args[0]));
				if (Bukkit.getServer().getPlayer(args[0]) != null) {
					sender.sendMessage(Strings.format("&a" + target.getPlayer().getName() + "'s EMC equals: &c" + target.getEMC()));
					return true;
				}
			} else {
				sender.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
				sender.sendMessage(Strings.format("&r&c/emcdata {player}"));
			}
		}
		return false;
	}
}