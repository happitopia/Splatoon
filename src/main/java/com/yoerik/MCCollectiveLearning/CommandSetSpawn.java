package com.yoerik.MCCollectiveLearning;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSpawn extends CLCommand {
	@Override
	public boolean execute(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		GameManager.getManager().setSpawn(p.getLocation());
		p.sendMessage("Spawn Location set");
		return true;
	}
}
