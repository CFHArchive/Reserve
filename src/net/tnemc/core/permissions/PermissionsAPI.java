package net.tnemc.core.permissions;

import net.tnemc.core.permissions.holder.HolderGroup;
import net.tnemc.core.permissions.holder.NodeHolder;
import net.tnemc.core.permissions.node.Node;
import net.tnemc.core.permissions.node.NodeData;
import net.tnemc.core.permissions.node.NodePath;
import org.bukkit.plugin.Plugin;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
public interface PermissionsAPI {

  /**
   * @return The name of the Permissions implementation.
   */
  String name();

  /**
   * @return The version of Reserve the Permissions implementation supports.
   */
  String version();

  /**
   * @return Whether or not this implementation is enabled.
   */
  boolean enabled();

  /**
   * @return A {@link Collection} of every {@link NodeHolder} that currently exists.
   */
  CompletableFuture<Collection<NodeHolder>> getHolders();

  /**
   * @param owner The {@link Plugin} instance to be used in this check.
   * @return A {@link Collection} of every {@link NodeHolder} that currently exists, which also has the specified
   * {@link Plugin} as its owner.
   */
  CompletableFuture<Collection<NodeHolder>> getHolders(Plugin owner);

  /**
   * @return A {@link Collection} of every {@link NodeHolder} that is currently loaded.
   */
  Collection<NodeHolder> getLoadedHolders();

  /**
   * @param owner The {@link Plugin} instance to be used in this check.
   * @return A {@link Collection} of every {@link NodeHolder} that is currently loaded, which also has the specified
   * {@link Plugin} as its owner.
   */
  CompletableFuture<Collection<NodeHolder>> getLoadedHolders(Plugin owner);

  /**
   * @param identifier The string identifier for a {@link Node} object.
   * @return An empty {@link Optional} if there is no {@link Node} for the specified identifier, otherwise an optional
   * with the Node object.
   */
  Optional<Node> getNode(String identifier);

  /**
   * @param identifier The string identifier for a {@link NodeData} object.
   * @return An empty {@link Optional} if there is no {@link NodeData} for the specified identifier, otherwise an optional
   * with the NodeData object.
   */
  Optional<NodeData> getNodeData(String identifier);

  /**
   * @param identifier The String representation of the {@link NodePath} that's being requested.
   * @return An empty {@link Optional} if there is no {@link NodePath} for the specified identifier, otherwise an optional
   * with the NodePath object.
   */
  Optional<NodePath> getPath(String identifier);

  /**
   * @return A {@link Collection} of every {@link Node} that currently exists.
   */
  CompletableFuture<Collection<Node>> getNodes();

  /**
   * @param identifier The identifier of the {@link HolderGroup} used in this check.
   * @return True if a {@link HolderGroup} with the specified identifier exists.
   */
  CompletableFuture<Boolean> hasGroup(String identifier);

  /**
   * @param identifier The identifier of the {@link HolderGroup} used in this check.
   * @return An empty Optional if no {@link HolderGroup} exists with the specified identifier, otherwise an Optional with
   * the group.
   */
  Optional<HolderGroup> getGroup(String identifier);

  /**
   * @param identifier The identifier of the {@link NodeHolder} used in this check.
   * @return True if a {@link NodeHolder} with the specified identifier exists.
   */
  CompletableFuture<Boolean> hasHolder(String identifier);

  /**
   * @param identifier The identifier of the {@link NodeHolder} used in this check.
   * @return An empty Optional if no {@link NodeHolder} exists with the specified identifier, otherwise an Optional with
   * the holder.
   */
  CompletableFuture<Optional<NodeHolder>> getHolder(String identifier);
}