package net.tnemc.core.economy;

import net.tnemc.core.economy.currency.Currency;
import org.bukkit.World;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Created by creatorfromhell on 2/26/2018.
 * <p>
 * Reserve API
 * <p>
 * Copyright (C) 2018 creatorfromhell
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
public interface EconomyAPI {

  /**
   * @return The name of the Economy implementation.
   */
  String name();

  /**
   * @return The version of Reserve the Economy implementation supports.
   */
  String version();

  /**
   * @return Whether or not this implementation is enabled.
   */
  boolean enabled();

  /**
   * @return Whether or not this implementation should have a default Vault implementation.
   */
  default boolean vault() {
    return true;
  }

  /**
   * Used to get the plural name of the default currency.
   * @return The plural name of the default currency.
   */
  String currencyDefaultPlural();

  /**
   * Used to get the singular name of the default currency.
   * @return The plural name of the default currency.
   */
  String currencyDefaultSingular();

  /**
   * Used to get the plural name of the default currency for a world.
   * @param world The world to be used in this check.
   * @return The plural name of the default currency.
   */
  String currencyDefaultPlural(String world);

  /**
   * Used to get the singular name of the default currency for a world.
   * @param world The world to be used in this check.
   * @return The plural name of the default currency.
   */
  String currencyDefaultSingular(String world);

  /**
   * Checks to see if a {@link Currency} exists with this name.
   * @param name The name of the {@link Currency} to search for.
   * @return True if the currency exists, else false.
   */
  boolean hasCurrency(String name);

  /**
   * Checks to see if a {@link Currency} exists with this name.
   * @param name The name of the {@link Currency} to search for.
   * @param world The name of the {@link World} to check for this {@link Currency} in.
   * @return True if the currency exists, else false.
   */
  boolean hasCurrency(String name, String world);

  /**
   * Checks to see if a {@link Currency} exists with this name.
   * @param name The name of the {@link Currency} to search for.
   * @return True if the currency exists, else false.
   */
  CompletableFuture<Boolean> asyncHasCurrency(String name);

  /**
   * Checks to see if a {@link Currency} exists with this name.
   * @param name The name of the {@link Currency} to search for.
   * @param world The name of the {@link World} to check for this {@link Currency} in.
   * @return True if the currency exists, else false.
   */
  CompletableFuture<Boolean> asyncHasCurrency(String name, String world);

  /**
   * Checks to see if an account exists for this identifier. This method should be used for non-player accounts.
   * @param identifier The identifier of the account.
   * @return True if an account exists for this player, else false.
   */
  boolean hasAccount(String identifier);

  /**
   * Checks to see if an account exists for this identifier. This method should be used for player accounts.
   * @param identifier The {@link UUID} of the account.
   * @return True if an account exists for this player, else false.
   */
  boolean hasAccount(UUID identifier);

  /**
   * Checks to see if an account exists for this identifier. This method should be used for non-player accounts.
   * @param identifier The identifier of the account.
   * @return True if an account exists for this player, else false.
   */
  CompletableFuture<Boolean> asyncHasAccount(String identifier);

  /**
   * Checks to see if an account exists for this identifier. This method should be used for player accounts.
   * @param identifier The {@link UUID} of the account.
   * @return True if an account exists for this player, else false.
   */
  CompletableFuture<Boolean> asyncHasAccount(UUID identifier);

  /**
   * Attempts to create an account for this identifier. This method should be used for non-player accounts.
   * @param identifier The identifier of the account.
   * @return True if an account was created, else false.
   */
  boolean createAccount(String identifier);

  /**
   * Attempts to create an account for this identifier. This method should be used for player accounts.
   * @param identifier The {@link UUID} of the account.
   * @return True if an account was created, else false.
   */
  boolean createAccount(UUID identifier);

  /**
   * Attempts to create an account for this identifier. This method should be used for non-player accounts.
   * @param identifier The identifier of the account.
   * @return True if an account was created, else false.
   */
  CompletableFuture<Boolean> asyncCreateAccount(String identifier);

  /**
   * Attempts to create an account for this identifier. This method should be used for player accounts.
   * @param identifier The {@link UUID} of the account.
   * @return True if an account was created, else false.
   */
  CompletableFuture<Boolean> asyncCreateAccount(UUID identifier);

  /**
   * Attempts to delete an account for this identifier. This method should be used for non-player accounts.
   * @param identifier The identifier of the account.
   * @return True if an account was deleted, else false.
   */
  boolean deleteAccount(String identifier);

