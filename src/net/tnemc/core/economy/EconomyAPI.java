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
 * Created by creatorfromhell on 8/9/2017.
 * All rights reserved.
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
   * Formats a monetary amount into a more text-friendly version.
   * @param amount The amount of currency to format.
   * @param world The {@link World} in which this format operation is occurring.
   * @return The formatted amount.
   */
  String format(BigDecimal amount, String world);

  /**
   * Formats a monetary amount into a more text-friendly version.
   * @param amount The amount of currency to format.
   * @param currency The {@link Currency} associated with the amount to be formatted.
   * @return The formatted amount.
   */
  String format(BigDecimal amount, Currency currency);

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
  boolean supportTransactions();

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
   * Returns a {@link Map} of all {@link Transaction} objects that have been recorded by this {@link EconomyAPI}
   * implementation.
   *
   * The key is the {@link UUID} of the {@link Transaction}, while the value is the {@link Transaction} object itself.
   *
   * @return A {@link Map} of all recorded {@link Transaction} objects.
   */
  Map<UUID, Transaction> getTransactions();

  /**
   * Returns a {@link Map} of all {@link Transaction} objects that have been recorded by this {@link EconomyAPI}
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