package net.tnemc.core.economy;

import net.tnemc.core.economy.currency.Currency;
import net.tnemc.core.economy.currency.Tier;
import net.tnemc.core.economy.transaction.Transaction;
import net.tnemc.core.economy.transaction.result.TransactionResult;
import net.tnemc.core.economy.transaction.type.TransactionType;
import org.bukkit.World;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Created by creatorfromhell on 10/16/2017.
 *
 * Reserve API
 *
 * Copyright (C) 2018 creatorfromhell
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
public interface ExtendedEconomyAPI extends EconomyAPI {

  /**
   * Finds the default {@link Currency} for the server.
   * @return The default {@link Currency} for the server.
   */
  Currency getDefault();

  /**
   * Finds the default {@link Currency} for a {@link World}
   * @param world The name of the {@link World} to use.
   * @return The default {@link Currency} for this {@link World}.
   */
  Currency getDefault(String world);

  /**
   * Grabs a {@link Set} of {@link Currency} objects that exist.
   * @return A Set containing all the {@link Currency} objects that exist on this server.
   */
  Set<Currency> getCurrencies();

  /**
   * Grabs a {@link Set} of {@link Currency} objects that exist in a {@link World}
   * @param world The name of the {@link World} to use in this search.
   * @return A Set containing all the {@link Currency} objects that exist on this {@link World}.
   */
  Set<Currency> getCurrencies(String world);

  /**
   * Grabs a currency based on the name provided.
   * @param name The name of the currency you are attempted to retrieve.
   * @return The {@link Currency} object if it exists, otherwise null.
   */
  Currency getCurrency(String name);

  /**
   * Grabs a currency based on the name provided.
   * @param name The name of the currency you are attempted to retrieve.
   * @param world The name of the {@link World} to use in this search.
   * @return The {@link Currency} object if it exists, otherwise null.
   */
  Currency getCurrency(String name, String world);

  /**
   * Checks to see if a {@link Currency} has the specified tier.
   * @param name The name of the {@link Tier} to search for.
   * @param currency The {@link Currency} to search
   * @return True if the tier exists, otherwise false.
   */
  boolean hasTier(String name, Currency currency);

  /**
   * Checks to see if a {@link Currency} has the specified tier.
   * @param name The name of the {@link Tier} to search for.
   * @param currency The {@link Currency} to search
   * @param world The name of the {@link World} to use for search purposes.
   * @return True if the tier exists, otherwise false.
   */
  boolean hasTier(String name, Currency currency, String world);

  /**
   * Returns a {@link Set} of {@link Tier} objects associated with the specified {@link Currency}.
   * @param currency The {@link Currency} to grab the tiers from.
   * @return A Set containing all the {@link Tier} objects belonging to this {@link Currency}.
   */
  Set<Tier> getTiers(Currency currency);

  /**
   * Attempts to retrieve an account by the specified identifier. This method should be used for non-player accounts.
   * @param identifier The of the account.
   * @return The instance of the account if it exists, otherwise null.
   */
  Account getAccount(String identifier);

  /**
   * Attempts to retrieve an account by the specified identifier. This method should be used for player accounts.
   * @param identifier The {@link UUID} of the account.
   * @return The instance of the account if it exists, otherwise null.
   */
  Account getAccount(UUID identifier);

  /**
   * This is a shortcut method that combines getAccount with createAccount. This method should be used for non-player
   * Accounts.
   * @param identifier The of the account.
   * @return The instance of the account.
   */
  Account createIfNotExists(String identifier);

  /**
   * This is a shortcut method that combines getAccount with createAccount. This method should be used for non-player
   * Accounts.
   * @param identifier The {@link UUID} of the account.
   * @return The instance of the account.
   */
  Account createIfNotExists(UUID identifier);

