package net.tnemc.core.chat;

import net.tnemc.core.chat.language.LanguageAPI;

import java.util.Optional;

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
   * @return Whether or not this implementation supports the {@link ChatRoomAPI}.
   */
  boolean supportsChatRooms();

  /**
   * @return The instance of this implementation's {@link ChatRoomAPI} if applicable.
   */
  Optional<ChatRoomAPI> chatRoomAPI();

  /**
   * @return Whether or not this implementation supports the {@link LanguageAPI}.
   */
  boolean supportsLanguage();

  /**
   * @return The instance of this implementation's {@link LanguageAPI} if applicable.
   */
  Optional<LanguageAPI> languageAPI();
}