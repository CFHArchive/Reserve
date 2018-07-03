package net.tnemc.core.chat;

import net.tnemc.core.chat.translate.TranslationAPI;

import java.util.UUID;

/**
 * Created by Daniel.
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
public interface ChatAPI {

  /**
   * @return The name of the ChatAPI implementation.
   */
  String name();

  /**
   * @return The version of Reserve the ChatAPI implementation supports.
   */
  String version();

  /**
   * @return Whether or not this implementation is enabled.
   */
  boolean enabled();

  /**
   * @param player The player to use for the check.
   * @return The prefix of the player.
   */
  String getPrefix(UUID player);

  /**
   * @param player The player to use for the check.
   * @param world The world to use for the check.
   * @return The prefix of the player in the world.
   */
  String getPrefix(UUID player, String world);

  /**
   * @param player The player to use for the check.
   * @param prefix The prefix to set for the player.
   * @return True if the player's prefix was set.
   */
  boolean setPrefix(UUID player, String prefix);

  /**
   * @param player The player to use for the check.
   * @param prefix The prefix to set for the player.
   * @param world The world to use for the check.
   * @return True if the player's prefix was set.
   */
  boolean setPrefix(UUID player, String prefix, String world);

  /**
   * @param player The player to use for the check.
   * @return The suffix of the player.
   */
  String getSuffix(UUID player);

  /**
   * @param player The player to use for the check.
   * @param world The world to use for the check.
   * @return The suffix of the player in the world.
   */
  String getSuffix(UUID player, String world);

  /**
   * @param player The player to use for the check.
   * @param suffix The suffix to set for the player.
   * @return True if the player's suffix was set.
   */
  boolean setSuffix(UUID player, String suffix);

  /**
   * @param player The player to use for the check.
   * @param suffix The suffix to set for the player.
   * @param world The world to use for the check.
   * @return True if the player's suffix was set.
   */
  boolean setSuffix(UUID player, String suffix, String world);

  /**
   * @return Whether or not this implementation supports the {@link TranslationAPI}.
   */
  boolean supportsTranslation();

  /**
   * @return The instance of this implementation's {@link TranslationAPI} if applicable.
   */
  TranslationAPI translationAPI();

  /**
   * @return Whether or not this implementation supports the {@link ChatRoomAPI}.
   */
  boolean supportsChatRooms();

  /**
   * @return The instance of this implementation's {@link ChatRoomAPI} if applicable.
   */
  ChatRoomAPI chatRoomAPI();
}