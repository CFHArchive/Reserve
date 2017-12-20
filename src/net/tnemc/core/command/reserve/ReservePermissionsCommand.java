package net.tnemc.core.command.reserve;

import net.tnemc.core.Reserve;
import net.tnemc.core.command.TNECommand;
import net.tnemc.core.permissions.PermissionsAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by creatorfromhell on 10/16/2017.
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
public class ReservePermissionsCommand extends TNECommand {

  public ReservePermissionsCommand(Reserve plugin) {
    super(plugin);
  }

  @Override
  public String getName() {
    return "permissions";
  }

  @Override
  public String[] getAliases() {
    return new String[]{
        "perm"
    };
  }

  @Override
  public String getNode() {
    return "reserve.admin.permission";
  }

  @Override
  public boolean console() {
    return true;
  }

  @Override
  public String getHelp() {
    return ChatColor.GOLD + "/reserve permissions " + ChatColor.WHITE + "- Displays the permissions service that is currently being used.";
  }

  @Override
  public boolean execute(CommandSender sender, String command, String[] arguments) {
    if(Reserve.instance().permissionsProvided()) {
      PermissionsAPI api = Reserve.instance().permissions().get();
      sender.sendMessage(ChatColor.WHITE + "Permissions Service: " + ChatColor.GREEN + api.name());
      sender.sendMessage(ChatColor.WHITE + " Supported Reserve Version: " + ChatColor.GREEN + api.version());
      return true;
    }
    sender.sendMessage(ChatColor.WHITE + "There is currently no permissions service running.");
    return false;
  }
}