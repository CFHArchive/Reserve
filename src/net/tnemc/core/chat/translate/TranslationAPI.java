package net.tnemc.core.chat.translate;

import org.bukkit.command.CommandSender;

import java.util.UUID;

/**
 * Created by Daniel.
 *
 * Reserve API
 *
 * Copyright (C) 2017 creatorfromhell
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 **/
public interface TranslationAPI {

  /**
   * Determines if the specified language has a handler associated with it.
   * @param language The name of the language to use for the check.
   * @return True if the language has a registered handler, otherwise false.
   */
  boolean hasHandler(String language);

  /**
   * Attempts to register a {@link LanguageHandler}.
   * @param handler The handler to register.
   * @return True if the handler was registered, otherwise false.
   */
  boolean registerHandler(LanguageHandler handler);

  /**
   * Determines if the player has a set language.
   * @param player The UUID of the player.
   * @return True if the player's language has been set, otherwise false.
   */
  boolean hasLanguage(UUID player);

  /**
   * Determines if the player has a set language for the specified world.
   * @param player The UUID of the player.
   * @param world The world to use for the check.
   * @return True if the player's language has been set in the specified world, otherwise false.
   */
  boolean hasLanguage(UUID player, String world);

  /**
   * Used to set a player's language
   * @param player The UUID of the player.
   * @param language The name of the language to use for this operation.
   * @return The corresponding {@link LanguageResult} for the outcome.
   */
  LanguageResult setLanguage(UUID player, String language);

  /**
   * Determines if the player has a set language for the specified world.
   * @param player The UUID of the player.
   * @param world The world to use for the check.
   * @param language The name of the language to use for this operation.
   * @return The corresponding {@link LanguageResult} for the outcome.
   */
  LanguageResult setLanguage(UUID player, String world, String language);

  /**
   * Attempts to translate the provided message.
   * @param message The message to translate.
   * @param sender The sender of the message.
   * @param receiver The receiver of the message.
   * @return The translation of the message if translated, otherwise the original message.
   */
  String translate(String message, CommandSender sender, CommandSender receiver);
}