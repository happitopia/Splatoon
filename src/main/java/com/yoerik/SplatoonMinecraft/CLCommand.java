package com.yoerik.SplatoonMinecraft;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CLCommand {
	public boolean execute(MCCollectiveLearning plugin, CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		p.sendMessage("Todo");
		return true;
	}
	
	public String printHelp() {
		return "Default description";
	}
}
