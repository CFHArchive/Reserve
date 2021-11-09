package net.tnemc.core.general.response;

/**
 * Created by creatorfromhell on 8/9/2017.
 *
 * Reserve API
 *
 * Copyright (C) 2021 creatorfromhell
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA, or see
 * it at <https://www.gnu.org/licenses/lgpl-3.0.txt>.
 **/
public interface PluginResponse {

  /**
   * @return True if the associated action was performed correctly.
   */
  boolean success();

  /**
   * @return The string to return to the performer of the action.
   */
  default String response() {
    return "Default response string";
  }

  /**
   * @return The string to return to the recipient of the action. I.E. the recipient of the payer.
   */
  default String responseRecipient() {
    return "Default response string";
  }
}