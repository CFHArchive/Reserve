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
public enum HoldingsResponse implements EconomyResponse {

  /**
   * The action was unsuccessful due to the account going over the max supported holdings.
   *
   * @since 1.0.0
   */
  MAX_HOLDINGS {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The holdings change would put this account above the max allowed holdings.";
    }
  },

  /**
   * The action was unsuccessful due to the account going below the minimum holdings.
   *
   * @since 1.0.0
   */
  MIN_HOLDINGS {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The holdings change would put this account below the minimum allowed holdings.";
    }
  },

  /**
   * The action was unsuccessful due to the account not having enough funds.
   *
   * @since 1.0.0
   */
  INSUFFICIENT {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The specified account has insufficient funds.";
    }
  }
}
