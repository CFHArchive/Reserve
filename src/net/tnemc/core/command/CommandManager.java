package net.tnemc.core.command;

import net.tnemc.core.Reserve;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
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
public class CommandManager {

  public Map<String[], TNECommand> commands = new HashMap<>();
  private Integer lastRegister = 0;
  private Field commandMap = null;
  private Field knownCommands = null;

  public void registerCommands() {
    if(lastRegister == commands.size()) { return; }

    lastRegister = commands.size();
    try {
      commandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
      commandMap.setAccessible(true);
      knownCommands = SimpleCommandMap.class.getDeclaredField("knownCommands");
      knownCommands.setAccessible(true);
    } catch(Exception e) {
      /* do nothing */
    }

    if(commandMap != null && knownCommands != null) {

      for(Map.Entry<String[], TNECommand> entry : commands.entrySet()) {
        for(String s : entry.getKey()) {
          if(entry.getValue().activated("", "")) {
            if(registered(s)) {
              unregister(s);
            }
            register(s);
          }
        }
      }
    }
  }

  public void unregister(String[] accessors) {
    for(String s : accessors) {
      unregister(s);
    }
  }

  private void register(String command) {
    try {
      Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
      c.setAccessible(true);
      PluginCommand pluginCommand = c.newInstance(command, Reserve.instance());
      ((SimpleCommandMap)commandMap.get(Bukkit.getServer())).register(command, pluginCommand);
    } catch(Exception e) {
      //nothing to see here
    }
  }

  private void unregister(String command) {
    try {
      ((Map<String, Command>)knownCommands.get(commandMap.get(Bukkit.getServer()))).remove(command);
    } catch(Exception e) {
      //nothing to see here;
    }
  }

  private Boolean registered(String command) {
    try {
      return ((Map<String, Command>)knownCommands.get(commandMap.get(Bukkit.getServer()))).containsKey(command);
    } catch(Exception e) {
      //nothing to see here;
    }
    return false;
  }

  public TNECommand Find(String name) {
    for(TNECommand c : commands.values()) {
      if(c.getName().equalsIgnoreCase(name)) {
        return c;
      }
    }
    for(TNECommand c : commands.values()) {
      for(String s : c.getAliases()) {
        if(s.equalsIgnoreCase(name)) {
          return c;
        }
      }
    }
    return null;
  }
}