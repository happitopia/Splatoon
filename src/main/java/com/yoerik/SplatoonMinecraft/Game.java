package com.yoerik.SplatoonMinecraft;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Game {
	private int id;
	private Location spawn;
	private Location lobby;
	private List<UUID> players = new ArrayList<UUID>();
	private HashMap<UUID, Team> teams = new HashMap<UUID, Team>();
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
	
	public Material getColor(Player p) {
		return teams.get(p.getUniqueId()).getColor();
	}
	
	public void removePlayer(Player p) {
		players.remove(p.getUniqueId());
		teams.remove(p);
	}
	
	public void addPlayer(Player p, Team team) {
		players.add(p.getUniqueId());
		teams.put(p.getUniqueId(), team);
	}
}