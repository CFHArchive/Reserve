package net.tnemc.core.model.account;

import net.tnemc.core.economy.strict.account.GenericAccount;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.service.context.Context;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.service.economy.account.VirtualAccount;
import org.spongepowered.api.service.economy.transaction.TransactionResult;
import org.spongepowered.api.service.economy.transaction.TransferResult;
import org.spongepowered.api.text.Text;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class WrapperVirtualAccount implements VirtualAccount {

  private GenericAccount account;

  public WrapperVirtualAccount(GenericAccount account) {
    this.account = account;
  }

  @Override
  public Text getDisplayName() {
    return Text.of(account.name());
  }

  @Override
  public BigDecimal getDefaultBalance(Currency currency) {
    return null;
  }

  @Override
  public boolean hasBalance(Currency currency, Set<Context> contexts) {
    return false;
  }

  @Override
  public BigDecimal getBalance(Currency currency, Set<Context> contexts) {
    return null;
  }

  @Override
  public Map<Currency, BigDecimal> getBalances(Set<Context> contexts) {
    return null;
  }

  @Override
  public TransactionResult setBalance(Currency currency, BigDecimal amount, Cause cause, Set<Context> contexts) {
    return null;
  }

  @Override
  public Map<Currency, TransactionResult> resetBalances(Cause cause, Set<Context> contexts) {
    return null;
  }

  @Override
  public TransactionResult resetBalance(Currency currency, Cause cause, Set<Context> contexts) {
    return null;
  }

  @Override
  public TransactionResult deposit(Currency currency, BigDecimal amount, Cause cause, Set<Context> contexts) {
    return null;
  }

  @Override
  public TransactionResult withdraw(Currency currency, BigDecimal amount, Cause cause, Set<Context> contexts) {
    return null;
  }

  @Override
  public TransferResult transfer(Account to, Currency currency, BigDecimal amount, Cause cause, Set<Context> contexts) {
    return null;
  }

  @Override
  public String getIdentifier() {
    return account.identifier();
  }

  @Override
  public Set<Context> getActiveContexts() {
    return null;
  }
}