package com.yoerik.MCCollectiveLearning;


import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {
	MCCollectiveLearning plugin;
	static HashMap<String, CLCommand> commands = new HashMap<String, CLCommand>();
	static {
		commands.put("join", new CommandJoin());
		commands.put("leave", new CommandLeave());
		commands.put("setspawn", new CommandSetSpawn());
		commands.put("setlobby", new CommandSetLobby());
	}
	
	public CommandListener(MCCollectiveLearning plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("cl")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			} else {
				if (args.length > 0) {
					if (commands.containsKey(args[0].toLowerCase())) {
						CLCommand command = commands.get(args[0]);
						return command.execute(plugin, sender, cmd, label, args);
					} else if (args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.AQUA + "Collective Learning Commands:");
						System.out.println(commands.keySet().toString());
						for (String command : commands.keySet()) {
							sender.sendMessage(ChatColor.YELLOW + command + " " + ChatColor.RED + commands.get(command).printHelp());
						}
					} else {
						sender.sendMessage("Help: /cl help");
						return true;
					}
				} else if (args.length == 0) {
					sender.sendMessage("Help: /cl help");
				}
				return true;
			}
		}
		return false;
	}
}
