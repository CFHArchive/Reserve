package net.tnemc.reserve.core.command.reserve;

import net.tnemc.reserve.core.Reserve;
import net.tnemc.reserve.core.command.TNECommand;

/**
 * Created by creatorfromhell on 10/16/2017.
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
public class ReserveCommand extends TNECommand {

  public ReserveCommand(Reserve plugin) {
    super(plugin);
    subCommands.add(new ReserveEconomyCommand(plugin));
    subCommands.add(new ReserveLoadedCommand(plugin));
    subCommands.add(new ReserveSetCommand(plugin));
  }

  @Override
  public String getName() {
    return "reserve";
  }

  @Override
  public String[] getAliases() {
    return new String[] { "rsv" };
  }

  @Override
  public String getNode() {
    return "reserve.admin";
  }

  @Override
  public boolean console() {
    return true;
  }
}