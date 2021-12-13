package net.tnemc.core.economy.strict.account;

import net.tnemc.core.generic.source.ActionSource;

import java.util.UUID;
/**
 * Created by creatorfromhell on 10/14/2021.
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

/**
 * This represents a generic account object that is not attached to a Player. This should be used
 * for things like bank accounts, NPC accounts and server accounts.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface GenericAccount extends Account {

  /**
   * Used to determine if a player has access to this account.
   * @param identifier The unique identifier of the player.
   * @param accessLevel The access level we are checking to see if the player has.
   * @return True if the player has the specified {@link AccessLevel} to this account, otherwise false.
   * @since 1.0.0
   */
  boolean hasAccess(UUID identifier, AccessLevel accessLevel);

  /**
   * Used to update access that a player has to this account.
   * @param identifier The {@link UUID} associated with the player in this operation.
   * @param accessLevel The access level we are adding for the specified player.
   * @param source The {@link ActionSource source} response for this deletion call.
   * @return True if the operation was successful, otherwise false.
   */
  boolean updateAccess(UUID identifier, AccessLevel accessLevel, ActionSource source);

  /**
   * Used to remove all access from this account for the specified player.
   * @param identifier The {@link UUID} associated with the player in this operation.
   * @param source The {@link ActionSource source} response for this deletion call.
   * @return True if the operation was successful, otherwise false.
   */
  boolean removeAccess(UUID identifier, ActionSource source);
}