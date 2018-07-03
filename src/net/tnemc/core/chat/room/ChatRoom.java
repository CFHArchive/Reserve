package net.tnemc.core.chat.room;

import net.tnemc.core.chat.CostObject;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.UUID;

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
public interface ChatRoom {

  /**
   * @return The name of the chat room.
   */
  String name();

  /**
   * @return True if this room has a {@link ChatNetwork}.
   */
  boolean hasNetwork();

  /**
   * @return The ChatNetwork this room is associated with, otherwise null if it has no network.
   */
  ChatNetwork getNetwork();

  /**
   * @return The {@link RoomType} associated with this room.
   */
  RoomType type();

  /**
   * @return The world of the chat room. Depending on implementation this may not have any meaning.
   */
  String world();

  /**
   * @return The UUID of the chat room owner.
   */
  UUID owner();

  /**
   * @return The {@link net.tnemc.core.chat.CostObject} for joining this room.
   */
  CostObject joinCost();

  /**
   * @return The {@link net.tnemc.core.chat.CostObject} for sending a message in this room.
   */
  CostObject messageCost();

  /**
   * @return The list of members in this chat room that are currently receiving hat messages from this room.
   */
  List<UUID> activeParticipants();

  /**
   * @return The list of members in this chat room, which may or may not be receiving messages from this room.
   */
  List<UUID> participants();

  /**
   * @return The list of players muted in this chat room.
   */
  List<UUID> muted();

  /**
   * @return The list of players banned from this chat room.
   */
  List<UUID> banned();

  /**
   * @return The password required to join this room. Return empty string for no password.
   */
  String password();

  /**
   * @return The permission node required to join this room.
   */
  String joinPermission();

  /**
   * @return The permission node required to send messages in this room.
   */
  String talkPermission();

  /**
   * @return The permission node required to read messages in this room.
   */
  String readPermission();

  /**
   * @return True if this room has a password, otherwise false.
   */
  default boolean hasPassword() {
    return !password().equalsIgnoreCase("");
  }

  /**
   * @return True if this room has a join permission node, otherwise false.
   */
  default boolean hasJoinPermission() {
    return !joinPermission().equalsIgnoreCase("");
  }

  /**
   * @return True if this room has a talk permission node, otherwise false.
   */
  default boolean hasTalkPermission() {
    return !talkPermission().equalsIgnoreCase("");
  }

  /**
   * @return True if this room has a read permission node, otherwise false.
   */
  default boolean hasReadPermission() {
    return !readPermission().equalsIgnoreCase("");
  }

  /**
   * Used to send a message to all active participants in this room.
   * @param sender The sender of the message.
   * @param message The message being sent.
   * @return True if the message was sent.
   */
  boolean broadcast(CommandSender sender, String message);

  /**
   * Used to send a message to all active participants in this room.
   * @param sender The sender of the message.
   * @param message The message being sent.
   * @param node The permission node required to see this message.
   * @return True if the message was sent.
   */
  boolean broadcast(CommandSender sender, String message, String node);
}