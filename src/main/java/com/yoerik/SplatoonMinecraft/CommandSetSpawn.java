package com.yoerik.SplatoonMinecraft;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSpawn extends CLCommand {
	@Override
	public boolean execute(MCCollectiveLearning plugin, CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		plugin.gameManager.setSpawn(p.getLocation());
		p.sendMessage("Spawn Location set");
		return true;
	}
	
	@Override
	public String printHelp() {
		return "Set spawn location";
	}
}
