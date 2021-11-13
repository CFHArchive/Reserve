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
public class ReserveSetCommand extends TNECommand {

  public ReserveSetCommand(Reserve plugin) {
    super(plugin);
  }

  @Override
  public String getName() {
    return "set";
  }

  @Override
  public String[] getAliases() {
    return new String[0];
  }

  @Override
  public String getNode() {
    return "reserve.admin.set";
  }

  @Override
  public boolean console() {
    return true;
  }

  @Override
  public String getHelp() {
    return ChatColor.GOLD + "/reserve set <name> " + ChatColor.WHITE + "- Set the active economy provider to the one specified.";
  }

  @Override
  public boolean execute(CommandSender sender, String command, String[] arguments) {
    if(arguments.length < 1) {
      help(sender);
      return false;
    }
    /*if(Reserve.instance().getRegisteredEconomies().containsKey(arguments[0])) {
      Reserve.instance().setEconomy(arguments[0]);
      sender.sendMessage(ChatColor.WHITE + "Successfully set economy provider to " + arguments[0] + ".");
      return true;
    }*/
    sender.sendMessage(ChatColor.RED + "No economy provider found with the name of " + arguments[0] + ".");
    return false;
  }
}