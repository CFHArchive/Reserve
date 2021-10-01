package net.tnemc.reserve.core.economy;

import net.tnemc.reserve.core.economy.currency.Currency;
import net.tnemc.reserve.core.economy.currency.Tier;
import net.tnemc.reserve.core.economy.response.EconomyResponse;
import net.tnemc.reserve.core.economy.tax.TaxEntry;
import net.tnemc.reserve.core.economy.tax.TaxType;
import net.tnemc.reserve.core.economy.transaction.Transaction;
import net.tnemc.reserve.core.economy.transaction.result.TransactionResult;
import net.tnemc.reserve.core.economy.transaction.type.TransactionType;
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
  Optional<Currency> getCurrency(String name);

  /**
   * Grabs a currency based on the name provided.
   * @param name The name of the currency you are attempted to retrieve.
   * @param world The name of the {@link World} to use in this search.
   * @return The {@link Currency} object if it exists, otherwise null.
   */
  Optional<Currency> getCurrency(String name, String world);

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
  Optional<Account> getAccount(String identifier);

  /**
   * Attempts to retrieve an account by the specified identifier. This method should be used for player accounts.
   * @param identifier The {@link UUID} of the account.
   * @return The instance of the account if it exists, otherwise null.
   */
  Optional<Account> getAccount(UUID identifier);

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
    final Optional<Account> account = getAccount(identifier);
    final Optional<Account> accessorAccount = getAccount(accessor);
    if(account.isPresent() && accessorAccount.isPresent()) {
      return account.get().isAccessor(accessorAccount.get());
    }
    return false;
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
    return isAccessor(identifier, accessor.toString());
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
    return isAccessor(identifier.toString(), accessor);
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
    return isAccessor(identifier.toString(), accessor.toString());
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  @Override
  default EconomyResponse canWithdrawDetail(String identifier, String accessor) {
    return createIfNotExists(identifier).canWithdraw(createIfNotExists(accessor));
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  @Override
  default EconomyResponse canWithdrawDetail(String identifier, UUID accessor) {
    return createIfNotExists(identifier).canWithdraw(createIfNotExists(accessor));
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  @Override
  default EconomyResponse canWithdrawDetail(UUID identifier, String accessor) {
    return createIfNotExists(identifier).canWithdraw(createIfNotExists(accessor));
  }

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  @Override
  default EconomyResponse canWithdrawDetail(UUID identifier, UUID accessor) {
    return createIfNotExists(identifier).canWithdraw(createIfNotExists(accessor));
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  @Override
  default EconomyResponse canDepositDetail(String identifier, String accessor) {
    return createIfNotExists(identifier).canDeposit(createIfNotExists(accessor));
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  @Override
  default EconomyResponse canDepositDetail(String identifier, UUID accessor) {
    return createIfNotExists(identifier).canDeposit(createIfNotExists(accessor));
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  @Override
  default EconomyResponse canDepositDetail(UUID identifier, String accessor) {
    return createIfNotExists(identifier).canDeposit(createIfNotExists(accessor));
  }

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param accessor   The identifier of the user attempting to access this account.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  @Override
  default EconomyResponse canDepositDetail(UUID identifier, UUID accessor) {
    return createIfNotExists(identifier).canDeposit(createIfNotExists(accessor));
  }

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  @Override
  default BigDecimal getHoldings(String identifier) {
    return createIfNotExists(identifier).getHoldings();
  }

  /**
   * Used to get the balance of an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @return The balance of the account.
   */
  @Override
  default BigDecimal getHoldings(UUID identifier) {
    return createIfNotExists(identifier).getHoldings();
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
    return createIfNotExists(identifier).getHoldings(world);
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
    return createIfNotExists(identifier).getHoldings(world);
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
    return createIfNotExists(identifier).getHoldings(world, getCurrency(currency, world).get());
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
    return createIfNotExists(identifier).getHoldings(world, getCurrency(currency, world).get());
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
    return createIfNotExists(identifier).hasHoldings(amount);
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
    return createIfNotExists(identifier).hasHoldings(amount);
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
    return createIfNotExists(identifier).hasHoldings(amount, world);
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
    return createIfNotExists(identifier).hasHoldings(amount, world);
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
    return createIfNotExists(identifier).hasHoldings(amount, getCurrency(currency, world).get(), world);
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
    return createIfNotExists(identifier).hasHoldings(amount, getCurrency(currency, world).get(), world);
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  @Override
  default EconomyResponse setHoldingsDetail(String identifier, BigDecimal amount) {
    return createIfNotExists(identifier).setHoldings(amount);
  }

  /**
   * Used to set the funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  @Override
  default EconomyResponse setHoldingsDetail(UUID identifier, BigDecimal amount) {
    return createIfNotExists(identifier).setHoldings(amount);
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
  default EconomyResponse setHoldingsDetail(String identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).setHoldings(amount, world);
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
  default EconomyResponse setHoldingsDetail(UUID identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).setHoldings(amount, world);
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
  default EconomyResponse setHoldingsDetail(String identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).setHoldings(amount, getCurrency(currency, world).get(), world);
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
  default EconomyResponse setHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).setHoldings(amount, getCurrency(currency, world).get(), world);
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  @Override
  default EconomyResponse addHoldingsDetail(String identifier, BigDecimal amount) {
    return createIfNotExists(identifier).addHoldings(amount);
  }

  /**
   * Used to add funds to an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  @Override
  default EconomyResponse addHoldingsDetail(UUID identifier, BigDecimal amount) {
    return createIfNotExists(identifier).addHoldings(amount);
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
  default EconomyResponse addHoldingsDetail(String identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).addHoldings(amount, world);
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
  default EconomyResponse addHoldingsDetail(UUID identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).addHoldings(amount, world);
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
  default EconomyResponse addHoldingsDetail(String identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).addHoldings(amount, getCurrency(currency, world).get(), world);
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
  default EconomyResponse addHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).addHoldings(amount, getCurrency(currency, world).get(), world);
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
  default EconomyResponse canAddHoldingsDetail(String identifier, BigDecimal amount) {
    return createIfNotExists(identifier).canAddHoldings(amount);
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
  default EconomyResponse canAddHoldingsDetail(UUID identifier, BigDecimal amount) {
    return createIfNotExists(identifier).canAddHoldings(amount);
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
  default EconomyResponse canAddHoldingsDetail(String identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).canAddHoldings(amount, world);
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
  default EconomyResponse canAddHoldingsDetail(UUID identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).canAddHoldings(amount, world);
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
  default EconomyResponse canAddHoldingsDetail(String identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).canAddHoldings(amount, getCurrency(currency, world).get(), world);
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
  default EconomyResponse canAddHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).canAddHoldings(amount, getCurrency(currency, world).get(), world);
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  @Override
  default EconomyResponse removeHoldingsDetail(String identifier, BigDecimal amount) {
    return createIfNotExists(identifier).removeHoldings(amount);
  }

  /**
   * Used to remove funds from an account.
   *
   * @param identifier The identifier of the account that is associated with this call.
   * @param amount     The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  @Override
  default EconomyResponse removeHoldingsDetail(UUID identifier, BigDecimal amount) {
    return createIfNotExists(identifier).removeHoldings(amount);
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
  default EconomyResponse removeHoldingsDetail(String identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).removeHoldings(amount, world);
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
  default EconomyResponse removeHoldingsDetail(UUID identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).removeHoldings(amount, world);
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
  default EconomyResponse removeHoldingsDetail(String identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).removeHoldings(amount, getCurrency(currency, world).get(), world);
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
  default EconomyResponse removeHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).removeHoldings(amount, getCurrency(currency, world).get(), world);
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
  default EconomyResponse canRemoveHoldingsDetail(String identifier, BigDecimal amount) {
    return createIfNotExists(identifier).canRemoveHoldings(amount);
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
  default EconomyResponse canRemoveHoldingsDetail(UUID identifier, BigDecimal amount) {
    return createIfNotExists(identifier).canRemoveHoldings(amount);
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
  default EconomyResponse canRemoveHoldingsDetail(String identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).canRemoveHoldings(amount, world);
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
  default EconomyResponse canRemoveHoldingsDetail(UUID identifier, BigDecimal amount, String world) {
    return createIfNotExists(identifier).canRemoveHoldings(amount, world);
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
  default EconomyResponse canRemoveHoldingsDetail(String identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).canRemoveHoldings(amount, getCurrency(currency, world).get(), world);
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
  default EconomyResponse canRemoveHoldingsDetail(UUID identifier, BigDecimal amount, String world, String currency) {
    return createIfNotExists(identifier).canRemoveHoldings(amount, getCurrency(currency, world).get(), world);
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
    return format(amount, getCurrency(currency, world).get(), world);
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
   * Attempt to find a {@link TransactionResult}.
   * @param name The name of the {@link TransactionResult result}.
   * @return Optional with {@link TransactionResult result} if exists, or empty Optional.
   */
  Optional<TransactionResult> findTransactionResult(String name);

  /**
   * Used to remove a tax exception from an account from a given {@link TransactionType}.
   * @param identifier The identifier associated with the account that should have the exception removed
   * from it.
   * @param transactionType The name of the {@link TransactionType} to remove the exception from.
   * @return True if the tax exception was removed successfully, otherwise false.
   */
  boolean removeTaxException(String identifier, String transactionType);

  /**
   * Used to register an exception to taxation of a certain {@link TransactionType} for an account with
   * the specified identifier.
   * @param identifier The identifier associated with the account that should have the exception placed
   * on it.
   * @param transactionType The name of the {@link TransactionType} to add the exception for.
   * @param exception The new {@link TaxEntry} for the account in the exception.
   * @return True if the tax exception was registered successfully, otherwise false.
   */
  boolean registerTaxException(String identifier, String transactionType, TaxEntry exception);

  /**
   * Used to register a {@link TaxType taxType} to the economy provider.
   * @param taxType The {@link TaxType taxType} to register.
   * @return True if it was registered, otherwise false.
   */
  boolean registerTaxType(TaxType taxType);


  /**
   * Attempt to find a {@link TaxType}.
   * @param name The name of the {@link TaxType tax type}.
   * @return Optional with {@link TaxType tax type} if exists, or empty Optional.
   */
  Optional<TaxType> findTaxType(String name);

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