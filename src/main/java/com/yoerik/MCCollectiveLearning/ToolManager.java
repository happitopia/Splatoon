package com.yoerik.MCCollectiveLearning;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

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
					checkBreak(player, Material.STONE_SPADE, event);
					break;
				// Axes can break these
				case LOG:
				case WOOD:
					checkBreak(player, Material.STONE_AXE, event);
					break;
				// Pickaxes can break these
				case STONE:
				case COBBLESTONE:
				case COAL_ORE:
				case IRON_ORE:
				case GOLD_ORE:
				case DIAMOND_ORE:
				case REDSTONE_ORE:
					checkBreak(player, Material.STONE_PICKAXE, event);
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
				// Nobody can break anything else
				default:
					event.setCancelled(true);
					break;
			}
		}
	}
	private static final Set<Material> allowedCraftingItems = new HashSet<Material>(Arrays.asList(new Material[] {Material.STICK, Material.WOOD, Material.WORKBENCH, Material.STONE_SPADE, Material.STONE_AXE, Material.STONE_PICKAXE, Material.STAINED_GLASS, Material.FURNACE}));
	
	@EventHandler
	public void craftItem(PrepareItemCraftEvent e) {
		Material itemType = e.getRecipe().getResult().getType();
		if (!allowedCraftingItems.contains(itemType)) {
			e.getInventory().setResult(new ItemStack(Material.AIR));
			for (HumanEntity he : e.getViewers()) {
				if (he instanceof Player) {
					((Player) he).sendMessage(ChatColor.RED + "You cannot craft this!");
				}
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
