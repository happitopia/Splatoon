package com.yoerik.SplatoonMinecraft;


import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetLobby extends SPCommand {
	@Override
	public boolean execute(Splatoon plugin, CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		plugin.gameManager.setLobby(p.getLocation());
		p.sendMessage("Lobby Location set");
		return true;
	}
	
	@Override
	public String printHelp() {
		return "Set lobby location";
	}
}
