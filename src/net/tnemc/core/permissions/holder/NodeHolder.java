package net.tnemc.core.permissions.holder;

import net.tnemc.core.permissions.node.Node;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

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
public interface NodeHolder {

  /**
   * @return An instance of the {@link NodeHolderData} that belongs to this {@link NodeHolder}.
   */
  NodeHolderData getData();

  /**
   * @return Empty optional if this holder has no parent, otherwise return Optional containing the {@link NodeHolder}
   * parent.
   */
  Optional<NodeHolder> getParent();

  /**
   * @return A {@link Collection} containing all children {@link NodeHolder}'s of this holder.
   */
  Collection<NodeHolder> getChildren();

  /**
   * @param identifier The identifier of child {@link NodeHolder} you're attempting to find.
   * @return True if this holder contains a child {@link NodeHolder} with the specified identifier, otherwise false.
   */
  boolean hasChild(String identifier);

  /**
   * @param node The identifier of the {@link Node} involved in this check.
   * @return True if this {@link NodeHolder} has the specified {@link Node} for the identifier.
   */
  boolean hasPermission(String node);

  /**
   * @return A {@link Map} of every permission this holder has, and a boolean value of whether this {@link NodeHolder}
   * has the permission, or not.
   */
  Map<String, Boolean> getPermissions();
}