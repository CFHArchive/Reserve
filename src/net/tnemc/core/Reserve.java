package net.tnemc.core;

import net.tnemc.core.command.CommandManager;
import net.tnemc.core.command.TNECommand;
import net.tnemc.core.command.reserve.ReserveCommand;
import net.tnemc.core.economy.EconomyAPI;
import net.tnemc.core.permissions.PermissionsAPI;
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
 *
 * Reserve API
 *
 * Copyright (C) 2017 creatorfromhell
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
public class Reserve extends JavaPlugin {

  private static Reserve instance;
  protected CommandManager commandManager;

  private Map<String, EconomyAPI> registeredEconomies = new HashMap<>();
  private Map<String, PermissionsAPI> registeredPermissions = new HashMap<>();
  private String ecoProvider = null;
  private String permissionsProvider = null;

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
    if(provider.enabled()) {
      ecoProvider = provider.name();
    }
    registeredEconomies.put(provider.name(), provider);
  }

  public void registerProvider(PermissionsAPI provider) {
    getLogger().info("Permissions Provider registered: " + provider.name());
    if(provider.enabled()) {
      permissionsProvider = provider.name();
    }
    registeredPermissions.put(provider.name(), provider);
  }

  public Map<String, EconomyAPI> getRegisteredEconomies() {
    return registeredEconomies;
  }

  public Map<String, PermissionsAPI> getRegisteredPermissions() {
    return registeredPermissions;
  }

  public void setEconomy(String name) {
    ecoProvider = name;
  }

  public void setPermissions(String name) {
    permissionsProvider = name;
  }

  public Optional<EconomyAPI> economy() {
    return Optional.of(registeredEconomies.get(ecoProvider));
  }
  public Optional<PermissionsAPI> permissions() {
    return Optional.of(registeredPermissions.get(permissionsProvider));
  }

  public boolean economyProvided() {
    return ecoProvider != null;
  }

  public boolean permissionsProvided() {
    return permissionsProvider != null;
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