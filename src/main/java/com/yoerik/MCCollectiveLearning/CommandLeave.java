package com.yoerik.MCCollectiveLearning;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLeave extends CLCommand {
	@Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		GameManager.getManager().removePlayer(p);
		p.sendMessage("You have now left the game");
		return true;
	}
}
