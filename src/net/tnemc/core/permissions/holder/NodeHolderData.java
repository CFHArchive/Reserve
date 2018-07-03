package net.tnemc.core.permissions.holder;

import org.bukkit.plugin.Plugin;

/**
 * Created by creatorfromhell on 12/21/2017.
 * <p>
 * Reserve API
 * <p>
 * Copyright (C) 2018 creatorfromhell
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
public interface NodeHolderData {

  /**
   * @return The plugin that this {@link NodeHolder} was created by.
   */
  Plugin owner();

  /**
   * @return The identifier of this {@link NodeHolder}.
   */
  String identifier();

  /**
   * @return True if this {@link NodeHolder} is currently loaded, else false.
   */
  boolean loaded();
}