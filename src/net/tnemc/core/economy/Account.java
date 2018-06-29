package net.tnemc.core.economy;

import net.tnemc.core.economy.currency.Currency;
import net.tnemc.core.economy.transaction.charge.TransactionCharge;
import net.tnemc.core.economy.transaction.charge.TransactionChargeType;
import org.bukkit.World;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by creatorfromhell on 12/5/2017.
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
   * @param account The {@link Account} that we're using in this check.
   * @return Whether or not the player is able to access this account.
   */
  boolean isAccessor(Account account);

  /**
   * Determines whether or not a player is able to withdraw holdings from this account.
   * @param account The {@link Account} that we're using in this check.
   * @return Whether or not the player is able to withdraw holdings from this account.
   */
  boolean canWithdraw(Account account);

  /**
   * Determines whether or not a player is able to deposit holdings into this account.
   * @param account The {@link Account} that we're using in this check.
   * @return Whether or not the player is able to deposit holdings into this account.
   */
  boolean canDeposit(Account account);

  /**
   * Determines whether or not a player is able to revoke another player from accessing this account.
   * @param account The {@link Account} that we're using in this check.
   * @return Whether or not the player is able to revoke access this account.
   */
  boolean canRemoveAccessor(Account account);

  /**
   * Determines whether or not a player is able to grant other players access this account.
   * @param account The {@link Account} that we're using in this check.
   * @return Whether or not the player is able to grant access this account.
   */
  boolean canAddAccessor(Account account);

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
   * Used to set the funds to an account.
   * @param amount The amount you wish to set this accounts's funds to.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(BigDecimal amount);

  /**
   * Used to set the funds to an account.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(BigDecimal amount, String world);

  /**
   * Used to set the funds to an account.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param currency The {@link Currency} object associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(BigDecimal amount, Currency currency);

  /**
   * Used to set the funds to an account.
   * @param amount The amount you wish to set this accounts's funds to.
   * @param currency The {@link Currency} object associated with the amount.
   * @param world The name of the {@link World} associated with the amount.
   * @return True if the funds were set for the account, otherwise false.
   */
  boolean setHoldings(BigDecimal amount, Currency currency, String world);

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