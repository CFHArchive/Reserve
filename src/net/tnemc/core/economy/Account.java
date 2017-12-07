package net.tnemc.core.economy;

import net.tnemc.core.economy.currency.Currency;
import net.tnemc.core.economy.transaction.charge.TransactionCharge;
import net.tnemc.core.economy.transaction.charge.TransactionChargeType;
import org.bukkit.World;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by creatorfromhell on 12/5/2017.
 * All rights reserved.
 **/
public interface Account {

  /**
   * The unique identifier associated with this account. This may be plugin specific.
   * @return The {@link UUID} unique identifier associated with this account.
   */
  UUID identifier();

  /**
   * @return A readable identifier associated with this account.
   */
  String displayName();

  /**
   * @return Whether or not this account is associated with a player or not.
   */
  boolean playerAccount();

  /**
   * Determines whether or not a player is able to access this account.
   * @param identifier The identifier associated with the player .
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(String identifier);

  /**
   * Determines whether or not a player is able to access this account.
   * @param identifier The {@link UUID} identifier associated with the player .
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(UUID identifier);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The identifier associated with the player .
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  boolean canWithdraw(String identifier);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param identifier The {@link UUID} identifier associated with the player .
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  boolean canWithdraw(UUID identifier);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The identifier associated with the player .
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  boolean canDeposit(String identifier);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param identifier The {@link UUID} identifier associated with the player .
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  boolean canDeposit(UUID identifier);

  /**
   * Determines whether or not a player is able to revoke another player from accessing this account.
   * @param identifier The identifier associated with the player .
   * @return Whether or not the player is able to revoke access this account.
   */
  boolean canRemoveAccessor(String identifier);

  /**
   * Determines whether or not a player is able to revoke another player from accessing this account.
   * @param identifier The {@link UUID} identifier associated with the player .
   * @return Whether or not the player is able to revoke access this account.
   */
  boolean canRemoveAccessor(UUID identifier);

  /**
   * Determines whether or not a player is able to grant other players access this account.
   * @param identifier The identifier associated with the player .
   * @return Whether or not the player is able to grant access this account.
   */
  boolean canAddAccessor(String identifier);

  /**
   * Determines whether or not a player is able to grant other players access this account.
   * @param identifier The {@link UUID} identifier associated with the player .
   * @return Whether or not the player is able to grant access this account.
   */
  boolean canAddAccessor(UUID identifier);

  /**
   * Used to get the balance of an account.
   * @return The balance of the account.
   */
  BigDecimal getHoldings();

  /**
   * Used to get the balance of an account.
   * @param world The name of the {@link World} associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(String world);

  /**
   * Used to get the balance of an account.
   * @param world The name of the {@link World} associated with the balance.
   * @param currency The {@link Currency} object associated with the balance.
   * @return The balance of the account.
   */
  BigDecimal getHoldings(String world, Currency currency);

  /**
   * Used to get the balance of an account.
   * @param currency The {@link Currency} object associated with the balance.
   * @return The balance of the account for the specified {@link Currency}.
   */
  BigDecimal getHoldings(Currency currency);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param amount The amount you wish to use for this check.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(BigDecimal amount);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param amount The amount you wish to use for this check.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(BigDecimal amount, String world);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param amount The amount you wish to use for this check.
   * @param currency The {@link Currency} object associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(BigDecimal amount, Currency currency);

  /**
   * Used to determine if an account has at least an amount of funds.
   * @param amount The amount you wish to use for this check.
   * @param currency The {@link Currency} object associated with the amount.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the account has at least the specified amount of funds, otherwise false.
   */
  boolean hasHoldings(BigDecimal amount, Currency currency, String world);

  /**
   * Used to add funds to an account.
   * @param amount The amount you wish to add to this account.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(BigDecimal amount);

  /**
   * Used to add funds to an account.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(BigDecimal amount, String world);

  /**
   * Used to add funds to an account.
   * @param amount The amount you wish to add to this account.
   * @param currency The {@link Currency} object associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(BigDecimal amount, Currency currency);

  /**
   * Used to add funds to an account.
   * @param amount The amount you wish to add to this account.
   * @param currency The {@link Currency} object associated with the amount.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were added to the account, otherwise false.
   */
  boolean addHoldings(BigDecimal amount, Currency currency, String world);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param amount The amount you wish to add to this account.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param amount The amount you wish to add to this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param amount The amount you wish to add to this account.
   * @param currency The {@link Currency} object associated with the amount.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(BigDecimal amount, Currency currency);

  /**
   * Used to determine if a call to the corresponding addHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param amount The amount you wish to add to this account.
   * @param currency The {@link Currency} object associated with the amount.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding addHoldings method would return true, otherwise false.
   */
  boolean canAddHoldings(BigDecimal amount, Currency currency, String world);

  /**
   * Used to remove funds from an account.
   * @param amount The amount you wish to remove from this account.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(BigDecimal amount);

  /**
   * Used to remove funds from an account.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(BigDecimal amount, String world);

  /**
   * Used to remove funds from an account.
   * @param amount The amount you wish to remove from this account.
   * @param currency The {@link Currency} object associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(BigDecimal amount, Currency currency);

  /**
   * Used to remove funds from an account.
   * @param amount The amount you wish to remove from this account.
   * @param currency The {@link Currency} object associated with the amount.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were removed from the account, otherwise false.
   */
  boolean removeHoldings(BigDecimal amount, Currency currency, String world);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param amount The amount you wish to remove from this account.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(BigDecimal amount);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param amount The amount you wish to remove from this account.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(BigDecimal amount, String world);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param amount The amount you wish to remove from this account.
   * @param currency The {@link Currency} object associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(BigDecimal amount, Currency currency);

  /**
   * Used to determine if a call to the corresponding removeHoldings method would be successful. This method does not
   * affect an account's funds.
   * @param amount The amount you wish to remove from this account.
   * @param currency The {@link Currency} object associated with the amount.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if a call to the corresponding removeHoldings method would return true, otherwise false.
   */
  boolean canRemoveHoldings(BigDecimal amount, Currency currency, String world);

  /**
   * Used to handle an {@link TransactionCharge}. This is mostly a shorthand method.
   * @param charge The {@link TransactionCharge} to handle.
   * @return True if charge is able to be handled successfully, otherwise false.
   */
  default boolean handleCharge(TransactionCharge charge) {
    if(charge.getType().equals(TransactionChargeType.LOSE)) {
      return removeHoldings(charge.getEntry().getAmount(), charge.getCurrency(), charge.getWorld());
    }
    return addHoldings(charge.getEntry().getAmount(), charge.getCurrency(), charge.getWorld());
  }

  /**
   * Used to determine if a call to handleCharge would be successful. This method does not affect an account's funds.
   * @param charge The {@link TransactionCharge} to handle.
   * @return True if a call to handleCharge would return true, otherwise false.
   */
  default boolean canCharge(TransactionCharge charge) {
    if(charge.getType().equals(TransactionChargeType.LOSE)) {
      return canRemoveHoldings(charge.getEntry().getAmount(), charge.getCurrency(), charge.getWorld());
    }
    return canAddHoldings(charge.getEntry().getAmount(), charge.getCurrency(), charge.getWorld());
  }
}