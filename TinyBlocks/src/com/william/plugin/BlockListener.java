package com.william.plugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class BlockListener implements Listener {

	private Main main;
	
	public BlockListener(Main main) {
		this.main = main;
	}
	
	//Tiny blocks mode default to off:
	boolean tinyBlocksOn = false;
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onClickBlazeRod(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		
		//Right-clicking blaze rod toggles tiny blocks mode:
		if (player.getItemInHand().getType().equals(Material.BLAZE_ROD)) {
			if (tinyBlocksOn == false) {
				tinyBlocksOn = true;
				player.sendMessage(ChatColor.GREEN + "Tiny blocks on!");
			}
			else {
				tinyBlocksOn = false;
				player.sendMessage(ChatColor.RED + "Tiny blocks off!");
			}
		}
		
	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		
		//When block is placed, it is replaced with tiny block if tiny blocks mode is on:
		if (tinyBlocksOn == true) {
			Material block = e.getBlockPlaced().getType();
			e.setCancelled(true);
			Location location = e.getBlock().getLocation();
			spawnStand(location.add(0.5, -0.59375, 0.5), new ItemStack(block));
		}
	}
	
	//Replace normal block with tiny block using armor stand with block as helmet:
	private void spawnStand(Location location, ItemStack material) {
		ArmorStand stand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
		
		stand.setBasePlate(false);
		stand.setInvulnerable(true);
		stand.setVisible(false);
		stand.setGravity(false);
		stand.setSmall(true);
		
		stand.setHelmet(material);
	}
	
}
