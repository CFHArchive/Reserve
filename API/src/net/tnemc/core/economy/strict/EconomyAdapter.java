package net.tnemc.core.economy.strict;

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

import com.sun.istack.internal.NotNull;
import net.tnemc.core.economy.strict.account.Account;
import net.tnemc.core.economy.strict.account.UniqueAccount;
import net.tnemc.core.economy.strict.currency.Currency;
import net.tnemc.core.economy.strict.result.response.EconomyResponse;
import net.tnemc.core.generic.source.ActionSource;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * This represents an implementation of our Economy API. This holds all methods to begin accessing
 * the API and is what should be registered through the ServiceManager for the corresponding Minecraft
 * API(be it Bukkit, sponge, etc).
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface EconomyAdapter {

  /**
   * This is used to determine if Reserve should register a Vault provider for a given {@link EconomyAdapter}.
   * 
   * @return True if this provider should automatically register a Vault provider. This removes the
   * requirement for you to register a Vault and Reserve Economy Provider.
   * 
   * @since 1.0.0
   */
  boolean vault();

  /**
   * This is used to determine if this {@link EconomyAdapter} should override an already registered
   * adapter or not.
   *
   * @return False if this provider should not be registered if an {@link EconomyAdapter} already is,
   * otherwise return true to override already registered providers.
   *
   * @since 1.0.0
   */
  boolean override();

  /**
   * This is the name of this {@link EconomyAdapter adapter}.
   *
   * @return The name of this {@link EconomyAdapter} implementation.
   *
   * @since 1.0.0
   */
  String name();

  /**
   * The priority of this Adapater in relation to other economy adapters.
   * @return The priority that this adapter is in relation to other adapters on a scale of 1 to 5,
   * where 1 is the lowest and 5 is the highest.
   *
   * @since 1.0.0
   */
  int priority();

  /**
   * Used to determine if an {@link Account} exists with the specified identifier.
   *
   * This method is safe to search for non-player accounts.
   *
   * @param identifier The string identifier for the account that is being looked for.
   *
   * @return True if an account with the specified identifier exists, otherwise false.
   *
   * @since 1.0.0
   */
  boolean hasAccount(@NotNull String identifier);

  /**
   * Used to determine if an {@link Account} exists with the specified identifier.
   *
   * This method is not safe to search for non-player accounts.
   *
   * @param identifier The {@link UUID identifier} for the account that is being looked for.
   *
   * @return True if an account with the specified identifier exists, otherwise false.
   *
   * @since 1.0.0
   */
  boolean hasAccount(@NotNull UUID identifier);

  /**
   * Looks for an account based on the provided identifier and if none is found, creates it.
   *
   * This method could return either an {@link net.tnemc.core.economy.strict.account.GenericAccount GenericAccount}
   * or an {@link net.tnemc.core.economy.strict.account.UniqueAccount UniqueAccount}.
   *
   * This method is safe to search for non-player accounts.
   *
   * @param identifier The string identifier for the account that is being looked for.
   *
   * @return The correlating {@link Account account} object if found, otherwise the one created.
   *
   * @since 1.0.0
   */
  Account getOrCreateAccount(@NotNull String identifier);

  /**
   * Looks for an account based on the provided identifier and if none is found, creates it.
   *
   * This method returns an {@link net.tnemc.core.economy.strict.account.UniqueAccount UniqueAccount}.
   *
   * This method is not safe to search for non-player accounts.
   *
   * @param identifier The {@link UUID} identifier for the account that is being looked for.
   *
   * @return The correlating {@link UniqueAccount account} object if found, otherwise the one created.
   *
   * @since 1.0.0
   */
  UniqueAccount getOrCreateAccount(@NotNull UUID identifier);

  /**
   * Attempts to create an account with the given identifier. This method returns true if the account
   * was created, otherwise false.
   *
   * This method is intended for non-player accounts.
   *
   * @param identifier The String identifier for the account that is being created.
   *
   * @return True if the account was created. If the account was not created this returns false.
   *
   * @since 1.0.0
   */
  boolean createAccount(@NotNull String identifier);

  /**
   * Attempts to create an account with the given identifier. This method returns true if the account
   * was created, otherwise false.
   *
   * This method is not intended for non-player accounts.
   *
   * @param identifier The {@link UUID} identifier for the account that is being created.
   * @param name The String representation of the name for the account being created, usually the username
   *             of the player.
   *
   * @return True if the account was created. If the account was not created this returns false.
   *
   * @since 1.0.0
   */
  boolean createAccount(@NotNull UUID identifier, @NotNull String name);

  /**
   * Looks for an account based on the provided identifier.
   *
   * This method could return either an {@link net.tnemc.core.economy.strict.account.GenericAccount GenericAccount}
   * or an {@link net.tnemc.core.economy.strict.account.UniqueAccount UniqueAccount}.
   *
   * This method is safe to search for non-player accounts.
   *
   * @param identifier The string identifier for the account that is being looked for.
   * @return An optional containing the {@link Account} if found, otherwise an empty optional.
   *
   * @since 1.0.0
   */
  Optional<Account> getAccount(@NotNull String identifier);

  /**
   * Looks for an {@link net.tnemc.core.economy.strict.account.UniqueAccount} based on the provided
   * {@link UUID identifier}.
   *
   * This method is not safe to search for non-player accounts.
   *
   * @param identifier The {@link UUID identifier} for the account that is being looked for.
   * @return An optional containing the {@link net.tnemc.core.economy.strict.account.UniqueAccount}
   * if found, otherwise an empty optional.
   *
   * @since 1.0.0
   */
  Optional<UniqueAccount> getAccount(@NotNull UUID identifier);

  /**
   * Used to delete the specified account.
   *
   * This method is safe to search for non-player accounts.
   *
   * @param identifier The identifier associated with the account that you wish to delete.
   * @param source The {@link ActionSource source} response for this deletion call.
   *
   * @return The {@link EconomyResponse response} that should be returned based on the deletion action.
   *
   * @since 1.0.0
   */
  EconomyResponse deleteAccount(@NotNull String identifier, @NotNull ActionSource source);

  /**
   * Used to delete the specified account.
   *
   * This method is not safe to search for non-player accounts.
   *
   * @param identifier The identifier associated with the account that you wish to delete.
   * @param source The {@link ActionSource source} response for this deletion call.
   *
   * @return The {@link EconomyResponse response} that should be returned based on the deletion action.
   *
   * @since 1.0.0
   */
  EconomyResponse deleteAccount(@NotNull UUID identifier, @NotNull ActionSource source);

  /**
   * Used to get the default currency. This could be the default currency for the server globally or
   * for the default world if the implementation supports multi-world.
   * @return The currency that is the default for the server if multi-world support is not available
   * otherwise the default for the default world.
   *
   * @since 1.0.0
   */
  @NotNull
  Currency getDefaultCurrency();

  /**
   * Used to get the default currency for the specified world if this implementation has multi-world
   * support, otherwise the default currency for the server.
   * @param world The world to get the default currency for.
   * @return The default currency for the specified world if this implementation has multi-world
   * support, otherwise the default currency for the server.
   *
   * @since 1.0.0
   */
  @NotNull
  Currency getDefaultCurrency(@NotNull String world);

  /**
   * Used to get a set of every  {@link Currency} object for the server.
   * @return A set of every {@link Currency} object that is available for the server.
   *
   * @since 1.0.0
   */
  Set<Currency> getCurrencies();

  /**
   * Used to get a set of every {@link Currency} object that is available in the specified world if
   * this implementation has multi-world support, otherwise all {@link Currency} objects for the server.
   * @param world The world we want to get the {@link Currency} objects for.
   * @return A set of every {@link Currency} object that is available in the specified world if
   * this implementation has multi-world support, otherwise all {@link Currency} objects for the server.
   *
   * @since 1.0.0
   */
  Set<Currency> getCurrencies(@NotNull String world);
}