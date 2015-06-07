package com.yoerik.MCCollectiveLearning;


import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class ToolManager implements Listener {
	public MCCollectiveLearning plugin;
	
	public ToolManager(MCCollectiveLearning instance) {
		this.plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if (player.getGameMode() == GameMode.SURVIVAL && plugin.gameManager.isInGame(player)) {
			Block block = event.getBlock();
			Material material = block.getType();
			switch (material) {
			// Shovels can break these
				case SAND:
				case DIRT:
				case CLAY:
				case GRASS:
					checkBreak(player, Material.WOOD_SPADE, event);
					break;
				// Axes can break these
				case LOG:
				case WOOD:
					checkBreak(player, Material.WOOD_AXE, event);
					break;
				// Pickaxes can break these
				case STONE:
				case COBBLESTONE:
				case COAL_ORE:
					checkBreak(player, Material.WOOD_PICKAXE, event);
					break;
				// Everyone can break these
				case LONG_GRASS:
				case SUGAR_CANE_BLOCK:
				case DOUBLE_PLANT:
				case LEAVES:
				case YELLOW_FLOWER:
				case SNOW:
				case RED_ROSE:
					event.setCancelled(false);
					break;
				default:
					event.setCancelled(true);
					break;
			}
		}
	}
	
	private void checkBreak(Player player, Material material, BlockBreakEvent event) {
		if (!isPlayerHolding(player, material)) {
			event.setCancelled(true);
			player.sendMessage("You are not allowed to break this");
		}
	}
	
	private boolean isPlayerHolding(Player player, Material material) {
		return player.getItemInHand().getType() == material;
	}
}
