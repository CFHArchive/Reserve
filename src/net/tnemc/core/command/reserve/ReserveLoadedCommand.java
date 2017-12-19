package net.tnemc.core.command.reserve;

import net.tnemc.core.Reserve;
import net.tnemc.core.command.TNECommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by creatorfromhell on 12/7/2017.
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
    String joined = "";
    for(String name : Reserve.instance().getProviders().keySet()) {
      if(!joined.equalsIgnoreCase("")) joined += ", ";
      joined += name;
    }
    sender.sendMessage("Economy Providers: " + joined);
    return true;
  }
}