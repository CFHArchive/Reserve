package net.tnemc.core.economy.transaction.type;

import net.tnemc.core.Reserve;
import net.tnemc.core.economy.transaction.Transaction;
import net.tnemc.core.economy.transaction.TransactionAffected;
import net.tnemc.core.economy.transaction.result.TransactionResult;

/**
 * Created by creatorfromhell on 8/9/2017.
 * All rights reserved.
 **/
public interface TransactionType {

  /**
   * @return The name of this transaction type.
   */
  String name();

  /**
   * @return True if the recipient, or initiator may be null, which is identified as the console.
   */
  boolean console();

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
    boolean proceed = false;

    if(affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {
      proceed = Reserve.instance().economy().get().getAccount(transaction.initiator()).canCharge(transaction.initiatorCharge().copy(true));
    }
    if(affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
      if(affected().equals(TransactionAffected.BOTH) && proceed || affected().equals(TransactionAffected.RECIPIENT)) {
        proceed = Reserve.instance().economy().get().getAccount(transaction.recipient()).canCharge(transaction.recipientCharge().copy(true));
      }
    }


    if(proceed) {
      if(affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {
        Reserve.instance().economy().get().getAccount(transaction.initiator()).handleCharge(transaction.initiatorCharge().copy(true));
      }
      if(affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
        Reserve.instance().economy().get().getAccount(transaction.recipient()).handleCharge(transaction.recipientCharge().copy(true));
      }
      return true;
    }
    return false;
  }


  /**
   * Performs the actual transaction logic.
   * @param transaction The {@link Transaction} to perform.
   * @return The {@link TransactionResult} of this {@link Transaction}.
   */
  default TransactionResult perform(Transaction transaction) {
    boolean proceed = false;

    if(affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {
      proceed = Reserve.instance().economy().get().getAccount(transaction.initiator()).canCharge(transaction.initiatorCharge());
    }
    if(affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
      if(affected().equals(TransactionAffected.BOTH) && proceed || affected().equals(TransactionAffected.RECIPIENT)) {
        proceed = Reserve.instance().economy().get().getAccount(transaction.recipient()).canCharge(transaction.recipientCharge());
      }
    }


    if(proceed) {
      if(affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.INITIATOR)) {
        Reserve.instance().economy().get().getAccount(transaction.initiator()).handleCharge(transaction.initiatorCharge());
      }

      if(affected().equals(TransactionAffected.BOTH) || affected().equals(TransactionAffected.RECIPIENT)) {
        Reserve.instance().economy().get().getAccount(transaction.recipient()).handleCharge(transaction.recipientCharge());
      }
      return success();
    }
    return fail();
  }
}