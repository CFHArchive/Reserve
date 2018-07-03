package net.tnemc.core;

import net.milkbowl.vault.economy.Economy;
import net.tnemc.core.chat.ChatAPI;
import net.tnemc.core.command.CommandManager;
import net.tnemc.core.command.TNECommand;
import net.tnemc.core.command.reserve.ReserveCommand;
import net.tnemc.core.economy.EconomyAPI;
import net.tnemc.core.economy.Economy_Vault;
import net.tnemc.core.permissions.PermissionsAPI;
import net.tnemc.core.utils.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by creatorfromhell on 8/9/2017.
 *
 * Reserve API
 *
 * Copyright (C) 2018 creatorfromhell
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

  private Economy_Vault vaultEconomy;

  protected CommandManager commandManager;

  private LinkedHashMap<String, EconomyAPI> registeredEconomies = new LinkedHashMap<>();
  private LinkedHashMap<String, PermissionsAPI> registeredPermissions = new LinkedHashMap<>();
  private LinkedHashMap<String, ChatAPI> registeredChats = new LinkedHashMap<>();
  private String ecoProvider = null;
  private String permissionsProvider = null;
  private String chatProvider = null;

  public String defaultWorld = "Default";

  public void onLoad() {
    instance = this;

    ConfigurationManager.initialize(this);
  }

  public void onEnable() {
    commandManager = new CommandManager();

    if (!ConfigurationManager.loadSettings()){
      // Failed to load configuration. You decide what to do.
    }
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
    registeredEconomies.put(provider.name(), provider);
    if(provider.enabled()) {
      ecoProvider = provider.name();
      if(provider.vault() && getServer().getPluginManager().getPlugin("Vault") != null) {
        vaultEconomy = new Economy_Vault(this);
        setupVault();
      }
    }
  }

  public void registerProvider(PermissionsAPI provider) {
    getLogger().info("Permissions Provider registered: " + provider.name());
    if(provider.enabled()) {
      permissionsProvider = provider.name();
    }
    registeredPermissions.put(provider.name(), provider);
  }

  public void registerProvider(ChatAPI provider) {
    getLogger().info("Chat Provider registered: " + provider.name());
    if(provider.enabled()) {
      chatProvider = provider.name();
    }
    registeredChats.put(provider.name(), provider);
  }

  public LinkedHashMap<String, EconomyAPI> getRegisteredEconomies() {
    return registeredEconomies;
  }

  public LinkedHashMap<String, PermissionsAPI> getRegisteredPermissions() {
    return registeredPermissions;
  }

  public LinkedHashMap<String, ChatAPI> getRegisteredChats() {
    return registeredChats;
  }

  public void setEconomy(String name) {
    ecoProvider = name;
  }

  public void setPermissions(String name) {
    permissionsProvider = name;
  }

  public void setChat(String name) {
    chatProvider = name;
  }

  public EconomyAPI economy() {
    return registeredEconomies.get(ecoProvider);
  }

  public PermissionsAPI permissions() {
    return registeredPermissions.get(permissionsProvider);
  }

  public boolean economyProvided() {
    return ecoProvider != null;
  }

  public boolean permissionsProvided() {
    return permissionsProvider != null;
  }

  public boolean chatProvided() {
    return chatProvider != null;
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

  private void setupVault() {
    getServer().getServicesManager().register(Economy.class, vaultEconomy, this, ServicePriority.Highest);
    getLogger().info("Hooked into Vault");
  }
}