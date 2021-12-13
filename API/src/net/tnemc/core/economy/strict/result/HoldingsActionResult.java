package net.tnemc.core.economy.strict.result;


import net.tnemc.core.economy.strict.account.Account;
import net.tnemc.core.economy.strict.currency.Currency;
import net.tnemc.core.economy.strict.result.response.EconomyResponse;

import java.math.BigDecimal;
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

/**
 * Class that returns the result of an action performed on an {@link net.tnemc.core.economy.strict.account.Account}'s
 * holdings.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface HoldingsActionResult extends EconomyResponse {

  /**
   * The identifier of the action correlating to this action.
   *
   * @return The identifier of the type of action performed. This could be anything from deposit to
   * daily tax collection.
   *
   * @since 1.0.0
   */
  String actionType();

  /**
   * The {@link Account account} associated with the action.
   *
   * @return The {@link Account} that was affected by this action.
   *
   * @since 1.0.0
   */
  Account account();

  /**
   * The {@link Currency currency} object that was used in this action.
   *
   * @return The currency object that was used during this action.
   *
   * @since 1.0.0
   */
  Currency currency();

  /**
   * Returns the resulting holdings after this action was performed for the specified {@link Account account}.
   *
   * @return The resulting holdings for the {@link Account} that was affected by this action.
   *
   * @since 1.0.0
   */
  BigDecimal holdings();

  /**
   * The response correlating with the action result.
   *
   * @return The {@link EconomyResponse response} for this action's result.
   *
   * @since 1.0.0
   */
  EconomyResponse result();
}