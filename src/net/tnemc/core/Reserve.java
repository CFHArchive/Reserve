package net.tnemc.core;

import net.tnemc.core.command.CommandManager;
import net.tnemc.core.command.TNECommand;
import net.tnemc.core.command.reserve.ReserveCommand;
import net.tnemc.core.economy.EconomyAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by creatorfromhell on 8/9/2017.
 * All rights reserved.
 **/
public class Reserve extends JavaPlugin {

  private static Reserve instance;
  protected CommandManager commandManager;
  private EconomyAPI economy = null;

  private Map<String, EconomyAPI> registeredProviders = new HashMap<>();
  private String ecoProvider = null;

  public String defaultWorld = "Default";

  public void onEnable() {
    instance = this;
    commandManager = new CommandManager();

    if(Bukkit.getWorlds().size() >= 1) {
      defaultWorld = Bukkit.getServer().getWorlds().get(0).getName();
    } else {
      defaultWorld = "world";
    }
    registerCommand(new String[] { "reserve", "rsv" }, new ReserveCommand(this));

    new Metrics(this);
  }

  public static Reserve instance() {
    return instance;
  }

  public void registerProvider(EconomyAPI provider) {
    getLogger().info("Economy Provider registered: " + provider.name());
    ecoProvider = provider.name();
    registeredProviders.put(provider.name(), provider);
  }

  public Map<String, EconomyAPI> getProviders() {
    return registeredProviders;
  }

  public void setProvider(String name) {
    ecoProvider = name;
  }

  public Optional<EconomyAPI> economy() {
    return Optional.of(registeredProviders.get(ecoProvider));
  }

  public boolean economyProvided() {
    return ecoProvider != null;
  }

  private CommandManager getCommandManager() {
    return commandManager;
  }

  private void registerCommand(String[] accessors, TNECommand command) {
    commandManager.commands.put(accessors, command);
    commandManager.registerCommands();
  }

  private void registerCommands(Map<String[], TNECommand> commands) {
    commandManager.commands = commands;
    commandManager.registerCommands();
  }

  private void unregisterCommand(String[] accessors) {
    commandManager.unregister(accessors);
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
    TNECommand ecoCommand = commandManager.Find(label);
    if(ecoCommand != null) {
      if(!ecoCommand.canExecute(sender)) {
        sender.sendMessage(ChatColor.RED + "I'm sorry, but you're not allowed to use that commands.");
        return false;
      }
      return ecoCommand.execute(sender, label, arguments);
    }
    return false;
  }
}