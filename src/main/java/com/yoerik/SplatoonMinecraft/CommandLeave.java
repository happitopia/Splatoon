package com.yoerik.SplatoonMinecraft;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLeave extends CLCommand {
	@Override
	public boolean execute(MCCollectiveLearning plugin, CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		plugin.gameManager.removePlayer(p);
		p.sendMessage("You have now left the game");
		return true;
	}
	
	@Override
	public String printHelp() {
		return "Leave game";
	}
}
