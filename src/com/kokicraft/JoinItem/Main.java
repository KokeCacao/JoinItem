package com.kokicraft.JoinItem;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main plugin;
	public Logger log = Logger.getLogger("Minecraft");

	public void onEnable() {
		plugin = this;
		registerEvent();
		this.getConfig();
		this.saveDefaultConfig();
		registerCommand();
		log.info("[JoinItem] Version " + this.getDescription().getVersion() + " has been enabled.");
	}

	public void registerEvent() {
		getServer().getPluginManager().registerEvents(new WorldEvent(this), this);
	}

	public void registerCommand() {
		getCommand("joinitem").setExecutor(new JoinItemCommand(plugin));
	}

	public void onDisable() {
		log.info("[JoinItem] JoinItem has been disabled.");
	}
}
