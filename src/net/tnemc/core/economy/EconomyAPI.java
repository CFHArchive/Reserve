package net.tnemc.core.economy;

import net.tnemc.core.economy.response.EconomyResponse;
import net.tnemc.core.economy.response.GeneralResponse;
import org.bukkit.World;

import java.math.BigDecimal;
import java.util.ArrayList;
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
   * @return True if this implementation should override other economy implementations.
   */
  default boolean force() {
    return true;
  }

  /**
   * @return Whether or not this implementation should have a default Vault implementation.
   */
  default boolean vault() {
    return true;
  }

  /**
   * Used to get the plural name of the default currency.
   *
   * @return The plural name of the default currency.
   */
  String currencyDefaultPlural();

  /**
   * Used to get the singular name of the default currency.
   *
   * @return The plural name of the default currency.
   */
  String currencyDefaultSingular();

  /**
   * Used to get the plural name of the default currency for a world.
   *
   * @param world The world to be used in this check.
   * @return The plural name of the default currency.
   */
  String currencyDefaultPlural(String world);

  /**
   * Used to get the singular name of the default currency for a world.
   *
   * @param world The world to be used in this check.
   * @return The plural name of the default currency.
   */
  String currencyDefaultSingular(String world);

  /**
   * Checks to see if a name of the currency exists with this name.
   *
   * @param name The name of the name of the currency to search for.
   * @return True if the currency exists, else false.
   */
  boolean hasCurrency(String name);

  /**
   * Checks to see if a name of the currency exists with this name.
   *
   * @param name The name of the name of the currency to search for.
   * @param world The name of the {@link World} to check for this name of the currency in.
   * @return True if the currency exists, else false.
   */
  boolean hasCurrency(String name, String world);

  /**
   * Checks to see if a name of the currency exists with this name.
   *
   * @param name The name of the name of the currency to search for.
   * @return True if the currency exists, else false.
   */
  default CompletableFuture<Boolean> asyncHasCurrency(String name) {
    return CompletableFuture.supplyAsync(() -> hasCurrency(name));
  }

  /**
   * Checks to see if a name of the currency exists with this name.
   *
   * @param name The name of the name of the currency to search for.
   * @param world The name of the {@link World} to check for this name of the currency in.
   * @return True if the currency exists, else false.
   */
  default CompletableFuture<Boolean> asyncHasCurrency(String name, String world) {
    return CompletableFuture.supplyAsync(() -> hasCurrency(name, world));
  }

  /**
   * Checks to see if an account exists for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return True if an account exists for this player, else false.
   */
  default boolean hasAccount(String identifier) {
    return hasAccountDetail(identifier).success();
  }

  /**
   * Checks to see if an account exists for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return True if an account exists for this player, else false.
   */
  default boolean hasAccount(UUID identifier) {
    return hasAccountDetail(identifier).success();
  }

  /**
   * Checks to see if an account exists for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return True if an account exists for this player, else false.
   */
  default CompletableFuture<Boolean> asyncHasAccount(String identifier) {
    return CompletableFuture.supplyAsync(() -> hasAccountDetail(identifier).success());
  }

  /**
   * Checks to see if an account exists for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return True if an account exists for this player, else false.
   */
  default CompletableFuture<Boolean> asyncHasAccount(UUID identifier) {
    return CompletableFuture.supplyAsync(() -> hasAccountDetail(identifier).success());
  }

  /**
   * Checks to see if an account exists for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse hasAccountDetail(String identifier);

  /**
   * Checks to see if an account exists for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse hasAccountDetail(UUID identifier);

  /**
   * Checks to see if an account exists for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncHasAccountDetail(String identifier) {
    return CompletableFuture.supplyAsync(() -> hasAccountDetail(identifier));
  }

  /**
   * Checks to see if an account exists for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncHasAccountDetail(UUID identifier) {
    return CompletableFuture.supplyAsync(() -> hasAccountDetail(identifier));
  }

  /**
   * Attempts to create an account for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return True if an account was created, else false.
   */
  default boolean createAccount(String identifier) {
    return createAccountDetail(identifier).success();
  }

  /**
   * Attempts to create an account for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return True if an account was created, else false.
   */
  default boolean createAccount(UUID identifier) {
    return createAccountDetail(identifier).success();
  }

  /**
   * Attempts to create an account for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return True if an account was created, else false.
   */
  default CompletableFuture<Boolean> asyncCreateAccount(String identifier) {
    return CompletableFuture.supplyAsync(() -> createAccountDetail(identifier).success());
  }

  /**
   * Attempts to create an account for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return True if an account was created, else false.
   */
  default CompletableFuture<Boolean> asyncCreateAccount(UUID identifier) {
    return CompletableFuture.supplyAsync(() -> createAccountDetail(identifier).success());
  }

  /**
   * Attempts to create an account for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse createAccountDetail(String identifier);

  /**
   * Attempts to create an account for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse createAccountDetail(UUID identifier);

  /**
   * Attempts to create an account for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncCreateAccountDetail(String identifier) {
    return CompletableFuture.supplyAsync(() -> createAccountDetail(identifier));
  }

  /**
   * Attempts to create an account for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncCreateAccountDetail(UUID identifier) {
    return CompletableFuture.supplyAsync(() -> createAccountDetail(identifier));
  }

  /**
   * Attempts to delete an account for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return True if an account was deleted, else false.
   */
  default boolean deleteAccount(String identifier) {
    return deleteAccountDetail(identifier).success();
  }

  /**
   * Attempts to delete an account for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return True if an account was deleted, else false.
   */
  default boolean deleteAccount(UUID identifier) {
    return deleteAccountDetail(identifier).success();
  }

  /**
   * Attempts to delete an account for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return True if an account was deleted, else false.
   */
  default CompletableFuture<Boolean> asyncDeleteAccount(String identifier) {
    return CompletableFuture.supplyAsync(() -> deleteAccountDetail(identifier).success());
  }

  /**
   * Attempts to delete an account for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return True if an account was deleted, else false.
   */
  default CompletableFuture<Boolean> asyncDeleteAccount(UUID identifier) {
    return CompletableFuture.supplyAsync(() -> deleteAccountDetail(identifier).success());
  }

  /**
   * Attempts to delete an account for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse deleteAccountDetail(String identifier);

  /**
   * Attempts to delete an account for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse deleteAccountDetail(UUID identifier);

  /**
   * Attempts to delete an account for this identifier. This method should be used for non-player accounts.
   *
   * @param identifier The identifier of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncDeleteAccountDetail(String identifier) {
    return CompletableFuture.supplyAsync(() -> deleteAccountDetail(identifier));
  }

  /**
   * Attempts to delete an account for this identifier. This method should be used for player accounts.
   *
   * @param identifier The {@link UUID} of the account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncDeleteAccountDetail(UUID identifier) {
    return CompletableFuture.supplyAsync(() -> deleteAccountDetail(identifier));
  }

  /**
   * Determines whether or not a player is able to access this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(String identifier, String accessor);

  /**
   * Determines whether or not a player is able to access this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(String identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to access this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(UUID identifier, String accessor);

  /**
   * Determines whether or not a player is able to access this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(UUID identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  default boolean canWithdraw(String identifier, String accessor) {
    return canWithdrawDetail(identifier, accessor).success();
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  default boolean canWithdraw(String identifier, UUID accessor) {
    return canWithdrawDetail(identifier, accessor).success();
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  default boolean canWithdraw(UUID identifier, String accessor) {
    return canWithdrawDetail(identifier, accessor).success();
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  default boolean canWithdraw(UUID identifier, UUID accessor) {
    return canWithdrawDetail(identifier, accessor).success();
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canWithdrawDetail(String identifier, String accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canWithdrawDetail(String identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canWithdrawDetail(UUID identifier, String accessor);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canWithdrawDetail(UUID identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  default boolean canDeposit(String identifier, String accessor) {
    return canDepositDetail(identifier, accessor).success();
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  default boolean canDeposit(String identifier, UUID accessor) {
    return canDepositDetail(identifier, accessor).success();
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  default boolean canDeposit(UUID identifier, String accessor) {
    return canDepositDetail(identifier, accessor).success();
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  default boolean canDeposit(UUID identifier, UUID accessor) {
    return canDepositDetail(identifier, accessor).success();
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canDepositDetail(String identifier, String accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canDepositDetail(String identifier, UUID accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canDepositDetail(UUID identifier, String accessor);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor The identifier of the user attempting to access this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canDepositDetail(UUID identifier, UUID accessor);

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(String identifier);

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(UUID identifier);

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(String identifier, String world);

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(UUID identifier, String world);

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @param currency The name of the currency associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(String identifier, String world, String currency);

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param world The name of the {@link World} associated with the balance.
   * @param currency The name of the currency associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(UUID identifier, String world, String currency);

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(String identifier, BigDecimal amount);

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(UUID identifier, BigDecimal amount);

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(String identifier, BigDecimal amount, String world);

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  default boolean setHoldings(String identifier, BigDecimal amount) {
    return setHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  default boolean setHoldings(UUID identifier, BigDecimal amount) {
    return setHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  default boolean setHoldings(String identifier, BigDecimal amount, String world) {
    return setHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  default boolean setHoldings(UUID identifier, BigDecimal amount, String world) {
    return setHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were set for the account, otherwise false.
   */
  default boolean setHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return setHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were set for the account, otherwise false.
   */
  default boolean setHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return setHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse setHoldingsDetail(String identifier, BigDecimal amount);

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse setHoldingsDetail(UUID identifier, BigDecimal amount);

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse setHoldingsDetail(String identifier, BigDecimal amount, String world);

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse setHoldingsDetail(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse setHoldingsDetail(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse setHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean addHoldings(String identifier, BigDecimal amount) {
    return addHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean addHoldings(UUID identifier, BigDecimal amount) {
    return addHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean addHoldings(String identifier, BigDecimal amount, String world) {
    return addHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean addHoldings(UUID identifier, BigDecimal amount, String world) {
    return addHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean addHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return addHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean addHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return addHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse addHoldingsDetail(String identifier, BigDecimal amount);

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse addHoldingsDetail(UUID identifier, BigDecimal amount);

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse addHoldingsDetail(String identifier, BigDecimal amount, String world);

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse addHoldingsDetail(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse addHoldingsDetail(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse addHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncAddHoldingsDetail(String identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> addHoldingsDetail(identifier, amount));
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncAddHoldingsDetail(UUID identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> addHoldingsDetail(identifier, amount));
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncAddHoldingsDetail(String identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> addHoldingsDetail(identifier, amount, world));
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncAddHoldingsDetail(UUID identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> addHoldingsDetail(identifier, amount, world));
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncAddHoldingsDetail(String identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> addHoldingsDetail(identifier, amount, world, currency));
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncAddHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> addHoldingsDetail(identifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default boolean canAddHoldings(String identifier, BigDecimal amount) {
    return canAddHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default boolean canAddHoldings(UUID identifier, BigDecimal amount) {
    return canAddHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default boolean canAddHoldings(String identifier, BigDecimal amount, String world) {
    return canAddHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default boolean canAddHoldings(UUID identifier, BigDecimal amount, String world) {
    return canAddHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default boolean canAddHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return canAddHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default boolean canAddHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return canAddHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<Boolean> asyncCanAddHoldings(String identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount).success());
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<Boolean> asyncCanAddHoldings(UUID identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount).success());
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<Boolean> asyncCanAddHoldings(String identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount, world).success());
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<Boolean> asyncCanAddHoldings(UUID identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount, world).success());
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<Boolean> asyncCanAddHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount, world, currency).success());
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<Boolean> asyncCanAddHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount, world, currency).success());
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canAddHoldingsDetail(String identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canAddHoldingsDetail(UUID identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canAddHoldingsDetail(String identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canAddHoldingsDetail(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canAddHoldingsDetail(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse canAddHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncCanAddHoldingsDetail(String identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncCanAddHoldingsDetail(UUID identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncCanAddHoldingsDetail(String identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncCanAddHoldingsDetail(UUID identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncCanAddHoldingsDetail(String identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncCanAddHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canAddHoldingsDetail(identifier, amount, world, currency));
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean removeHoldings(String identifier, BigDecimal amount) {
    return removeHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean removeHoldings(UUID identifier, BigDecimal amount) {
    return removeHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean removeHoldings(String identifier, BigDecimal amount, String world) {
    return removeHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean removeHoldings(UUID identifier, BigDecimal amount, String world) {
    return removeHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean removeHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return removeHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean removeHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return removeHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default CompletableFuture<Boolean> asyncRemoveHoldings(String identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount).success());
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default CompletableFuture<Boolean> asyncRemoveHoldings(UUID identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount).success());
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default CompletableFuture<Boolean> asyncRemoveHoldings(String identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount, world).success());
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default CompletableFuture<Boolean> asyncRemoveHoldings(UUID identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount, world).success());
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default CompletableFuture<Boolean> asyncRemoveHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount, world, currency).success());
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default CompletableFuture<Boolean> asyncRemoveHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount, world, currency).success());
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse removeHoldingsDetail(String identifier, BigDecimal amount);

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse removeHoldingsDetail(UUID identifier, BigDecimal amount);

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse removeHoldingsDetail(String identifier, BigDecimal amount, String world);

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse removeHoldingsDetail(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse removeHoldingsDetail(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  EconomyResponse removeHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncRemoveHoldingsDetail(String identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount));
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncRemoveHoldingsDetail(UUID identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount));
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncRemoveHoldingsDetail(String identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount, world));
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncRemoveHoldingsDetail(UUID identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount, world));
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncRemoveHoldingsDetail(String identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount, world, currency));
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncRemoveHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> removeHoldingsDetail(identifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default boolean canRemoveHoldings(String identifier, BigDecimal amount) {
    return canRemoveHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default boolean canRemoveHoldings(UUID identifier, BigDecimal amount) {
    return canRemoveHoldingsDetail(identifier, amount).success();
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default boolean canRemoveHoldings(String identifier, BigDecimal amount, String world) {
    return canRemoveHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default boolean canRemoveHoldings(UUID identifier, BigDecimal amount, String world) {
    return canRemoveHoldingsDetail(identifier, amount, world).success();
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default boolean canRemoveHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return canRemoveHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default boolean canRemoveHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return canRemoveHoldingsDetail(identifier, amount, world, currency).success();
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanRemoveHoldings(String identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldings(identifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanRemoveHoldings(UUID identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldings(identifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanRemoveHoldings(String identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldings(identifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanRemoveHoldings(UUID identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldings(identifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanRemoveHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldings(identifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanRemoveHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldings(identifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  EconomyResponse canRemoveHoldingsDetail(String identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  EconomyResponse canRemoveHoldingsDetail(UUID identifier, BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  EconomyResponse canRemoveHoldingsDetail(String identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  EconomyResponse canRemoveHoldingsDetail(UUID identifier, BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  EconomyResponse canRemoveHoldingsDetail(String identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  EconomyResponse canRemoveHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanRemoveHoldingsDetail(String identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldingsDetail(identifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanRemoveHoldingsDetail(UUID identifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldingsDetail(identifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanRemoveHoldingsDetail(String identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldingsDetail(identifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanRemoveHoldingsDetail(UUID identifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldingsDetail(identifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanRemoveHoldingsDetail(String identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldingsDetail(identifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} that would be returned with the corresponding removeHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanRemoveHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canRemoveHoldingsDetail(identifier, amount, world, currency));
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were transferred.
   */
  default boolean transferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount) {
    return removeHoldings(fromIdentifier, amount) && addHoldings(toIdentifier, amount);
  }

  /**
   * Used to transfer funds from one account to another.
   *
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
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were transferred.
   */
  default boolean transferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    return removeHoldings(fromIdentifier, amount, world, currency) && addHoldings(toIdentifier, amount, world, currency);
  }

  /**
   * Used to transfer funds from one account to another.
   *
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
   *
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
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
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
   * @return True if the funds were transferred.
   */
  default CompletableFuture<Boolean> asyncTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount).success());
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were transferred.
   */
  default CompletableFuture<Boolean> asyncTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount, world).success());
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were transferred.
   */
  default CompletableFuture<Boolean> asyncTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount, world, currency).success());
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were transferred.
   */
  default CompletableFuture<Boolean> asyncTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount).success());
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were transferred.
   */
  default CompletableFuture<Boolean> asyncTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount, world).success());
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if the funds were transferred.
   */
  default CompletableFuture<Boolean> asyncTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount, world, currency).success());
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse transferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount) {
    if (removeHoldingsDetail(fromIdentifier, amount).success() && addHoldingsDetail(toIdentifier, amount).success()) {
      return GeneralResponse.SUCCESS;
    }
    return GeneralResponse.FAILED;
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse transferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount, String world) {
    if (removeHoldingsDetail(fromIdentifier, amount, world).success() && addHoldingsDetail(toIdentifier, amount, world).success()) {
      return GeneralResponse.SUCCESS;
    }
    return GeneralResponse.FAILED;
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse transferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    if (removeHoldingsDetail(fromIdentifier, amount, world, currency).success() && addHoldingsDetail(toIdentifier, amount, world, currency).success()) {
      return GeneralResponse.SUCCESS;
    }
    return GeneralResponse.FAILED;
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse transferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount) {
    if (removeHoldingsDetail(fromIdentifier, amount).success() && addHoldingsDetail(toIdentifier, amount).success()) {
      return GeneralResponse.SUCCESS;
    }
    return GeneralResponse.FAILED;
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse transferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world) {
    if (removeHoldingsDetail(fromIdentifier, amount, world).success() && addHoldingsDetail(toIdentifier, amount, world).success()) {
      return GeneralResponse.SUCCESS;
    }
    return GeneralResponse.FAILED;
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse transferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency) {
    if (removeHoldingsDetail(fromIdentifier, amount, world, currency).success() && addHoldingsDetail(toIdentifier, amount, world, currency).success()) {
      return GeneralResponse.SUCCESS;
    }
    return GeneralResponse.FAILED;
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncTransferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount));
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncTransferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount, world));
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncTransferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount, world, currency));
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncTransferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount));
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncTransferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount, world));
  }

  /**
   * Used to transfer funds from one account to another.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} for this action.
   */
  default CompletableFuture<EconomyResponse> asyncTransferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> transferHoldingsDetail(fromIdentifier, toIdentifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
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
   *
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
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  default boolean canTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    return canRemoveHoldings(fromIdentifier, amount, world, currency) && canAddHoldings(toIdentifier, amount, world, currency);
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
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
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  default boolean canTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency) {
    return canRemoveHoldings(fromIdentifier, amount, world, currency) && canAddHoldings(toIdentifier, amount, world, currency);
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldings(fromIdentifier, toIdentifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldings(fromIdentifier, toIdentifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanTransferHoldings(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldings(fromIdentifier, toIdentifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding transferHoldings method would return true, otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldings(fromIdentifier, toIdentifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldings(fromIdentifier, toIdentifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  default CompletableFuture<Boolean> asyncCanTransferHoldings(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldings(fromIdentifier, toIdentifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} associated with the corresponding transferHoldingsDetail method.
   */
  default EconomyResponse canTransferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount) {
    if (canTransferHoldings(fromIdentifier, toIdentifier, amount)) return GeneralResponse.SUCCESS;
    return GeneralResponse.FAILED;
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} associated with the corresponding transferHoldingsDetail method.
   */
  default EconomyResponse canTransferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount, String world) {
    if (canTransferHoldings(fromIdentifier, toIdentifier, amount, world)) return GeneralResponse.SUCCESS;
    return GeneralResponse.FAILED;
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} associated with the corresponding transferHoldingsDetail method.
   */
  default EconomyResponse canTransferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    if (canTransferHoldings(fromIdentifier, toIdentifier, amount, world, currency)) return GeneralResponse.SUCCESS;
    return GeneralResponse.FAILED;
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} associated with the corresponding transferHoldingsDetail method.
   */
  default EconomyResponse canTransferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount) {
    if (canTransferHoldings(fromIdentifier, toIdentifier, amount)) return GeneralResponse.SUCCESS;
    return GeneralResponse.FAILED;
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  default EconomyResponse canTransferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world) {
    if (canTransferHoldings(fromIdentifier, toIdentifier, amount, world)) return GeneralResponse.SUCCESS;
    return GeneralResponse.FAILED;
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  default EconomyResponse canTransferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency) {
    if (canTransferHoldings(fromIdentifier, toIdentifier, amount, currency)) return GeneralResponse.SUCCESS;
    return GeneralResponse.FAILED;
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} associated with the corresponding transferHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanTransferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldingsDetail(fromIdentifier, toIdentifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return The {@link EconomyResponse} associated with the corresponding transferHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanTransferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldingsDetail(fromIdentifier, toIdentifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return The {@link EconomyResponse} associated with the corresponding transferHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanTransferHoldingsDetail(String fromIdentifier, String toIdentifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldingsDetail(fromIdentifier, toIdentifier, amount, world, currency));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @return The {@link EconomyResponse} associated with the corresponding transferHoldingsDetail method.
   */
  default CompletableFuture<EconomyResponse> asyncCanTransferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldingsDetail(fromIdentifier, toIdentifier, amount));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  default CompletableFuture<EconomyResponse> asyncCanTransferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldingsDetail(fromIdentifier, toIdentifier, amount, world));
  }

  /**
   * Used to determine if a call to the corresponding transferHoldings method would be successful.
   * This method does not affect an account's funds.
   *
   * @param fromIdentifier The identifier of the account that the holdings will be coming from.
   * @param toIdentifier The identifier of the account that the holdings will be going to.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @param currency The name of the currency associated with the balance.
   * @return True if a call to the corresponding transferHoldings method would return true,
   * otherwise false.
   */
  default CompletableFuture<EconomyResponse> asyncCanTransferHoldingsDetail(UUID fromIdentifier, UUID toIdentifier, BigDecimal amount, String world, String currency) {
    return CompletableFuture.supplyAsync(() -> canTransferHoldingsDetail(fromIdentifier, toIdentifier, amount, world, currency));
  }

  /**
   * @return True if this economy implementation supports calculating costs for external plugins.
   */
  default boolean supportsExternalCosts() {
    return false;
  }

  /**
   * Used to calculate the cost of a product for a plugin.
   *
   * @param plugin The name of the plugin providing the product.
   * @param product The name of the product.
   * @param identifier The account identifier purchasing the product.
   * @param baseCost The base cost of the product.
   * @return The calculated cost in BigDecimal format.
   */
  default BigDecimal calculateCost(String plugin, String product, String identifier, BigDecimal baseCost) {
    return baseCost;
  }

  /**
   * Used to calculate the cost of a product for a plugin.
   *
   * @param plugin The name of the plugin providing the product.
   * @param product The name of the product.
   * @param world The name of the world the product is being purchased in.
   * @param identifier The account identifier purchasing the product.
   * @param baseCost The base cost of the product.
   * @return The calculated cost in BigDecimal format.
   */
  default BigDecimal calculateCost(String plugin, String product, String identifier, String world, BigDecimal baseCost) {
    return baseCost;
  }

  /**
   * Used to calculate the cost of a product for a plugin.
   *
   * @param plugin The name of the plugin providing the product.
   * @param product The name of the product.
   * @param world The name of the world the product is being purchased in.
   * @param currency The name of the currency the product is being purchased with.
   * @param identifier The account identifier purchasing the product.
   * @param baseCost The base cost of the product.
   * @return The calculated cost in BigDecimal format.
   */
  default BigDecimal calculateCost(String plugin, String product, String identifier, String world, String currency, BigDecimal baseCost) {
    return baseCost;
  }

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
  default List<String> getBanks() {
    return new ArrayList<>();
  }

  /**
   * @param world The name of the {@link World} to use for this call.
   * @return A list containing the names of the banks currently in the specified world
   */
  default List<String> getBanks(String world) {
    return new ArrayList<>();
  }

  /**
   * Asynchronous version of getBanks()
   *
   * @return A list containing the names of the banks currently in the server.
   */
  default CompletableFuture<List<String>> asyncGetBanks() {
    return CompletableFuture.supplyAsync(this::getBanks);
  }


  /**
   * Asynchronous version of getBanks(String world)
   *
   * @param world The name of the {@link World} to use for this call.
   * @return A list containing the names of the banks currently in the specified world
   */
  default CompletableFuture<List<String>> asyncGetBanks(String world) {
    return CompletableFuture.supplyAsync(() -> getBanks(world));
  }

  /**
   * @return A list of currencies that are able to be used with banks.
   */
  default List<String> acceptedBankCurrencies() {
    return new ArrayList<>();
  }

  /**
   * @param world The name of the {@link World} to use for this call.
   * @return A list of currencies that are able to be used with banks in the specified world.
   */
  default List<String> acceptedBankCurrencies(String world) {
    return new ArrayList<>();
  }

  /**
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return A list of currencies that are able to be used with the specified bank in the specified world.
   */
  default List<String> acceptedBankCurrencies(String world, String bank) {
    return new ArrayList<>();
  }

  /**
   * @return A list of currencies that are able to be used with banks.
   */
  default CompletableFuture<List<String>> asyncAcceptedBankCurrencies() {
    return CompletableFuture.supplyAsync(this::acceptedBankCurrencies);
  }

  /**
   * @param world The name of the {@link World} to use for this call.
   * @return A list of currencies that are able to be used with banks in the specified world.
   */
  default CompletableFuture<List<String>> asyncAcceptedBankCurrencies(String world) {
    return CompletableFuture.supplyAsync(() -> acceptedBankCurrencies(world));
  }

  /**
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return A list of currencies that are able to be used with the specified bank in the specified world.
   */
  default CompletableFuture<List<String>> asyncAcceptedBankCurrencies(String world, String bank) {
    return CompletableFuture.supplyAsync(() -> acceptedBankCurrencies(world, bank));
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to.
   */
  default List<UUID> availableBankAccounts(UUID player) {
    return new ArrayList<>();
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to in a specific world.
   */
  default List<UUID> availableBankAccounts(UUID player, String world) {
    return new ArrayList<>();
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to in a specific bank in a specific world.
   */
  default List<UUID> availableBankAccounts(UUID player, String world, String bank) {
    return new ArrayList<>();
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to.
   */
  default CompletableFuture<List<UUID>> asyncAvailableBankAccounts(UUID player) {
    return CompletableFuture.supplyAsync(() -> availableBankAccounts(player));
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to in a specific world.
   */
  default CompletableFuture<List<UUID>> asyncAvailableBankAccounts(UUID player, String world) {
    return CompletableFuture.supplyAsync(() -> availableBankAccounts(player, world));
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return A List of UUIDs of bank accounts that the specified player has access to in a specific bank in a specific world.
   */
  default CompletableFuture<List<UUID>> asyncAvailableBankAccounts(UUID player, String world, String bank) {
    return CompletableFuture.supplyAsync(() -> availableBankAccounts(player, world, bank));
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the specified player is the owner of a bank in the specified world.
   */
  default boolean isBankOwner(UUID player, String world) {
    return false;
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the specified player is the owner of the specified bank in the specified world.
   */
  default boolean isBankOwner(UUID player, String world, String bank) {
    return false;
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the specified player is the owner of a bank in the specified world.
   */
  default CompletableFuture<Boolean> asyncIsBankOwner(UUID player, String world) {
    return CompletableFuture.supplyAsync(() -> isBankOwner(player, world));
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the specified player is the owner of the specified bank in the specified world.
   */
  default CompletableFuture<Boolean> asyncIsBankOwner(UUID player, String world, String bank) {
    return CompletableFuture.supplyAsync(() -> isBankOwner(player, world, bank));
  }

  /**
   * @param owner The UUID of the owner of this bank account.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  default Optional<UUID> createBankAccount(UUID owner) {
    return Optional.empty();
  }

  /**
   * @param owner The UUID of the owner of this bank account.
   * @param world The name of the {@link World} to create this bank account in.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  default Optional<UUID> createBankAccount(UUID owner, String world) {
    return Optional.empty();
  }

  /**
   * @param owner The UUID of the owner of this bank account.
   * @param world The name of the {@link World} to create this bank account in.
   * @param bank The name of the bank to create this bank account in.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  default Optional<UUID> createBankAccount(UUID owner, String world, String bank) {
    return Optional.empty();
  }

  /**
   * @param owner The UUID of the owner of this bank account.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  default CompletableFuture<Optional<UUID>> asyncCreateBankAccount(UUID owner) {
    return CompletableFuture.supplyAsync(() -> createBankAccount(owner));
  }

  /**
   * @param owner The UUID of the owner of this bank account.
   * @param world The name of the {@link World} to create this bank account in.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  default CompletableFuture<Optional<UUID>> asyncCreateBankAccount(UUID owner, String world) {
    return CompletableFuture.supplyAsync(() -> createBankAccount(owner, world));
  }

  /**
   * @param owner The UUID of the owner of this bank account.
   * @param world The name of the {@link World} to create this bank account in.
   * @param bank The name of the bank to create this bank account in.
   * @return An optional with a UUID of the created bank account if it was created, otherwise an empty Optional.
   */
  default CompletableFuture<Optional<UUID>> asyncCreateBankAccount(UUID owner, String world, String bank) {
    return CompletableFuture.supplyAsync(() -> createBankAccount(owner, world, bank));
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player has a bank account.
   */
  default boolean hasBankAccount(UUID player) {
    return false;
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the specified player has a bank account in the specified world.
   */
  default boolean hasBankAccount(UUID player, String world) {
    return false;
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the specified player has a bank account in the specified bank in the specified world.
   */
  default boolean hasBankAccount(UUID player, String world, String bank) {
    return false;
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player has a bank account.
   */
  default CompletableFuture<Boolean> asyncHasBankAccount(UUID player) {
    return CompletableFuture.supplyAsync(() -> hasBankAccount(player));
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the specified player has a bank account in the specified world.
   */
  default CompletableFuture<Boolean> asyncHasBankAccount(UUID player, String world) {
    return CompletableFuture.supplyAsync(() -> hasBankAccount(player, world));
  }

  /**
   * @param player The UUID of the player to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the specified player has a bank account in the specified bank in the specified world.
   */
  default CompletableFuture<Boolean> asyncHasBankAccount(UUID player, String world, String bank) {
    return CompletableFuture.supplyAsync(() -> hasBankAccount(player, world, bank));
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player is the owner of the specified bank account.
   */
  default boolean isBankAccountOwner(UUID account, UUID player) {
    return false;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player is the owner of the specified bank account.
   */
  default CompletableFuture<Boolean> asyncIsBankAccountOwner(UUID account, UUID player) {
    return CompletableFuture.supplyAsync(() -> isBankAccountOwner(account, player));
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player is a member of the specified bank account.
   */
  default boolean isBankAccountMember(UUID account, UUID player) {
    return false;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player to use for this call.
   * @return True if the specified player is a member of the specified bank account.
   */
  default CompletableFuture<Boolean> asyncIsBankAccountMember(UUID account, UUID player) {
    return CompletableFuture.supplyAsync(() -> isBankAccountMember(account, player));
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @return The balance of the bank account.
   */
  default BigDecimal getBankHoldings(UUID account) {
    return BigDecimal.ZERO;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @return The balance of the bank account.
   */
  default BigDecimal getBankHoldings(UUID account, String world) {
    return BigDecimal.ZERO;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return The balance of the bank account.
   */
  default BigDecimal getBankHoldings(UUID account, String world, String currency) {
    return BigDecimal.ZERO;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return The balance of the bank account.
   */
  default BigDecimal getBankHoldings(UUID account, String world, String currency, String bank) {
    return BigDecimal.ZERO;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to add to the account.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean bankAddHoldings(UUID account, UUID player, BigDecimal amount) {
    return bankAddHoldingsDetail(account, player, amount).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean bankAddHoldings(UUID account, UUID player, BigDecimal amount, String world) {
    return bankAddHoldingsDetail(account, player, amount, world).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean bankAddHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency) {
    return bankAddHoldingsDetail(account, player, amount, world, currency).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the funds were added to the account, otherwise false.
   */
  default boolean bankAddHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency, String bank) {
    return bankAddHoldingsDetail(account, player, amount, world, currency, bank).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to add to the account.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankAddHoldingsDetail(UUID account, UUID player, BigDecimal amount) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankAddHoldingsDetail(UUID account, UUID player, BigDecimal amount, String world) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankAddHoldingsDetail(UUID account, UUID player, BigDecimal amount, String world, String currency) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if not associated with a player action.
   * @param amount The amount of funds to add to the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankAddHoldingsDetail(UUID account, UUID player, BigDecimal amount, String world, String currency, String bank) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean bankRemoveHoldings(UUID account, UUID player, BigDecimal amount) {
    return bankRemoveHoldingsDetail(account, player, amount).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean bankRemoveHoldings(UUID account, UUID player, BigDecimal amount, String world) {
    return bankRemoveHoldingsDetail(account, player, amount, world).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean bankRemoveHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency) {
    return bankRemoveHoldingsDetail(account, player, amount, world, currency).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the funds were removed from the account, otherwise false.
   */
  default boolean bankRemoveHoldings(UUID account, UUID player, BigDecimal amount, String world, String currency, String bank) {
    return bankRemoveHoldingsDetail(account, player, amount, world, currency, bank).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankRemoveHoldingsDetail(UUID account, UUID player, BigDecimal amount) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankRemoveHoldingsDetail(UUID account, UUID player, BigDecimal amount, String world) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankRemoveHoldingsDetail(UUID account, UUID player, BigDecimal amount, String world, String currency) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param player The UUID of the player adding the funds to the account, null if console.
   * @param amount The amount of funds to remove from the account.
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankRemoveHoldingsDetail(UUID account, UUID player, BigDecimal amount, String world, String currency, String bank) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @return True if the account's funds were set to the specified amount.
   */
  default boolean bankSetHoldings(UUID account, BigDecimal amount) {
    return bankSetHoldingsDetail(account, amount).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @return True if the account's funds were set to the specified amount.
   */
  default boolean bankSetHoldings(UUID account, BigDecimal amount, String world) {
    return bankSetHoldingsDetail(account, amount, world).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return True if the account's funds were set to the specified amount.
   */
  default boolean bankSetHoldings(UUID account, BigDecimal amount, String world, String currency) {
    return bankSetHoldingsDetail(account, amount, world, currency).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return True if the account's funds were set to the specified amount.
   */
  default boolean bankSetHoldings(UUID account, BigDecimal amount, String world, String currency, String bank) {
    return bankSetHoldingsDetail(account, amount, world, currency, bank).success();
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankSetHoldingsDetail(UUID account, BigDecimal amount) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankSetHoldingsDetail(UUID account, BigDecimal amount, String world) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankSetHoldingsDetail(UUID account, BigDecimal amount, String world, String currency) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * @param account The UUID of the bank account to use for this call.
   * @param amount The amount to set the account's funds to
   * @param world The name of the {@link World} to use for this call.
   * @param currency The name of the currency to use for this call.
   * @param bank The name of the bank to use for this call.
   * @return The {@link EconomyResponse} for this action.
   */
  default EconomyResponse bankSetHoldingsDetail(UUID account, BigDecimal amount, String world, String currency, String bank) {
    return GeneralResponse.UNSUPPORTED;
  }

  /**
   * Formats a monetary amount into a more text-friendly version.
   *
   * @param amount The amount of currency to format.
   * @return The formatted amount.
   */
  String format(BigDecimal amount);

  /**
   * Formats a monetary amount into a more text-friendly version.
   *
   * @param amount The amount of currency to format.
   * @param world The {@link World} in which this format operation is occurring.
   * @return The formatted amount.
   */
  String format(BigDecimal amount, String world);

  /**
   * Formats a monetary amount into a more text-friendly version.
   *
   * @param amount The amount of currency to format.
   * @param world The {@link World} in which this format operation is occurring.
   * @param currency The name of the currency associated with the balance.
   * @return The formatted amount.
   */
  String format(BigDecimal amount, String world, String currency);

  /**
   * Purges the database of accounts with the default balance.
   *
   * @return True if the purge was completed successfully.
   */
  boolean purgeAccounts();

  /**
   * Purges the database of accounts with a balance under the specified one.
   *
   * @param amount The amount that an account's balance has to be under in order to be removed.
   * @return True if the purge was completed successfully.
   */
  boolean purgeAccountsUnder(BigDecimal amount);

  /**
   * Purges the database of accounts with the default balance.
   *
   * @return True if the purge was completed successfully.
   */
  default CompletableFuture<Boolean> asyncPurgeAccounts() {
    return CompletableFuture.supplyAsync(this::purgeAccounts);
  }

  /**
   * Purges the database of accounts with a balance under the specified one.
   *
   * @param amount The amount that an account's balance has to be under in order to be removed.
   * @return True if the purge was completed successfully.
   */
  default CompletableFuture<Boolean> asyncPurgeAccountsUnder(BigDecimal amount) {
    return CompletableFuture.supplyAsync(() -> purgeAccountsUnder(amount));
  }
}
