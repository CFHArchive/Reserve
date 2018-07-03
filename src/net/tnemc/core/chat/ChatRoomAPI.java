package net.tnemc.core.chat;

import net.tnemc.core.chat.room.ChatNetwork;
import net.tnemc.core.chat.room.ChatRoom;
import net.tnemc.core.chat.room.RoomType;

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
public interface ChatRoomAPI {

  /**
   * Returns whether a chat network exists or not.
   * @param name The name of the chat network.
   * @return True if it exists, otherwise false.
   */
  boolean hasNetwork(String name);

  /**
   * Returns whether a chat network exists or not.
   * @param name The name of the chat network.
   * @param world The name of the world.
   * @return True if it exists, otherwise false.
   */
  boolean hasNetwork(String name, String world);

  /**
   * Returns the ChatNetwork object if it exists.
   * @param name The name of the chat network.
   * @return The chat network with the specified name if it exists, otherwise false.
   */
  ChatNetwork getNetwork(String name);

  /**
   * Returns the ChatNetwork object if it exists.
   * @param name The name of the chat network.
   * @param world The name of the world.
   * @return The chat network with the specified name if it exists, otherwise false.
   */
  ChatNetwork getNetwork(String name, String world);

  /**
   * Used to determine if a player is in network chat.
   * @param player The player to use for the check.
   * @return True if the player is in network chat, which means their messages are sent to everyone
   * in the network, otherwise false.
   */
  boolean inNetworkChat(UUID player);

  /**
   * Returns the network that the specified player is currently talking in, otherwise null;
   * @param player The player to use for the check.
   * @return The {@link ChatNetwork} that the player is currently talking in.
   */
  ChatNetwork getActiveNetwork(UUID player);

  /**
   * Returns whether a chat room exists or not.
   * @param name The name of the chat room.
   * @return True if it exists, otherwise false.
   */
  boolean hasRoom(String name);

  /**
   * Returns whether a chat room exists or not.
   * @param name The name of the chat room.
   * @param world The name of the world.
   * @return True if it exists, otherwise false.
   */
  boolean hasRoom(String name, String world);

  /**
   * Returns the ChatRoom object if it exists.
   * @param name The name of the chat room.
   * @return The chat room with the specified name if it exists, otherwise false.
   */
  ChatRoom getRoom(String name);

  /**
   * Returns the ChatRoom object if it exists.
   * @param name The name of the chat room.
   * @param world The name of the world.
   * @return The chat room with the specified name if it exists, otherwise false.
   */
  ChatRoom getRoom(String name, String world);

  /**
   * Returns whether a player is a member of a chat room or not.
   * @param name The name of the room.
   * @param player The UUID of the player.
   * @return True if the specified player is a member of the room, otherwise false.
   */
  boolean isRoomMember(String name, UUID player);

  /**
   * Returns whether a player is a member of a chat room or not.
   * @param name The name of the room.
   * @param world The name of the world.
   * @param player The UUID of the player.
   * @return True if the specified player is a member of the room, otherwise false.
   */
  boolean isRoomMember(String name, String world, UUID player);

  /**
   * Attempts to add a player to a room.
   * @param name The name of the room.
   * @param player The UUID of the player.
   * @return True if the player was added.
   */
  boolean addRoomMember(String name, UUID player);

  /**
   * Attempts to add a player to a room.
   * @param name The name of the room.
   * @param world The name of the world.
   * @param player The UUID of the player.
   * @return True if the player was added.
   */
  boolean addRoomMember(String name, String world, UUID player);

  /**
   * Attempts to remove a player from a room.
   * @param name The name of the room.
   * @param player The UUID of the player.
   * @return True if the player was removed.
   */
  boolean removeRoomMember(String name, UUID player);

  /**
   * Attempts to remove a player from a room.
   * @param name The name of the room.
   * @param world The name of the world.
   * @param player The UUID of the player.
   * @return True if the player was removed.
   */
  boolean removeRoomMember(String name, String world, UUID player);

  /**
   * Attempts to create a chat room.
   * @param name The name of the room.
   * @param world The name of the world.
   * @param owner The UUID of the room owner.
   * @return True if the room was created, otherwise false.
   */
  boolean createRoom(String name, String world, UUID owner);

  /**
   * Attempts to create a chat room.
   * @param name The name of the room.
   * @param world The name of the world.
   * @param owner The UUID of the room owner.
   * @param type The {@link RoomType type} of this chat room.
   * @return True if the room was created, otherwise false.
   */
  boolean createRoom(String name, String world, UUID owner, RoomType type);
}