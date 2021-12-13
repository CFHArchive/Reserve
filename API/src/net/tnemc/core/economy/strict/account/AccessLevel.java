package net.tnemc.core.economy.strict.account;
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
 * Represents the various levels of access that may be possed for an {@link Account}.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public enum AccessLevel {

  /**
   * This means the specified user is able to check holdings for the account.
   *
   * @since 1.0.0
   */
  BALANCE,

  /**
   * This means the specified user is able to withdraw holdings from the account.
   *
   * @since 1.0.0
   */
  WITHDRAW,

  /**
   * This means the specified user is able to deposit holdings to this account.
   * @since 1.0.0
   */
  DEPOSIT

}
