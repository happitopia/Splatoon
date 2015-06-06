package com.yoerik.MCCollectiveLearning;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {
	public CommandListener(MCCollectiveLearning mcCollectiveLearning) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cl")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				Player p = (Player) sender;
				if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
					sender.sendMessage(ChatColor.AQUA + "Collective Learning Help:");
					sender.sendMessage(ChatColor.BLUE + "  /cl help    Display help");
					sender.sendMessage(ChatColor.BLUE + "  /cl create  create game");
					sender.sendMessage(ChatColor.BLUE + "  /cl join    join game");
					sender.sendMessage(ChatColor.BLUE + "  /cl leave   leave game");
					sender.sendMessage(ChatColor.BLUE + "  /cl end     end game");
					sender.sendMessage(ChatColor.BLUE + "  /cl manage  Manage player");
				} else if (args[0].equalsIgnoreCase("create")) {
					// Create game
					GameManager.getManager().createGame(p.getLocation());
				} else if (args[0].equalsIgnoreCase("join")) {
					if (args.length == 2) {
						// Join game
						int num = 0;
						try {
							num = Integer.parseInt(args[1]);
						}
						catch (NumberFormatException e) {
							p.sendMessage("Invalid game ID");
						}
						GameManager.getManager().addPlayer(p, num);
						p.sendMessage("You have now joined the game.");
					} else {
						p.sendMessage("Usage: /cl join <id>");
					}
				} else if (args[0].equalsIgnoreCase("leave")) {
					// Leave Game
					GameManager.getManager().removePlayer(p);
					p.sendMessage("You have left the game.");
				} else if (args[0].equalsIgnoreCase("manage")) {
					// TODO: Manage player(s)
				} else if (args[0].equalsIgnoreCase("end")) {
					// TODO: End game
				} else if (args[0].equalsIgnoreCase("kick")) {
					// TODO: Kick player from game
				} else {
					sender.sendMessage(ChatColor.RED + "unrecognized command: " + args[0]);
				}
			}
			return true;
		}
		return false;
	}
}
