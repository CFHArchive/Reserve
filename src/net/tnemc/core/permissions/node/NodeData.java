package net.tnemc.core.permissions.node;

import net.tnemc.core.permissions.holder.NodeHolder;
import org.bukkit.plugin.Plugin;

/**
 * Created by creatorfromhell on 12/19/2017.
 * <p>
 * Reserve API
 * <p>
 * Copyright (C) 2017 creatorfromhell
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
public interface NodeData {

  /**
   * @return The plugin that this {@link Node} belongs to.
   */
  Plugin owner();

  /**
   * @return The identifier of this {@link Node}.
   */
  String identifier();

  /**
   * @return The description of this {@link Node}.
   */
  String description();

  /**
   * @return The default value of the corresponding {@link Node}. True means all {@link NodeHolder holders} have it by
   * default.
   */
  Boolean defaultValue();
}