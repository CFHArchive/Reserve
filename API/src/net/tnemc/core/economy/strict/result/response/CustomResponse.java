package net.tnemc.core.economy.strict.result.response;

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
public class CustomResponse implements EconomyResponse {

  private final boolean success;
  private final String response;

  /**
   * This is a helper class for Reserve implementations to return custom responses during API calls
   * that return an {@link EconomyResponse} object.
   *
   * @param success  Whether the action was performed successfully.
   * @param response The message to send describing the response. Example: "Action failed because account
   *                 doesn't exist"
   *
   * @since 1.0.0
   */
  public CustomResponse(final boolean success, final String response) {
    this.success = success;
    this.response = response;
  }

  @Override
  public boolean success() {
    return success;
  }

  @Override
  public String response() {
    return response;
  }
}