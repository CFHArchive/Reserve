package net.tnemc.core;

import net.milkbowl.vault.economy.Economy;
import net.tnemc.core.command.CommandManager;
import net.tnemc.core.command.TNECommand;
import net.tnemc.core.command.reserve.ReserveCommand;
import net.tnemc.core.economy.strict.EconomyAdapter;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by creatorfromhell on 8/9/2017.
 * <p>
 * Reserve API
 * <p>
 * Copyright (C) 2021 creatorfromhell
 * <p>
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA, or see
 * it at <https://www.gnu.org/licenses/lgpl-3.0.txt>.
 **/
public class Reserve extends JavaPlugin {

  private static Reserve instance;

  protected CommandManager commandManager;

  private String ecoProvider = "";

  public String defaultWorld = "Default";

  public void onLoad() {
    instance = this;

    ConfigurationManager.initialize(this);
  }

  public void onEnable() {
    commandManager = new CommandManager();

    if(!ConfigurationManager.loadSettings()) {
      // Failed to load configuration. You decide what to do.
    }
    defaultWorld = Bukkit.getServer().getWorlds().get(0).getName();
    registerCommand(new String[]{"reserve", "rsv"}, new ReserveCommand(this));

    new Metrics(this, 2586);
  }

  public static Reserve instance() {
    return instance;
  }

  public LinkedHashMap<String, EconomyAdapter> getRegisteredEconomies() {
    LinkedHashMap<String, EconomyAdapter> registered = new LinkedHashMap<>();

    for(RegisteredServiceProvider<EconomyAdapter> adapter : Bukkit.getServicesManager().getRegistrations(EconomyAdapter.class)) {
      registered.put(adapter.getProvider().name(), adapter.getProvider());
    }

    return registered;
  }

  public void setEconomy(String name) {
    ecoProvider = name;
  }

  public EconomyAdapter economy() {
    return (EconomyAdapter)Bukkit.getServicesManager().getRegistration(EconomyAdapter.class);
  }

  public void registerVault(RegisteredServiceProvider<EconomyAdapter> provider) {
    getLogger().info("Reserve: Hooked " + provider.getProvider().name() + " into vault.");
    getServer().getServicesManager().register(Economy.class, new VaultAdapter(provider.getProvider()), this, provider.getPriority());
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
