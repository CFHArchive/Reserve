package net.tnemc.core.command.reserve;

import net.tnemc.core.Reserve;
import net.tnemc.core.command.TNECommand;
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
public class ReserveEconomyCommand extends TNECommand {

  public ReserveEconomyCommand(Reserve plugin) {
    super(plugin);
  }

  @Override
  public String getName() {
    return "economy";
  }

  @Override
  public String[] getAliases() {
    return new String[]{
        "eco"
    };
  }

  @Override
  public String getNode() {
    return "reserve.admin.economy";
  }

  @Override
  public boolean console() {
    return true;
  }

  @Override
  public String getHelp() {
    return ChatColor.GOLD + "/reserve economy " + ChatColor.WHITE + "- Displays the economy service that is currently being used.";
  }

  @Override
  public boolean execute(CommandSender sender, String command, String[] arguments) {
    if(Reserve.instance().economyProvided()) {
      /*EconomyAPI api = Reserve.instance().economy();
      sender.sendMessage(ChatColor.WHITE + "Economy Service: " + ChatColor.GREEN + api.name());
      sender.sendMessage(ChatColor.WHITE + " Supported Reserve Version: " + ChatColor.GREEN + api.version());
      */return true;
    }
    sender.sendMessage(ChatColor.WHITE + "There is currently no economy service running.");
    return false;
  }
}