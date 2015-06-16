package com.yoerik.SplatoonMinecraft;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Game {
	private int id;
	private Location spawn;
	private Location lobby;
	private List<UUID> players = new ArrayList<UUID>();
	private boolean isInGame = false;
	
	public int getId() {
		return this.id;
	}
	
	public List<UUID> getPlayers() {
		return this.players;
	}
	
	public void setSpawn(Location loc) {
		spawn = loc;
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
	
	public void setLobby(Location loc) {
		lobby = loc;
	}
	
	public Location getLobby() {
		return lobby;
	}
	
	public void removePlayer(Player p) {
		players.remove(p.getUniqueId());
	}
	
	public void addPlayer(Player p) {
		players.add(p.getUniqueId());
	}
}