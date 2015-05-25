package com.yoerik.MCCollectiveLearning;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCCollectiveLearning extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
	}
 
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cl")) { // If the player typed /basic then do the following...
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				if (args.length == 0) {
					Player player = (Player) sender;
					sender.sendMessage("Usage: /cl help");
				}
				else if (args[0] == "help") {
					sender.sendMessage("&aCollective Learning Help:");
					sender.sendMessage("&f  /cl help    Display help");
					sender.sendMessage("&f  /cl manage  Manage player");
				}
			}
			return true;
		}
		return false;
	}
}