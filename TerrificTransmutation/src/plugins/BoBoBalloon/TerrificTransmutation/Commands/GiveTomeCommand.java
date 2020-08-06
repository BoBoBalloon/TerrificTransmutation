package plugins.BoBoBalloon.TerrificTransmutation.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Items.Tome;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class GiveTomeCommand implements CommandExecutor {
	
	public GiveTomeCommand() {
		TerrificTransmutation.getPlugin().getCommand("givetome").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("terrifictransmutation.command.givetome")) {
				if (args.length == 1) {
					try {
						Player target = Bukkit.getPlayer(args[0]);
						player.sendMessage(Strings.format("&aYou gave " + target.getName() + " a &dmagical tome&a!"));
						target.sendMessage(Strings.format("&aYou were given a &dmagical tome&a by " + player.getName() + "!"));
						target.getInventory().addItem(Tome.tome());
						return true;
					} catch (NullPointerException error) {
						sender.sendMessage(Strings.format("&r&cPlease enter a valid player!"));
					}
				} else if (args.length == 2) {
					if (args[1].equalsIgnoreCase("-s")) {
						try {
							Player target = Bukkit.getPlayer(args[0]);
							player.sendMessage(Strings.format("&aYou silently gave " + target.getName() + " a &dmagical tome&a!"));
							target.getInventory().addItem(Tome.tome());
							return true;
						} catch (NullPointerException error) {
							sender.sendMessage(Strings.format("&r&cPlease enter a valid player!"));
						}
					} else {
						player.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
						player.sendMessage(Strings.format("&r&c/givetome {player} {null/-s}"));
					}
				} else {
					player.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
					player.sendMessage(Strings.format("&r&c/givetome {player} {null/-s}"));
				}
			} else {
				player.sendMessage(Strings.format("&r&cYou dont have sufficient permissions to access this command!"));
			}
		} else {
			if (args.length == 1) {
				try {
					Player target = Bukkit.getPlayer(args[0]);
					sender.sendMessage(Strings.format("&aYou gave " + target.getName() + " a &dmagical tome&a!"));
					target.sendMessage(Strings.format("&aYou were given a &dmagical tome&a by Console!"));
					target.getInventory().addItem(Tome.tome());
					return true;
				} catch (NullPointerException error) {
					sender.sendMessage(Strings.format("&r&cPlease enter a valid player!"));
				}
			} else if (args.length == 2) {
				if (args[1].equalsIgnoreCase("-s")) {
					try {
						Player target = Bukkit.getPlayer(args[0]);
						sender.sendMessage(Strings.format("&aYou silently gave " + target.getName() + " a &dmagical tome&a!"));
						target.getInventory().addItem(Tome.tome());
						return true;
					} catch (NullPointerException error) {
						sender.sendMessage(Strings.format("&r&cPlease enter a valid player!"));
					}
				} else {
					sender.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
					sender.sendMessage(Strings.format("&r&c/givetome {player} {null/-s}"));
				}
			} else {
				sender.sendMessage(Strings.format("&r&cYou have used improper arguments to execute this command!"));
				sender.sendMessage(Strings.format("&r&c/givetome {player} {null/-s}"));
			}
		}
		return false;
	}
	
}