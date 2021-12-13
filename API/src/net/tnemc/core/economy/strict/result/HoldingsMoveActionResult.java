package net.tnemc.core.economy.strict.result;

import net.tnemc.core.economy.strict.account.Account;
import net.tnemc.core.economy.strict.currency.Currency;

import java.util.Set;
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
 * holdings. Unlike {@link HoldingsActionResult} this only applies to actions that involve moving
 * holdings from one {@link net.tnemc.core.economy.strict.account.Account} to another.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface HoldingsMoveActionResult {

  /**
   * @return The {@link Account} that received the holdings during the action.
   *
   * @since 1.0.0
   */
  Account receivingAccount();

  /**
   * @return The {@link Currency} the holdings were received in.
   *
   * @since 1.0.0
   */
  Currency receivedCurrency();
}