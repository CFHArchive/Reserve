package net.tnemc.core.economy.strict.currency;

import com.sun.istack.internal.NotNull;

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
 * Represents a currency object.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface Currency {

  /**
   * @return A unique non-user friendly identifier for the currency.
   *
   * @since 1.0.0
   */
  String identifier();

  /**
   * @return The currency's symbol.
   *
   * @since 1.0.0
   */
  String symbol();

  /**
   * @return The currency's decimal character.
   *
   * @since 1.0.0
   */
  char decimal();

  /**
   * @return The currency's user-friendly display name.
   *
   * @since 1.0.0
   */
  String display();

  /**
   * @return The plural form of the currency's user-friendly display name.
   *
   * @since 1.0.0
   */
  String displayPlural();

  /**
   * @return The currency's default number of decimal digits when formatting this currency.
   *
   * @since 1.0.0
   */
  int precision();

  /**
   * @return Whether this currency is the default currency. This method should use a global
   * context if multi-world support is not present, otherwise it should use the default world
   * for this check.
   *
   * @since 1.0.0
   */
  boolean isDefault();

  /**
   *
   * @param world The name of the world to use for the check.
   *
   * @return Whether this currency is the default currency for the specified world. This
   * method should default to a global context if multi-world support is not present. If the world
   * does not exist, this should default to the default world.
   *
   * @since 1.0.0
   */
  boolean isDefault(@NotNull String world);
  /**
   * Used to translate an amount to a user readable format with the default precision.
   *
   * @param amount The amount to format.
   *
   * @return The formatted text.
   *
   * @since 1.0.0
   */
  String format(@NotNull BigDecimal amount);

  /**
   * Used to translate an amount to a user readable format with the specified amount of decimal places.
   *
   * @param amount The amount to format.
   * @param precision The amount of decimal digits to use when formatting.
   *
   * @return The formatted text.
   *
   * @since 1.0.0
   */
  String format(@NotNull BigDecimal amount, @NotNull int precision);
}