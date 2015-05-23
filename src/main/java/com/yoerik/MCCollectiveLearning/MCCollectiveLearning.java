package com.yoerik.MCCollectiveLearning;
import org.bukkit.plugin.java.JavaPlugin;

public final class MCCollectiveLearning extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
	}
 
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}
}