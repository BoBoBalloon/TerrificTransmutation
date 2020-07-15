package plugins.BoBoBalloon.TerrificTransmutation.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import plugins.BoBoBalloon.TerrificTransmutation.TerrificTransmutation;
import plugins.BoBoBalloon.TerrificTransmutation.Utils.Strings;

public class MasterCommand implements CommandExecutor {
	
	public MasterCommand() {
		TerrificTransmutation.getPlugin().getCommand("terrifictransmutation").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(Strings.format("&r&5------------------------"));
		sender.sendMessage(Strings.format("&r&dTerrific Transmutation"));
		sender.sendMessage("");
		sender.sendMessage(Strings.format("&r&7-&r&a /gettome - get a Transmutation Tome"));
		sender.sendMessage(Strings.format("&r&5------------------------"));
		return true;
	}
}