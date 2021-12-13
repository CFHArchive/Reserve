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
public enum GeneralResponse implements EconomyResponse {

  /**
   * The action was successfully performed on the account's holdings.
   *
   * @since 1.0.0
   */
  SUCCESS {
    @Override
    public boolean success() {
      return true;
    }

    @Override
    public String response() {
      return "The action was successful.";
    }
  },

  /**
   * The access was unsuccessful.
   *
   * @since 1.0.0
   */
  FAILED {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The action was unsuccessful.";
    }
  },

  /**
   * The action was unsuccessful due to the Reserve implementation not supporting it.
   *
   * @since 1.0.0
   */
  UNSUPPORTED {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "This action is not supported by this Reserve implementation.";
    }
  }
}