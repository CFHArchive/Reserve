package net.tnemc.core.economy.transaction.type;

import net.tnemc.core.Reserve;
import net.tnemc.core.economy.ExtendedEconomyAPI;
import net.tnemc.core.economy.currency.CurrencyEntry;
import net.tnemc.core.economy.tax.TaxEntry;
import net.tnemc.core.economy.transaction.Transaction;
import net.tnemc.core.economy.transaction.TransactionAffected;
import net.tnemc.core.economy.transaction.result.TransactionResult;

import java.util.Map;
import java.util.Optional;

/**
 * Created by creatorfromhell on 8/9/2017.
 * All rights reserved.
 **/
public interface TransactionType {

  /**
   * @return A map containing the account identifier, and matching {@link TaxEntry} for all tax exceptions
   * for this {@link TransactionType}.
   */
  Map<String, TaxEntry> taxExceptions();

  /**
   * @return The name of this transaction type.
   */
  String name();

  /**
   * @return True if the recipient, or initiator may be null, which is identified as the console.
   */
  boolean console();

  /**
   *
   * @return The {@link TaxEntry} value for the taxes charged to the intiator.
   */
  Optional<TaxEntry> initiatorTax();

  /**
   * Used to calculate a initiator's {@link TaxEntry} after taking into account things such as tax
   * exceptions.
   * @param identifier The identifier of the initiator.
   * @return The final {@link TaxEntry} after lookup is performed.
   */
  default Optional<TaxEntry> calculateInitiatorTax(String identifier) {
    if(taxExceptions().containsKey(identifier)) return Optional.of(taxExceptions().get(identifier));
    return initiatorTax();
  }

  /**
   *
   * @return The {@link TaxEntry} value for the taxes charged to the recipient.
   */
  Optional<TaxEntry> recipientTax();

  /**
   * Used to calculate a recipient's {@link TaxEntry} after taking into account things such as tax
   * exceptions.
   * @param identifier The identifier of the recipient.
   * @return The final {@link TaxEntry} after lookup is performed.
   */
  default Optional<TaxEntry> calculateRecipientTax(String identifier) {
    if(taxExceptions().containsKey(identifier)) return Optional.of(taxExceptions().get(identifier));
    return recipientTax();
  }

  /**
   * @return The {@link TransactionResult} of this transaction if it were to be successful.
   */
  TransactionResult success();

  /**
   * @return The {@link TransactionResult} of this transaction if it were to fail.
   */
  TransactionResult fail();

  /**
   * @return The {@link TransactionAffected} of this transaction type.
   */
  TransactionAffected affected();

  /**
   * Handles the voiding of a transaction.
   * @param transaction The {@link Transaction} to be voided.
   * @return True if this transaction was voided successfully.
   */
  default boolean voidTransaction(Transaction transaction) {
    if(Reserve.instance().economyProvided() && Reserve.instance().economy().supportTransactions()) {
      ExtendedEconomyAPI api = (ExtendedEconomyAPI)Reserve.instance().economy();
      boolean proceed = false;

      if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {
        proceed = api.getAccount(transaction.initiator()).canCharge(transaction.initiatorCharge().copy(true));
      }
      if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
        if (affected().equals(TransactionAffected.BOTH) && proceed || affected().equals(TransactionAffected.RECIPIENT)) {
          proceed = api.getAccount(transaction.recipient()).canCharge(transaction.recipientCharge().copy(true));
        }
      }


      if (proceed) {
        if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {
          api.getAccount(transaction.initiator()).handleCharge(transaction.initiatorCharge().copy(true));
        }
        if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
          api.getAccount(transaction.recipient()).handleCharge(transaction.recipientCharge().copy(true));
        }
        return true;
      }
    }
    return false;
  }


  /**
   * Performs the actual transaction logic.
   * @param transaction The {@link Transaction} to perform.
   * @return The {@link TransactionResult} of this {@link Transaction}.
   */
  default TransactionResult perform(Transaction transaction) {
    if(Reserve.instance().economyProvided() && Reserve.instance().economy().supportTransactions()) {
      ExtendedEconomyAPI api = (ExtendedEconomyAPI)Reserve.instance().economy();
      boolean proceed = false;

      if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {

        final Optional<TaxEntry> calculated = calculateInitiatorTax(transaction.initiator());
        if(calculated.isPresent()) {
          CurrencyEntry entry = transaction.initiatorCharge().getEntry();
          entry.setAmount(calculated.get().getType().handleTaxation(entry.getAmount(), calculated.get().getTax()));
          transaction.initiatorCharge().setEntry(entry);
        }

        proceed = api.getAccount(transaction.initiator()).canCharge(transaction.initiatorCharge());
      }
      if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
        if (affected().equals(TransactionAffected.BOTH) && proceed || affected().equals(TransactionAffected.RECIPIENT)) {

          final Optional<TaxEntry> calculated = calculateRecipientTax(transaction.recipient());
          if(calculated.isPresent()) {
            CurrencyEntry entry = transaction.recipientCharge().getEntry();
            entry.setAmount(calculated.get().getType().handleTaxation(entry.getAmount(), calculated.get().getTax()));
            transaction.recipientCharge().setEntry(entry);
          }

          proceed = api.getAccount(transaction.recipient()).canCharge(transaction.recipientCharge());
        }
      }


      if (proceed) {
        if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {
          api.getAccount(transaction.initiator()).handleCharge(transaction.initiatorCharge());
        }

        if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
          api.getAccount(transaction.recipient()).handleCharge(transaction.recipientCharge());
        }
        return success();
      }
    }
    return fail();
  }
}