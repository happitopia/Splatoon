package com.yoerik.SplatoonMinecraft;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

public final class Splatoon extends JavaPlugin {
	Connection conn = null;
	File configFile;
	public FileConfiguration config;
	public PlayerChatListener playerListener = new PlayerChatListener(this);
	public ToolManager toolManager = new ToolManager(this);
	public CommandListener commandListener = new CommandListener(this);
	public GameManager gameManager = new GameManager(this);
	
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
		pm.registerEvents(toolManager, this);
		pm.registerEvents(playerListener, this);
		getCommand("sp").setExecutor(commandListener);
		gameManager.load();
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new DayNightCycle(), 0L, 40L);
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
}