  /**
   * Attempts to delete an account for this identifier. This method should be used for player accounts.
   * @param identifier The {@link UUID} of the account.
   * @return True if an account was deleted, else false.
   */
  boolean deleteAccount(UUID identifier);

  /**
   * Attempts to delete an account for this identifier. This method should be used for non-player accounts.
   * @param identifier The identifier of the account.
   * @return True if an account was deleted, else false.
   */
  CompletableFuture<Boolean> asyncDeleteAccount(String identifier);

  /**
   * Attempts to delete an account for this identifier. This method should be used for player accounts.
   * @param identifier The {@link UUID} of the account.
   * @return True if an account was deleted, else false.
   */
  CompletableFuture<Boolean> asyncDeleteAccount(UUID identifier);

  /**
   * Determines whether or not a player is able to access this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(String identifier, String accessor);

  /**
   * Determines whether or not a player is able to access this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(String identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to access this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(UUID identifier, String accessor);

  /**
   * Determines whether or not a player is able to access this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(UUID identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  boolean canWithdraw(String identifier, String accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  boolean canWithdraw(String identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  boolean canWithdraw(UUID identifier, String accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  boolean canWithdraw(UUID identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  CompletableFuture<Boolean> asyncCanWithdraw(String identifier, String accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  CompletableFuture<Boolean> asyncCanWithdraw(String identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  CompletableFuture<Boolean> asyncCanWithdraw(UUID identifier, String accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  CompletableFuture<Boolean> asyncCanWithdraw(UUID identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  boolean canDeposit(String identifier, String accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  boolean canDeposit(String identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  boolean canDeposit(UUID identifier, String accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  boolean canDeposit(UUID identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  CompletableFuture<Boolean> asyncCanDeposit(String identifier, String accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  CompletableFuture<Boolean> asyncCanDeposit(String identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  CompletableFuture<Boolean> asyncCanDeposit(UUID identifier, String accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  CompletableFuture<Boolean> asyncCanDeposit(UUID identifier, UUID accessor);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(String identifier);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(UUID identifier);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(String identifier, String world);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(UUID identifier, String world);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @param currency The {@link Currency} associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(String identifier, String world, String currency);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @param currency The {@link Currency} associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(UUID identifier, String world, String currency);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  CompletableFuture<BigDecimal> asyncGetHoldings(String identifier);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  CompletableFuture<BigDecimal> asyncGetHoldings(UUID identifier);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @return The balance of the account.
   */
  CompletableFuture<BigDecimal> asyncGetHoldings(String identifier, String world);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @return The balance of the account.
   */
  CompletableFuture<BigDecimal> asyncGetHoldings(UUID identifier, String world);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @param currency The {@link Currency} associated with the balance.
   * @return The balance of the account.
   */
  CompletableFuture<BigDecimal> asyncGetHoldings(String identifier, String world, String currency);

