package com.yoerik.MCCollectiveLearning;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCCollectiveLearning extends JavaPlugin {
	Connection conn = null;
	File configFile;
	File groupsFile;
	File historyFile;
	FileConfiguration config;
	FileConfiguration groups;
	FileConfiguration history;
	public final PlayerChatListener playerListener = new PlayerChatListener(this);
	
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void firstRun() throws Exception {
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			copy(getResource("config.yml"), configFile);
		}
	}
	
	public void saveYamls() {
		try {
			config.save(configFile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadYamls() {
		try {
			config.load(configFile);
			getLogger().info("Config file version: '" + config.get("ConfigVersion") + "'");
			if (!config.get("ConfigVersion").toString().equals("0.1")) {
				getLogger().warning("Old config version found... Updating.");
				copy(getResource("config.yml"), configFile);
				loadYamls();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		getLogger().info("onEnable has been invoked!");
		configFile = new File(getDataFolder(), "config.yml");
		try {
			firstRun();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		config = new YamlConfiguration();
		loadYamls();
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + config.get("mysql.host") + "/" + config.get("mysql.database") + "?" + "user=" + config.get("mysql.database") + "&password=" + config.get("mysql.password"));
		}
		catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		pm.registerEvents(this.playerListener, this);
	}
	
	public void startGame() {
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			if (player.hasPermission("send.receive.message")) {
				player.sendMessage("Game starting");
			}
		}
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
		saveYamls();
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
					} else {
						p.sendMessage("Usage: /cl join <id>");
					}
				} else if (args[0].equalsIgnoreCase("leave")) {
					// Leave Game
					GameManager.getManager().removePlayer(p);
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
	
	//@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
	/*public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		String msg = event.getMessage();
		if (msg == null) return;
		String resultMsg = ChatOverride.restrictMessage(msg);
		player.sendMessage(resultMsg);
	}*/
}
