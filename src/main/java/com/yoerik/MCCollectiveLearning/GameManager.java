package com.yoerik.MCCollectiveLearning;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GameManager {
	private static GameManager gm;
	// Player data
	private final Map<UUID, Location> locs = new HashMap<UUID, Location>();
	private final Map<UUID, ItemStack[]> inv = new HashMap<UUID, ItemStack[]>();
	private final Map<UUID, ItemStack[]> armor = new HashMap<UUID, ItemStack[]>();
	private final List<Game> games = new ArrayList<Game>();
	private int gameSize = 0;
	
	private GameManager() {} // Prevent instantiation
	
	// Singleton accessor with lazy initialization
	public static GameManager getManager() {
		if (gm == null) gm = new GameManager();
		return gm;
	}
	
	/**
	 * Acquires a game based on its ID number
	 *
	 * @param i
	 *        the ID to search the games for
	 * @return the game possessing the specified ID
	 */
	public Game getGame(int i) {
		for (Game g : games) {
			if (g.getId() == i) {
				return g;
			}
		}
		return null; // Not found
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
	 */
	public void addPlayer(Player p, int i) {
		Game g = getGame(i);
		if (g == null) {
			p.sendMessage("Invalid game!");
			return;
		}
		if (isInGame(p)) {
			p.sendMessage("Cannot join more than 1 game!");
			return;
		}
		// Adds the player to the game player list
		g.getPlayers().add(p.getUniqueId());
		// Save the inventory and armor
		inv.put(p.getUniqueId(), p.getInventory().getContents());
		armor.put(p.getUniqueId(), p.getInventory().getArmorContents());
		// Clear inventory and armor
		p.getInventory().setArmorContents(null);
		p.getInventory().clear();
		// Makes player use adventure
		p.setGameMode(GameMode.ADVENTURE);
		// Save the players's last location before joining,
		// then teleporting them to the game spawn
		locs.put(p.getUniqueId(), p.getLocation());
		p.teleport(g.getSpawn());
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
		Game g = null;
		// Searches each game for the player
		for (Game game : games) {
			if (game.getPlayers().contains(p.getUniqueId())) g = game;
		}
		// Check game validity
		if (g == null) {
			p.sendMessage("Invalid operation!");
			return;
		}
		// Remove from game player lost
		g.getPlayers().remove(p.getName());
		// Remove inventory acquired during the game
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
		// Restore inventory and armor
		if (inv.containsKey(p.getName())) {
			p.getInventory().setContents(inv.get(p.getName()));
		} else {
			p.getInventory().clear();
		}
		if (armor.containsKey(p.getName())) {
			p.getInventory().setArmorContents(armor.get(p.getName()));
		} else {
			p.getInventory().setArmorContents(null);
		}
		// Remove player data entries
		inv.remove(p.getUniqueId());
		armor.remove(p.getUniqueId());
		// Teleport to original location, remove it too
		p.setGameMode(GameMode.ADVENTURE);
		p.teleport(locs.get(p.getUniqueId()));
		locs.remove(p.getUniqueId());
		p.setFireTicks(0);
	}
	
	/**
	 * Creates an game at the specified location
	 *
	 * @param l
	 *        the location for game spawn
	 * @return the game created
	 */
	public Game createGame(Location l) {
		gameSize++;
		Game g = new Game(l, gameSize);
		games.add(g);
		return g;
	}
	
	/**
	 * Checks if the player is currently in an game
	 *
	 * @param p
	 *        the player to check
	 * @return {@code true} if the player is in game
	 */
	public boolean isInGame(Player p) {
		for (Game g : games) {
			if (g.getPlayers().contains(p.getUniqueId())) return true;
		}
		return false;
	}
	
	// UTILITY METHODS
	public String serializeLoc(Location l) {
		return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
	}
	
	public Location deserializeLoc(String s) {
		String[] st = s.split(",");
		return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
	}
}