package com.yoerik.MCCollectiveLearning;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CLCommand {
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		p.sendMessage("Todo");
		return true;
	}
	
	public String printHelp() {
		return "Default description";
	}
}
