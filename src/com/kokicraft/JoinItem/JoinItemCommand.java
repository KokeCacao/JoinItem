package com.kokicraft.JoinItem;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class JoinItemCommand implements CommandExecutor {

  private Main plugin;

  public JoinItemCommand(Main plugin) {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, final String[] args) {
    if (args.length == 0) {
      sender.sendMessage("Usage: /joinitem add|remove|list <item_name>");
    } else if (args.length == 1) {
      switch (args[0]) {
        case "add":
          sender.sendMessage("Usage: /joinitem add <item_name>");
          break;
        case "remove":
          sender.sendMessage("Usage: /joinitem remove <item_name>");
          break;
        case "list":
          List<String> activeItems = plugin.getConfig().getStringList("active");
          sender.sendMessage("Active items: " + activeItems);
          break;
        default:
          sender.sendMessage("Usage: /joinitem add|remove|list <item_name>");
          break;
      }
    } else if (args.length == 2) {
      switch (args[0]) {
        case "add":
          if (sender instanceof Player) {
            Player p = (Player) sender;
            ItemStack stack = p.getInventory().getItemInMainHand();
            if (stack != null) {
              plugin.getConfig().set("items." + args[1], stack);
              List<String> activeItems = plugin.getConfig().getStringList("active");
              activeItems.add(args[1]);
              plugin.getConfig().set("active", activeItems);
              plugin.saveConfig();
              sender.sendMessage("Added item " + args[1] + " to the list of items to give on join.");
            } else {
              sender.sendMessage("You must be holding an item to add it to the list of items to give on join.");
            }
          } else {
            sender.sendMessage("You must be a player to add an item to the list of items to give on join.");
          }
          break;
        case "remove":
          List<String> activeItems = plugin.getConfig().getStringList("active");
          if (activeItems.contains(args[1])) {
            activeItems.remove(args[1]);
            plugin.getConfig().set("active", activeItems);
            plugin.saveConfig();
            sender.sendMessage("Removed item " + args[1] + " from the list of items to give on join.");
          } else {
            sender.sendMessage("Item " + args[1] + " is not in the list of items to give on join.");
          }
          break;
        case "list":
          sender.sendMessage("Usage: /joinitem list");
          break;
      }
    } else {
      sender.sendMessage("Usage: /joinitem add|remove|list <item_name>");
    }
    return true;
  }
}