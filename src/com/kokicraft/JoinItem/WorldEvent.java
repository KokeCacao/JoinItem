package com.kokicraft.JoinItem;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class WorldEvent implements Listener {

  public Main plugin;

  public WorldEvent(Main instance) {
    plugin = instance;
  }

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player p = event.getPlayer();
    if (!p.hasPlayedBefore()) {
      List<String> activeItems = plugin.getConfig().getStringList("active");
      plugin.log.info("[JoinItem] Active items: " + activeItems + " for player " + p.getName());
      for (String item : activeItems) {
        if (item == null || item.isEmpty()) {
          continue;
        }
        ItemStack stack = plugin.getConfig().getItemStack("items." + item);
        p.getInventory().addItem(stack);
      }
    }
  }
}
