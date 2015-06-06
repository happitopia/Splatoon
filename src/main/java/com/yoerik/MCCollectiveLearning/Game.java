package com.yoerik.MCCollectiveLearning;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Location;

public class Game {
	private final int id;
	private final Location spawn;
	private final List<UUID> players = new ArrayList<UUID>();
	private boolean isInGame = false;
	
	public Game(Location spawn, int id) {
		this.spawn = spawn;
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public List<UUID> getPlayers() {
		return this.players;
	}
	
	public Location getSpawn() {
		return spawn;
	}
	
	public boolean isInGame() {
		return isInGame;
	}
	
	public void startGame() {
		isInGame = true;
	}
	
	public void stopGame() {
		isInGame = false;
	}
}