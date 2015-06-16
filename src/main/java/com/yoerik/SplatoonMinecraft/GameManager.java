package com.yoerik.SplatoonMinecraft;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameManager {
	private static Splatoon plugin;
	// Player data
	private Map<UUID, Location> locs = new HashMap<UUID, Location>();
	private Map<UUID, ItemStack[]> inv = new HashMap<UUID, ItemStack[]>();
	private Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();
	private final Game game = new Game();
	
	public GameManager(Splatoon p) {
		plugin = p;
	}
	
	public void load() {
		game.setSpawn(loadLocation("spawnLocation"));
		game.setLobby(loadLocation("lobbyLocation"));
	}
	
	private Location loadLocation(String prefix) {
		try {
			World world = plugin.getServer().getWorld((String) plugin.config.get(prefix + ".world"));
			Double x = Double.parseDouble(plugin.config.get(prefix + ".x").toString());
			Double y = Double.parseDouble((String) plugin.config.get(prefix + ".y").toString());
			Double z = Double.parseDouble((String) plugin.config.get(prefix + ".z").toString());
			float pitch = Float.parseFloat((String) plugin.config.get(prefix + ".pitch").toString());
			float yaw = Float.parseFloat((String) plugin.config.get(prefix + ".yaw").toString());
			return new Location(world, x, y, z, yaw, pitch);
		}
		catch (NullPointerException e) {
			System.out.println("Error loading location");
			e.printStackTrace();
			return plugin.getServer().getWorld("world").getSpawnLocation();
		}
	}
	
	private void saveLocation(String prefix, Location location) {
		plugin.config.set(prefix + ".world", location.getWorld().getName());
		plugin.config.set(prefix + ".x", location.getX());
		plugin.config.set(prefix + ".y", location.getY());
		plugin.config.set(prefix + ".z", location.getZ());
		plugin.config.set(prefix + ".pitch", location.getPitch());
		plugin.config.set(prefix + ".yaw", location.getYaw());
	}
	
	/**
	 * Adds the player to a game
	 *
	 * <p>
	 * Gets the game by ID, checks that it exists, and check the player isn't already in a game.
	 * </p>
	 *
	 * @param p
	 *        the player to add
	 * @param i
	 *        the game ID. A check will be done to ensure its validity.
	 * @return
	 */
	public boolean addPlayer(Player p) {
		if (isInGame(p)) {
			p.sendMessage("Cannot join a game you are already in");
			return false;
		}
		// Adds the player to the game player list
		game.addPlayer(p);
		// Save the inventory and armor
		inv.put(p.getUniqueId(), p.getInventory().getContents());
		armor.put(p.getUniqueId(), p.getInventory().getArmorContents());
		// Clear inventory and armor
		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		// Make player use survival
		p.setGameMode(GameMode.SURVIVAL);
		// Save the players's last location before joining,
		// then teleporting them to the game spawn
		locs.put(p.getUniqueId(), p.getLocation());
		p.teleport(game.getSpawn());
		return true;
	}
	
	/**
	 * Removes the player from their current game.
	 *
	 * <p>
	 * The player is allowed to not be in game, a check will be performed to ensure the validity of the game
	 * </p>
	 *
	 * @param p
	 *        the player to remove from the game
	 */
	public void removePlayer(Player p) {
		// Remove from game player lost
		game.removePlayer(p);
		// Remove inventory acquired during the game
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		// Restore inventory and armor
		if (inv.containsKey(p.getUniqueId())) {
			p.getInventory().setContents(inv.get(p.getUniqueId()));
		} else {
			p.getInventory().clear();
		}
		if (armor.containsKey(p.getUniqueId())) {
			p.getInventory().setArmorContents(armor.get(p.getUniqueId()));
		} else {
			p.getInventory().setArmorContents(null);
		}
		// Remove player data entries
		inv.remove(p.getUniqueId());
		armor.remove(p.getUniqueId());
		// Teleport to original location, remove it too
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(game.getLobby());
		locs.remove(p.getUniqueId());
		p.setFireTicks(0);
	}
	
	/**
	 * Checks if the player is currently in an game
	 *
	 * @param p
	 *        the player to check
	 * @return {@code true} if the player is in game
	 */
	public boolean isInGame(Player p) {
		return game.getPlayers().contains(p.getUniqueId());
	}
	
	public void setSpawn(Location location) {
		game.setSpawn(location);
		saveLocation("spawnLocation", location);
	}
	
	public void setLobby(Location location) {
		game.setLobby(location);
		saveLocation("lobbyLocation", location);
	}
}