  /**
   * Determines whether or not a player is able to access this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  @Override
  default boolean isAccessor(String identifier, String accessor) {
    return getAccount(identifier).isAccessor(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to access this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  @Override
  default boolean isAccessor(String identifier, UUID accessor) {
    return getAccount(identifier).isAccessor(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to access this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  @Override
  default boolean isAccessor(UUID identifier, String accessor) {
    return getAccount(identifier).isAccessor(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to access this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to access this account.
   */
  @Override
  default boolean isAccessor(UUID identifier, UUID accessor) {
    return getAccount(identifier).isAccessor(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  @Override
  default boolean canWithdraw(String identifier, String accessor) {
    return getAccount(identifier).canWithdraw(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  @Override
  default boolean canWithdraw(String identifier, UUID accessor) {
    return getAccount(identifier).canWithdraw(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  @Override
  default boolean canWithdraw(UUID identifier, String accessor) {
    return getAccount(identifier).canWithdraw(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  @Override
  default boolean canWithdraw(UUID identifier, UUID accessor) {
    return getAccount(identifier).canWithdraw(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  @Override
  default boolean canDeposit(String identifier, String accessor) {
    return getAccount(identifier).canDeposit(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  @Override
  default boolean canDeposit(String identifier, UUID accessor) {
    return getAccount(identifier).canDeposit(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  @Override
  default boolean canDeposit(UUID identifier, String accessor) {
    return getAccount(identifier).canDeposit(getAccount(accessor));
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  @Override
  default boolean canDeposit(UUID identifier, UUID accessor) {
    return getAccount(identifier).canDeposit(getAccount(accessor));
  }

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  @Override
  default BigDecimal getHoldings(String identifier) {
    return getAccount(identifier).getHoldings();
  }

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  @Override
  default BigDecimal getHoldings(UUID identifier) {
    return getAccount(identifier).getHoldings();
  }

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param world      The name of the {@link World} associated with the balance.
   * @return The balance of the account.
   */
  @Override
  default BigDecimal getHoldings(String identifier, String world) {
    return getAccount(identifier).getHoldings(world);
  }

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param world      The name of the {@link World} associated with the balance.
   * @return The balance of the account.
   */
  @Override
  default BigDecimal getHoldings(UUID identifier, String world) {
    return getAccount(identifier).getHoldings(world);
  }

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param world      The name of the {@link World} associated with the balance.
   * @param currency   The {@link Currency} associated with the balance.
   * @return The balance of the account.
   */
  @Override
  default BigDecimal getHoldings(String identifier, String world, String currency) {
    return getAccount(identifier).getHoldings(world, getCurrency(currency, world));
  }

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param world      The name of the {@link World} associated with the balance.
   * @param currency   The {@link Currency} associated with the balance.
   * @return The balance of the account.
   */
  @Override
  default BigDecimal getHoldings(UUID identifier, String world, String currency) {
    return getAccount(identifier).getHoldings(world, getCurrency(currency, world));
  }

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to use for this check.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  @Override
  default boolean hasHoldings(String identifier, BigDecimal amount) {
    return getAccount(identifier).hasHoldings(amount);
  }

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to use for this check.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  @Override
  default boolean hasHoldings(UUID identifier, BigDecimal amount) {
    return getAccount(identifier).hasHoldings(amount);
  }

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to use for this check.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  @Override
  default boolean hasHoldings(String identifier, BigDecimal amount, String world) {
    return getAccount(identifier).hasHoldings(amount, world);
  }

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to use for this check.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  @Override
  default boolean hasHoldings(UUID identifier, BigDecimal amount, String world) {
    return getAccount(identifier).hasHoldings(amount, world);
  }

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to use for this check.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  @Override
  default boolean hasHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).hasHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to determine if an account has at least an amount of funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to use for this check.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  @Override
  default boolean hasHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).hasHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  @Override
  default boolean setHoldings(String identifier, BigDecimal amount) {
    return getAccount(identifier).setHoldings(amount);
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  @Override
  default boolean setHoldings(UUID identifier, BigDecimal amount) {
    return getAccount(identifier).setHoldings(amount);
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to set this accounts's funds to.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  @Override
  default boolean setHoldings(String identifier, BigDecimal amount, String world) {
    return getAccount(identifier).setHoldings(amount, world);
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to set this accounts's funds to.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  @Override
  default boolean setHoldings(UUID identifier, BigDecimal amount, String world) {
    return getAccount(identifier).setHoldings(amount, world);
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to set this accounts's funds to.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if the funds were set for the account, otherwise false.
   */
  @Override
  default boolean setHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).setHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to set this accounts's funds to.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if the funds were set for the account, otherwise false.
   */
  @Override
  default boolean setHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).setHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  @Override
  default boolean addHoldings(String identifier, BigDecimal amount) {
    return getAccount(identifier).addHoldings(amount);
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  @Override
  default boolean addHoldings(UUID identifier, BigDecimal amount) {
    return getAccount(identifier).addHoldings(amount);
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  @Override
  default boolean addHoldings(String identifier, BigDecimal amount, String world) {
    return getAccount(identifier).addHoldings(amount, world);
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  @Override
  default boolean addHoldings(UUID identifier, BigDecimal amount, String world) {
    return getAccount(identifier).addHoldings(amount, world);
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if the funds were added to the account, otherwise false.
   */
  @Override
  default boolean addHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).addHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if the funds were added to the account, otherwise false.
   */
  @Override
  default boolean addHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).addHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canAddHoldings(String identifier, BigDecimal amount) {
    return getAccount(identifier).canAddHoldings(amount);
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canAddHoldings(UUID identifier, BigDecimal amount) {
    return getAccount(identifier).canAddHoldings(amount);
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canAddHoldings(String identifier, BigDecimal amount, String world) {
    return getAccount(identifier).canAddHoldings(amount, world);
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canAddHoldings(UUID identifier, BigDecimal amount, String world) {
    return getAccount(identifier).canAddHoldings(amount, world);
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canAddHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).canAddHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canAddHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).canAddHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  @Override
  default boolean removeHoldings(String identifier, BigDecimal amount) {
    return getAccount(identifier).removeHoldings(amount);
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  @Override
  default boolean removeHoldings(UUID identifier, BigDecimal amount) {
    return getAccount(identifier).removeHoldings(amount);
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  @Override
  default boolean removeHoldings(String identifier, BigDecimal amount, String world) {
    return getAccount(identifier).removeHoldings(amount, world);
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  @Override
  default boolean removeHoldings(UUID identifier, BigDecimal amount, String world) {
    return getAccount(identifier).removeHoldings(amount, world);
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  @Override
  default boolean removeHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).removeHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if the funds were removed from the account, otherwise false.
   */
  @Override
  default boolean removeHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).removeHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canRemoveHoldings(String identifier, BigDecimal amount) {
    return getAccount(identifier).canRemoveHoldings(amount);
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canRemoveHoldings(UUID identifier, BigDecimal amount) {
    return getAccount(identifier).canRemoveHoldings(amount);
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canRemoveHoldings(String identifier, BigDecimal amount, String world) {
    return getAccount(identifier).canRemoveHoldings(amount, world);
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canRemoveHoldings(UUID identifier, BigDecimal amount, String world) {
    return getAccount(identifier).canRemoveHoldings(amount, world);
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canRemoveHoldings(String identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).canRemoveHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @param world      The name of the {@link World} associated with the amount.
   * @param currency   The {@link Currency} associated with the balance.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  @Override
  default boolean canRemoveHoldings(UUID identifier, BigDecimal amount, String world, String currency) {
    return getAccount(identifier).canRemoveHoldings(amount, getCurrency(currency, world), world);
  }

  /**
   * Formats a monetary amount into a more text-friendly version.
   * @param amount The amount of currency to format.
   * @param currency The {@link Currency} associated with the amount to be formatted.
   * @return The formatted amount.
   */
  String format(BigDecimal amount, Currency currency);

  /**
   * Formats a monetary amount into a more text-friendly version.
   *
   * @param amount   The amount of currency to format.
   * @param world    The {@link World} in which this format operation is occurring.
   * @param currency The {@link Currency} associated with the balance.
   * @return The formatted amount.
   */
  @Override
  default String format(BigDecimal amount, String world, String currency) {
    return format(amount, getCurrency(currency, world), world);
  }

  /**
   * Formats a monetary amount into a more text-friendly version.
   * @param amount The amount of currency to format.
   * @param currency The {@link Currency} associated with the amount to be formatted.
   * @param world The {@link World} in which this format operation is occuring.
   * @return The formatted amount.
   */
  String format(BigDecimal amount, Currency currency, String world);

  /**
   * Whether or not this API Implementation supports the Transaction System.
   */
  @Override
  default boolean supportTransactions() {
    return true;
  }

  /**
   * Performs a {@link Transaction}.
   * @param transaction The {@link Transaction} to perform.
   * @return The {@link TransactionResult} of the {@link Transaction}.
   */
  TransactionResult performTransaction(Transaction transaction);

  /**
   * Attempts to get the {@link Transaction} associated with the specified {@link UUID}.
   * @param uuid The {@link UUID} of the {@link Transaction}.
   * @return A non-empty {@link Optional} if a {@link Transaction} exists with the specified {@link UUID}.
   */
  Optional<Transaction> getTransaction(UUID uuid);

  /**
   * Attempts to void the {@link Transaction} with the specified {@link UUID}.
   * @param uuid The {@link UUID} of the {@link Transaction}.
   * @return True if the {@link Transaction} was voided, otherwise false.
   */
  boolean voidTransaction(UUID uuid);

  /**
   * Grabs a {@link Set} of {@link TransactionType} objects available for use.
   * @return A {@link Set} of {@link TransactionType} objects.
   */
  Set<TransactionType> getTransactionTypes();

  /**
   * Returns a {@link Map} of all {@link Transaction} objects that have been recorded by this {@link ExtendedEconomyAPI}
   * implementation.
   *
   * The key is the {@link UUID} of the {@link Transaction}, while the value is the {@link Transaction} object itself.
   *
   * @return A {@link Map} of all recorded {@link Transaction} objects.
   */
  Map<UUID, Transaction> getTransactions();

  /**
   * Returns a {@link Map} of all {@link Transaction} objects that have been recorded by this {@link ExtendedEconomyAPI}
   * implementation, which involve the account with the specified identifier.
   *
   * The key is the {@link UUID} of the {@link Transaction}, while the value is the {@link Transaction} object itself.
   *
   * @param identifier The identifier of the account. This may be a {@link UUID}, or a player's name.
   * @return A {@link Map} of all recorded {@link Transaction} objects, which involve the account with the specified
   * identifier.
   */
  Map<UUID, Transaction> getTransactions(String identifier);

  /**
   * Register a custom {@link TransactionType}.
   * @param type The {@link TransactionType type} to register.
   * @return True if the {@link TransactionType type} was registered.
   */
  boolean registerTransactionType(TransactionType type);

  /**
   * Register a custom {@link TransactionResult}.
   * @param result The {@link TransactionResult result} to register.
   * @return True if the {@link TransactionResult result} was registered.
   */
  boolean registerTransactionResult(TransactionResult result);

  /**
   * Register a {@link Currency}  to be used by other plugins.
   * @param currency The {@link Currency} to register.
   * @return True if the {@link Currency} was registered, otherwise false.
   */
  boolean registerCurrency(Currency currency);

  /**
   * Register a {@link Currency}  to be used by other plugins.
   * @param currency The {@link Currency} to register.
   * @param world The name of the {@link World} to use during the registration process.
   * @return True if the {@link Currency}  was registered, otherwise false.
   */
  boolean registerCurrency(Currency currency, String world);

  /**
   * Register a {@link Currency} {@link Tier} to be used by other plugins.
   * @param tier The {@link Tier} to register.
   * @param currency The {@link Currency} to register this {@link Tier} under.
   * @return True if the {@link Tier} was registered, otherwise false.
   */
  boolean registerTier(Tier tier, Currency currency);

  /**
   * Register a {@link Currency} {@link Tier} to be used by other plugins.
   * @param tier The {@link Tier} to register.
   * @param currency The {@link Currency} to register this {@link Tier} under.
   * @param world The name of the {@link World} to use during the registration process.
   * @return True if the {@link Tier} was registered, otherwise false.
   */
  boolean registerTier(Tier tier, Currency currency, String world);
}