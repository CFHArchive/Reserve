package net.tnemc.core.chat.language;

import java.util.Optional;

/**
 * Created by Daniel.
 *
 * Reserve API
 *
 * Copyright (C) 2018 creatorfromhell
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
public interface LanguageAPI {

  /**
   * Determines if a specific language exists.
   * @param name The name of the language.
   * @return True if the specified language exists, otherwise false.
   */
  boolean hasLanguage(String name);

  /**
   * Attempts to create a new language.
   * @param name The name(s) of the language.
   * @return True if the language was created, otherwise false.
   */
  boolean createLanguage(String... name);

  /**
   * Determines if a language has a message based on the specified owner and node.
   * @param language The name of the language.
   * @param owner The owner of the message, usually plugin name.
   * @param node The node identifier of the message.
   * @return True if the language contains the message, otherwise false.
   */
  boolean hasMessage(String language, String owner, String node);

  /**
   * Attempts to retrieve the specified message from a language.
   * @param language The name of the language.
   * @param owner The owner of the message, usually plugin name.
   * @param node The node identifier of the message.
   * @return An optional containing the message if it exists, otherwise an empty optional.
   */
  Optional<String> getMessage(String language, String owner, String node);

  /**
   * Attempts to add a message to a language for a specified node identifier.
   * @param language The name of the language.
   * @param owner The owner of the message, usually plugin name.
   * @param node The node used to identify the message.
   * @param translation The translation to be used for the language.
   * @return True if the message was added.
   */
  boolean addMessage(String language, String owner, String node, String translation);

  /**
   * Attempts to remove a message from a language for a specified node identifier.
   * @param language The name of the language.
   * @param owner The owner of the message, usually plugin name.
   * @param node The node used to identify the message.
   * @return True if the message was removed.
   */
  boolean removeMessage(String language, String owner, String node);
}