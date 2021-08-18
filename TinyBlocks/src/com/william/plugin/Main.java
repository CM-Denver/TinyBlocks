package com.william.plugin;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		System.out.println("TinyBlocks plugin has worked (sorta)");
		
		//Add BlockListener:
		Bukkit.getPluginManager().registerEvents(new BlockListener(this), this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("TinyBlocks plugin disabled");
	}
	
}
