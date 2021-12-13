package net.tnemc.core.economy.strict.account;

import com.sun.istack.internal.NotNull;
import net.tnemc.core.economy.strict.currency.Currency;
import net.tnemc.core.economy.strict.result.HoldingsActionResult;
import net.tnemc.core.economy.strict.result.HoldingsMoveActionResult;
import net.tnemc.core.generic.Identifiable;
import net.tnemc.core.generic.source.ActionSource;

import java.math.BigDecimal;
import java.util.Map;
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
 * An object that represents an economy account.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface Account extends Identifiable {

  BigDecimal holdings(@NotNull String world);


  BigDecimal holdings(@NotNull String world, @NotNull Currency currency);

  boolean has(@NotNull String world, @NotNull BigDecimal amount);


  boolean has(@NotNull String world, @NotNull Currency currency, @NotNull BigDecimal amount);


  Map<Currency, BigDecimal> getMultiHoldings(@NotNull String world);


  HoldingsActionResult deposit(@NotNull BigDecimal amount, @NotNull ActionSource source);


  HoldingsActionResult deposit(@NotNull BigDecimal amount, @NotNull Currency currency, @NotNull ActionSource source);


  HoldingsActionResult deposit(@NotNull BigDecimal amount, @NotNull String world, @NotNull Currency currency, @NotNull ActionSource source);


  HoldingsMoveActionResult transfer(@NotNull Account receiving, @NotNull BigDecimal amount, @NotNull ActionSource source);


  HoldingsMoveActionResult transfer(@NotNull Account receiving, @NotNull BigDecimal amount, @NotNull Currency currency, @NotNull ActionSource source);


  HoldingsMoveActionResult transfer(@NotNull Account receiving, @NotNull BigDecimal amount, @NotNull String world, @NotNull Currency currency, @NotNull ActionSource source);


  HoldingsActionResult withdraw(@NotNull BigDecimal amount, @NotNull ActionSource source);


  HoldingsActionResult withdraw(@NotNull BigDecimal amount, @NotNull Currency currency, @NotNull ActionSource source);


  HoldingsActionResult withdraw(@NotNull BigDecimal amount, @NotNull String world, @NotNull Currency currency, @NotNull ActionSource source);
}