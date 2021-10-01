package net.tnemc.reserve.core.economy.transaction.type;

import net.tnemc.reserve.core.Reserve;
import net.tnemc.reserve.core.economy.ExtendedEconomyAPI;
import net.tnemc.reserve.core.economy.currency.CurrencyEntry;
import net.tnemc.reserve.core.economy.response.CustomResponse;
import net.tnemc.reserve.core.economy.response.EconomyResponse;
import net.tnemc.reserve.core.economy.tax.TaxEntry;
import net.tnemc.reserve.core.economy.transaction.Transaction;
import net.tnemc.reserve.core.economy.transaction.TransactionAffected;
import net.tnemc.reserve.core.economy.transaction.result.CustomTransactionResult;
import net.tnemc.reserve.core.economy.transaction.result.TransactionResult;

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
   * @return True if the recipient, or initiator contain the console
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
        proceed = api.createIfNotExists(transaction.initiator()).canCharge(transaction.initiatorCharge().copy(true)).success();
      }
      if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
        if (affected().equals(TransactionAffected.BOTH) && proceed || affected().equals(TransactionAffected.RECIPIENT)) {
          proceed = api.createIfNotExists(transaction.recipient()).canCharge(transaction.recipientCharge().copy(true)).success();
        }
      }


      if (proceed) {
        if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {
          api.createIfNotExists(transaction.initiator()).handleCharge(transaction.initiatorCharge().copy(true));
        }
        if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
          api.createIfNotExists(transaction.recipient()).handleCharge(transaction.recipientCharge().copy(true));
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
    EconomyResponse response = new CustomResponse(false, "placeholder");

    if(Reserve.instance().economyProvided() && Reserve.instance().economy().supportTransactions()) {

      ExtendedEconomyAPI api = (ExtendedEconomyAPI)Reserve.instance().economy();

      if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {

        final Optional<TaxEntry> calculated = calculateInitiatorTax(transaction.initiator());
        if(calculated.isPresent()) {
          CurrencyEntry entry = transaction.initiatorCharge().getEntry();
          entry.setAmount(calculated.get().getType().handleTaxation(entry.getAmount(), calculated.get().getTax()));
          transaction.initiatorCharge().setEntry(entry);
        }

        response = api.createIfNotExists(transaction.initiator()).canCharge(transaction.initiatorCharge());
      }
      if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
        if (affected().equals(TransactionAffected.BOTH) && response.success() || affected().equals(TransactionAffected.RECIPIENT)) {

          final Optional<TaxEntry> calculated = calculateRecipientTax(transaction.recipient());
          if(calculated.isPresent()) {
            CurrencyEntry entry = transaction.recipientCharge().getEntry();
            entry.setAmount(calculated.get().getType().handleTaxation(entry.getAmount(), calculated.get().getTax()));
            transaction.recipientCharge().setEntry(entry);
          }

          response = api.createIfNotExists(transaction.recipient()).canCharge(transaction.recipientCharge());
        }
      }


      if (response.success()) {
        if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {
          api.createIfNotExists(transaction.initiator()).handleCharge(transaction.initiatorCharge());
        }

        if (affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
          api.createIfNotExists(transaction.recipient()).handleCharge(transaction.recipientCharge());
        }
      }

    }
    return new CustomTransactionResult("generated", response.response(), response.responseRecipient(), response.success());
  }
}