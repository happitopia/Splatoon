package com.yoerik.MCCollectiveLearning;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Level {
    private final int id;
    private final Location spawn;
    private final List<UUID> players = new ArrayList<UUID>();
 
    public Level(Location spawn, int id) {
        this.spawn = spawn;
        this.id = id;
    }
 
    // Getters
 
    public int getId() {
        return this.id;
    }
 
    public List<UUID> getPlayers() {
        return this.players;
    }
}