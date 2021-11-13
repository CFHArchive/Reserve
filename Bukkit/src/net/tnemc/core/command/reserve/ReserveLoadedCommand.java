package net.tnemc.core.command.reserve;

import net.tnemc.core.command.TNECommand;
import net.tnemc.core.Reserve;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;


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
public class ReserveLoadedCommand extends TNECommand {

  public ReserveLoadedCommand(Reserve plugin) {
    super(plugin);
  }

  @Override
  public String getName() {
    return "loaded";
  }

  @Override
  public String[] getAliases() {
    return new String[0];
  }

  @Override
  public String getNode() {
    return "reserve.admin.loaded";
  }

  @Override
  public boolean console() {
    return true;
  }

  @Override
  public String getHelp() {
    return ChatColor.GOLD + "/reserve loaded " + ChatColor.WHITE + "- Displays economy providers that are currently registered.";
  }

  @Override
  public boolean execute(CommandSender sender, String command, String[] arguments) {
    StringBuilder economies = new StringBuilder();
    /*for(String name : Reserve.instance().getRegisteredEconomies().keySet()) {
      if(!economies.toString().equalsIgnoreCase("")) { economies.append(", "); }
      economies.append(name);
    }*/
    sender.sendMessage("Economy Providers: " + economies);
    return true;
  }
}