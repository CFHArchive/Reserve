package net.tnemc.core.language;

import java.util.Optional;

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
public interface Language {

  /**
   * @return The name(s) of this language.
   */
  String[] name();

  /**
   * Determines if this language has a message based on the specified owner and node.
   * @param owner The owner of the message, usually plugin name.
   * @param node The node identifier of the message.
   * @return True if this language contains the message, otherwise false.
   */
  boolean hasMessage(String owner, String node);

  /**
   * Attempts to retrieve the specified message.
   * @param owner The owner of the message, usually plugin name.
   * @param node The node identifier of the message.
   * @return An optional containing the message if it exists, otherwise an empty optional.
   */
  Optional<String> getMessage(String owner, String node);

  /**
   * Attempts to add a message to this language for a specified node identifier.
   * @param owner The owner of the message, usually plugin name.
   * @param description An array containing lines to be used as a description for this message.
   * @param node The node used to identify the message.
   * @param translation The translation to be used for this language.
   * @return True if the message was added.
   */
  boolean addMessage(String owner, String[] description, String node, String translation);

  /**
   * Attempts to remove a message from this language for a specified node identifier.
   * @param owner The owner of the message, usually plugin name.
   * @param node The node used to identify the message.
   * @return True if the message was removed.
   */
  boolean removeMessage(String owner, String node);
}