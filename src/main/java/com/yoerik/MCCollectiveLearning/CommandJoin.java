package com.yoerik.MCCollectiveLearning;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandJoin extends CLCommand {
	@Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (GameManager.getManager().addPlayer(p)) {
			p.sendMessage("You have now joined the game.");
		} else {
			p.sendMessage("Error joining game...");
		}
		return true;
	}
}