  /**
   * Used to get the balance of an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @param currency The {@link Currency} associated with the balance.
   * @return The balance of the account.
   */
  CompletableFuture<BigDecimal> asyncGetHoldings(UUID identifier, String world, String currency);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(String identifier, BigDecimal amount);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  CompletableFuture<Boolean> asyncHasHoldings(String identifier, BigDecimal amount);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  CompletableFuture<Boolean> asyncHasHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  CompletableFuture<Boolean> asyncHasHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  CompletableFuture<Boolean> asyncHasHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  CompletableFuture<Boolean> asyncHasHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  CompletableFuture<Boolean> asyncHasHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(String identifier, BigDecimal amount);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncSetHoldings(String identifier, BigDecimal amount);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncSetHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncSetHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncSetHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were set for the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncSetHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to set the funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were set for the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncSetHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(String identifier, BigDecimal amount);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncAddHoldings(String identifier, BigDecimal amount);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncAddHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncAddHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncAddHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncAddHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to add funds to an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncAddHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(String identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanAddHoldings(String identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanAddHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanAddHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanAddHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanAddHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanAddHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(String identifier, BigDecimal amount);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncRemoveHoldings(String identifier, BigDecimal amount);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncRemoveHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncRemoveHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncRemoveHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncRemoveHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to remove funds from an account.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncRemoveHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(String identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanRemoveHoldings(String identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanRemoveHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanRemoveHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanRemoveHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanRemoveHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanRemoveHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   *
   * @return True if the funds were transferred.
   */
  default boolean transferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount) {
    return removeHoldings(fromIdentifier, amount) && addHoldings(toIdentifier, amount);
  }

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were transferred.
   */
  default boolean transferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world) {
    return removeHoldings(fromIdentifier, amount, world) && addHoldings(toIdentifier, amount, world);
  }

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were transferred.
   */
  default boolean transferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    return removeHoldings(fromIdentifier, amount, world, currency) && addHoldings(toIdentifier, amount, world, currency);
  }

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were transferred.
   */
  default boolean transferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount) {
    return removeHoldings(fromIdentifier, amount) && addHoldings(toIdentifier, amount);
  }

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were transferred.
   */
  default boolean transferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world) {
    return removeHoldings(fromIdentifier, amount, world) && addHoldings(toIdentifier, amount, world);
  }

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were transferred.
   */
  default boolean transferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency) {
    return removeHoldings(fromIdentifier, amount, world, currency) && addHoldings(toIdentifier, amount, world, currency);
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   *
   * @return True if the funds were transferred.
   */
  CompletableFuture<Boolean> asyncTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount);

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were transferred.
   */
  CompletableFuture<Boolean> asyncTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world);

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were transferred.
   */
  CompletableFuture<Boolean> asyncTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency);

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were transferred.
   */
  CompletableFuture<Boolean> asyncTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount);

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were transferred.
   */
  CompletableFuture<Boolean> asyncTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world);

  /**
   * Used to transfer funds from one account to another.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if the funds were transferred.
   */
  CompletableFuture<Boolean> asyncTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  default boolean canTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount) {
    return canRemoveHoldings(fromIdentifier, amount) && canAddHoldings(toIdentifier, amount);
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  default boolean canTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world) {
    return canRemoveHoldings(fromIdentifier, amount, world) && canAddHoldings(toIdentifier, amount, world);
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  default boolean canTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    return canRemoveHoldings(fromIdentifier, amount, world, currency) && canAddHoldings(toIdentifier, amount, world, currency);
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  default boolean canTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount) {
    return canRemoveHoldings(fromIdentifier, amount) && canAddHoldings(toIdentifier, amount);
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   *
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  default boolean canTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world) {
    return canRemoveHoldings(fromIdentifier, amount, world) && canAddHoldings(toIdentifier, amount, world);
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   *
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  default boolean canTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency) {
    return canRemoveHoldings(fromIdentifier, amount, world, currency) && canAddHoldings(toIdentifier, amount, world, currency);
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  CompletableFuture<Boolean> asyncCanTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   *
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  CompletableFuture<Boolean> asyncCanTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The {@link Currency} associated with the balance.
   *
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  CompletableFuture<Boolean> asyncCanTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if this implementation supports banks or not.
   *
   * @return True if banks are supported, otherwise false.
   */
  default boolean supportBanks() {
    return false;
  }

  /**
   * @return A list containing the names of the banks currently in the server.
   */
  List<String> getBanks();

  /**
   * @param world The name of the {@link World} to use for this call.
   * @return A list containing the names of the banks currently in the specified world
   */
  List<String> getBanks(String world);

  /**
   * Asynchronous version of getBanks()
   * @return A list containing the names of the banks currently in the server.
   */
  CompletableFuture<List<String>> asyncGetBanks();


  /**
   * Asynchronous version of getBanks(String world)
   * @param world The name of the {@link World} to use for this call.
   * @return A list containing the names of the banks currently in the specified world
   */
  CompletableFuture<List<String>> asyncGetBanks(String world);

  /**
   * @return A list of currencies that are able to be used with banks.
   */
  List<String> acceptedBankCurrencies();

  /**
   * @param world The name of the {@link World} to use for this call.
   * @return A list of currencies that are able to be used with banks in the specified world.
   */
  List<String> acceptedBankCurrencies(String world);

  /**
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return A list of currencies that are able to be used with the specified bank in the specified world.
   */
  List<String> acceptedBankCurrencies(String world, String bank);

  /**
   * @return A list of currencies that are able to be used with banks.
   */
  CompletableFuture<List<String>> asyncAcceptedBankCurrencies();

  /**
   * @param world The name of the {@link World} to use for this call.
   * @return A list of currencies that are able to be used with banks in the specified world.
   */
  CompletableFuture<List<String>> asyncAcceptedBankCurrencies(String world);

  /**
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return A list of currencies that are able to be used with the specified bank in the specified world.
   */
  CompletableFuture<List<String>> asyncAcceptedBankCurrencies(String world, String bank);

  /**
   * @param player The UUID of the player to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to.
   */
  List<UUID> availableBankAccounts(UUID player);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to in a specific world.
   */
  List<UUID> availableBankAccounts(UUID player, String world);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to in a specific bank in a specific world.
   */
  List<UUID> availableBankAccounts(UUID player, String world, String bank);

  /**
   * @param player The UUID of the player to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to.
   */
  CompletableFuture<List<UUID>> asyncAvailableBankAccounts(UUID player);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to in a specific world.
   */
  CompletableFuture<List<UUID>> asyncAvailableBankAccounts(UUID player, String world);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to in a specific bank in a specific world.
   */
  CompletableFuture<List<UUID>> asyncAvailableBankAccounts(UUID player, String world, String bank);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the specified player is the owner of a bank in the specified world.
   */
  boolean isBankOwner(UUID player, String world);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the specified player is the owner of the specified bank in the specified world.
   */
  boolean isBankOwner(UUID player, String world, String bank);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the specified player is the owner of a bank in the specified world.
   */
  CompletableFuture<Boolean> asyncIsBankOwner(UUID player, String world);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the specified player is the owner of the specified bank in the specified world.
   */
  CompletableFuture<Boolean> asyncIsBankOwner(UUID player, String world, String bank);

  /**
   * @param owner The UUID of the owner of this bank account.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  Optional<UUID> createBankAccount(UUID owner);

  /**
   * @param owner The UUID of the owner of this bank account.
   * @param world The name of the {@link World} to create this bank account in.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  Optional<UUID> createBankAccount(UUID owner, String world);

  /**
   * @param owner The UUID of the owner of this bank account.
   * @param world The name of the {@link World} to create this bank account in.
   * @param bank The name of the bank to create this bank account in.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  Optional<UUID> createBankAccount(UUID owner, String world, String bank);

  /**
   * @param owner The UUID of the owner of this bank account.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  CompletableFuture<Optional<UUID>> asyncCreateBankAccount(UUID owner);

  /**
   * @param owner The UUID of the owner of this bank account.
   * @param world The name of the {@link World} to create this bank account in.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  CompletableFuture<Optional<UUID>> asyncCreateBankAccount(UUID owner, String world);

  /**
   * @param owner The UUID of the owner of this bank account.
   * @param world The name of the {@link World} to create this bank account in.
   * @param bank The name of the bank to create this bank account in.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  CompletableFuture<Optional<UUID>> asyncCreateBankAccount(UUID owner, String world, String bank);

  /**
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player has a bank account.
   */
  boolean hasBankAccount(UUID player);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the specified player has a bank account in the specified world.
   */
  boolean hasBankAccount(UUID player, String world);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the specified player has a bank account in the specified bank in the specified world.
   */
  boolean hasBankAccount(UUID player, String world, String bank);

  /**
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player has a bank account.
   */
  CompletableFuture<Boolean> asyncHasBankAccount(UUID player);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the specified player has a bank account in the specified world.
   */
  CompletableFuture<Boolean> asyncHasBankAccount(UUID player, String world);

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the specified player has a bank account in the specified bank in the specified world.
   */
  CompletableFuture<Boolean> asyncHasBankAccount(UUID player, String world, String bank);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player is the owner of the specified bank account.
   */
  boolean isBankAccountOwner(UUID account, UUID player);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player is the owner of the specified bank account.
   */
  CompletableFuture<Boolean> asyncIsBankAccountOwner(UUID account, UUID player);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player is a member of the specified bank account.
   */
  boolean isBankAccountMember(UUID account, UUID player);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player is a member of the specified bank account.
   */
  CompletableFuture<Boolean> asyncIsBankAccountMember(UUID account, UUID player);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @return The balance of the bank account.
   */
  BigDecimal getBankHoldings(UUID account);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return The balance of the bank account.
   */
  BigDecimal getBankHoldings(UUID account, String world);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return The balance of the bank account.
   */
  BigDecimal getBankHoldings(UUID account, String world, String currency);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return The balance of the bank account.
   */
  BigDecimal getBankHoldings(UUID account, String world, String currency, String bank);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @return The balance of the bank account.
   */
  CompletableFuture<BigDecimal> asyncGetBankHoldings(UUID account);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return The balance of the bank account.
   */
  CompletableFuture<BigDecimal> asyncGetBankHoldings(UUID account, String world);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return The balance of the bank account.
   */
  CompletableFuture<BigDecimal> asyncGetBankHoldings(UUID account, String world, String currency);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return The balance of the bank account.
   */
  CompletableFuture<BigDecimal> asyncGetBankHoldings(UUID account, String world, String currency, String bank);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to add to the account.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean bankAddHoldings(UUID account, UUID player, BigDecimal amount);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean bankAddHoldings(UUID account, UUID player, BigDecimal amount, String world);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean bankAddHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean bankAddHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency, String bank);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to add to the account.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncBankAddHoldings(UUID account, UUID player, BigDecimal amount);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncBankAddHoldings(UUID account, UUID player, BigDecimal amount, String world);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncBankAddHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the funds were added to the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncBankAddHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency, String bank);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean bankRemoveHoldings(UUID account, UUID player, BigDecimal amount);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean bankRemoveHoldings(UUID account, UUID player, BigDecimal amount, String world);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean bankRemoveHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean bankRemoveHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency, String bank);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncBankRemoveHoldings(UUID account, UUID player, BigDecimal amount);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncBankRemoveHoldings(UUID account, UUID player, BigDecimal amount, String world);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncBankRemoveHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the funds were removed from the account, otherwise false.
   */
  CompletableFuture<Boolean> asyncBankRemoveHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency, String bank);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @return True if the account's funds were set to the specified amount.
   */
  boolean bankSetHoldings(UUID account, BigDecimal amount);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @return True if the account's funds were set to the specified amount.
   */
  boolean bankSetHoldings(UUID account, BigDecimal amount, String world);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return True if the account's funds were set to the specified amount.
   */
  boolean bankSetHoldings(UUID account, BigDecimal amount, String world, String currency);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the account's funds were set to the specified amount.
   */
  boolean bankSetHoldings(UUID account, BigDecimal amount, String world, String currency, String bank);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @return True if the account's funds were set to the specified amount.
   */
  CompletableFuture<Boolean> asyncBankSetHoldings(UUID account, BigDecimal amount);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @return True if the account's funds were set to the specified amount.
   */
  CompletableFuture<Boolean> asyncBankSetHoldings(UUID account, BigDecimal amount, String world);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return True if the account's funds were set to the specified amount.
   */
  CompletableFuture<Boolean> asyncBankSetHoldings(UUID account, BigDecimal amount, String world, String currency);

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the account's funds were set to the specified amount.
   */
  CompletableFuture<Boolean> asyncBankSetHoldings(UUID account, BigDecimal amount, String world, String currency, String bank);

  /**
   * Formats a monetary amount into a more text-friendly version.
   * @param amount The amount of currency to format.
   * @return The formatted amount.
   */
  String format(BigDecimal amount);

  /**
   * Formats a monetary amount into a more text-friendly version.
   * @param amount The amount of currency to format.
   * @param world The {@link World} in which this format operation is occurring.
   * @return The formatted amount.
   */
  String format(BigDecimal amount, String world);

  /**
   * Formats a monetary amount into a more text-friendly version.
   * @param amount The amount of currency to format.
   * @param world The {@link World} in which this format operation is occurring.
   * @param currency The {@link Currency} associated with the balance.
   * @return The formatted amount.
   */
  String format(BigDecimal amount, String world, String currency);

  /**
   * Purges the database of accounts with the default balance.
   * @return True if the purge was completed successfully.
   */
  boolean purgeAccounts();

  /**
   * Purges the database of accounts with a balance under the specified one.
   * @param amount The amount that an account's balance has to be under in order to be removed.
   * @return True if the purge was completed successfully.
   */
  boolean purgeAccountsUnder(BigDecimal amount);

  /**
   * Purges the database of accounts with the default balance.
   * @return True if the purge was completed successfully.
   */
  CompletableFuture<Boolean> asyncPurgeAccounts();

  /**
   * Purges the database of accounts with a balance under the specified one.
   * @param amount The amount that an account's balance has to be under in order to be removed.
   * @return True if the purge was completed successfully.
   */
  CompletableFuture<Boolean> asyncPurgeAccountsUnder(BigDecimal amount);

  /**
   * Whether or not this API Implementation supports the Transaction System.
   */
  default boolean supportTransactions() {
    return false;
  }
}
