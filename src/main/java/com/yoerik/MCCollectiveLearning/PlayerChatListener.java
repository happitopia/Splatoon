package com.yoerik.MCCollectiveLearning;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
	public MCCollectiveLearning plugin;
	
	public PlayerChatListener(MCCollectiveLearning instance) {
		this.plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChat(AsyncPlayerChatEvent chat) {
		if (chat.isCancelled()) {
			return;
		} // no need to do anything.
		Player p = chat.getPlayer();
		if (plugin.gameManager.isInGame(p)) {
			String message = ChatOverride.restrictMessage(chat.getMessage());
			if (message == "") {
				chat.setCancelled(true);
				p.sendMessage("Please make sure that you are using the allowed vocabulary.");
			} else {
				chat.setMessage(message);
			}
		}
	}
}
