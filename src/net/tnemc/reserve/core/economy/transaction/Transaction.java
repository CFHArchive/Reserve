package net.tnemc.reserve.core.economy.transaction;

import net.tnemc.reserve.core.Reserve;
import net.tnemc.reserve.core.economy.ExtendedEconomyAPI;
import net.tnemc.reserve.core.economy.currency.CurrencyEntry;
import net.tnemc.reserve.core.economy.transaction.charge.TransactionCharge;
import net.tnemc.reserve.core.economy.transaction.result.TransactionResult;
import net.tnemc.reserve.core.economy.transaction.type.TransactionType;

import java.util.UUID;

/**
 * Created by creatorfromhell on 8/9/2017.
 * All rights reserved.
 **/
public interface Transaction {



  /**
   * @return The identifier of the initiator's account. This may be a {@link UUID}, or a player's name.
   */
  String initiator();

  /**
   * @return The identifier of the recipient's account. This may be a {@link UUID}, or a player's name.
   */
  String recipient();

  /**
   * A {@link CurrencyEntry} containing the initiator's balance before the {@link Transaction} was performed.
   * @return The {@link CurrencyEntry} that contained the initiator's balance before the {@link Transaction} has been performed.
   */
  CurrencyEntry initiatorBalance();

  /**
   * Used to set the {@link CurrencyEntry} containing the initiator's initial balance.
   * @param entry  The {@link CurrencyEntry} to set as the initiator's initial balance.
   */
  void setInitiatorBalance(CurrencyEntry entry);

  /**
   * A {@link CurrencyEntry} containing the recipient's balance before the {@link Transaction} was performed.
   * @return The {@link CurrencyEntry} that contained the recipient's balance before the {@link Transaction} has been performed.
   */
  CurrencyEntry recipientBalance();

  /**
   * Used to set the {@link CurrencyEntry} containing the recipient's initial balance.
   * @param entry  The {@link CurrencyEntry} to set as the recipient's initial balance.
   */
  void setRecipientBalance(CurrencyEntry entry);

  /**
   * @return The charge that will be performed on the initiator's balance.
   */
  TransactionCharge initiatorCharge();

  /**
   * Used to set the initiator's {@link TransactionCharge}.
   * @param charge The {@link TransactionCharge} to use instead.
   */
  void setInitiatorCharge(TransactionCharge charge);

  /**
   * @return The charge that will be performed on the recipient's balance.
   */
  TransactionCharge recipientCharge();

  /**
   * Used to set the recipient's {@link TransactionCharge}.
   * @param charge The {@link TransactionCharge} to use instead.
   */
  void setRecipientCharge(TransactionCharge charge);

  /**
   * @return True if this transaction has been voided before.
   */
  boolean voided();

  /**
   * Used to set whether this {@link Transaction} has been voided or not.
   * @param voided Whether or not this {@link Transaction} has been voided.
   */
  void setVoided(boolean voided);


  /**
   * This method checks to see if the transaction was voided before, and attempts to void it if not.
   * @return True if the transaction was successfully voided.
   */
  default boolean voidTransaction() {
    if(!voided()) {
      boolean voided = type().voidTransaction(this);
      setVoided(voided);
      return voided;
    }
    return false;
  }

  /**
   * The {@link UUID} of this transaction.
   * @return The {@link UUID} of this transaction.
   */
  UUID transactionID();

  /**
   * The type of transaction being performed.
   * @return The type of transaction being performed.
   */
  TransactionType type();

  /**
   *  The time the transaction was performed in milliseconds.
   * @return The time the transaction was performed in milliseconds.
   */
  long time();

  /**
   * Performs the actual transaction logic.
   * @return The {@link TransactionResult} of the transaction.
   */
  default TransactionResult perform() {
    if(Reserve.instance().economyProvided() && Reserve.instance().economy().supportTransactions()) {
      ExtendedEconomyAPI api = (ExtendedEconomyAPI)Reserve.instance().economy();
      CurrencyEntry recipientInitial = recipientCharge().getEntry();
      recipientInitial.setAmount(api.createIfNotExists(recipient()).getHoldings(recipientInitial.getWorld(), recipientInitial.getCurrency()));
      setRecipientBalance(recipientInitial);

      CurrencyEntry initiatorInitial = recipientCharge().getEntry();
      initiatorInitial.setAmount(api.createIfNotExists(initiator()).getHoldings(initiatorInitial.getWorld(), initiatorInitial.getCurrency()));
      setInitiatorBalance(initiatorInitial);
    }
    return type().perform(this);
  }
}