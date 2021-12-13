package net.tnemc.core.service;

import net.tnemc.core.Reserve;
import net.tnemc.core.model.currency.WrapperCurrency;
import org.spongepowered.api.service.context.ContextCalculator;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.service.economy.account.AccountDeletionResultType;
import org.spongepowered.api.service.economy.account.UniqueAccount;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class ReserveEconomyService implements EconomyService {


  @Override
  public Currency getDefaultCurrency() {
    return new WrapperCurrency(Reserve.adapter().getDefaultCurrency());
  }

  @Override
  public Set<Currency> getCurrencies() {
    Set<Currency> currencies = new HashSet<>();

    for(net.tnemc.core.economy.strict.currency.Currency cur : Reserve.adapter().getCurrencies()) {
      currencies.add(new WrapperCurrency(cur));
    }
    return currencies;
  }

  @Override
  public boolean hasAccount(UUID uuid) {
    return Reserve.adapter().hasAccount(uuid);
  }

  @Override
  public boolean hasAccount(String identifier) {
    return Reserve.adapter().hasAccount(identifier);
  }

  @Override
  public Optional<UniqueAccount> getOrCreateAccount(UUID uuid) {
    return Optional.empty();
  }

  @Override
  public Optional<Account> getOrCreateAccount(String identifier) {
    return Optional.empty();
  }

  @Override
  public AccountDeletionResultType deleteAccount(UUID uuid) {
    return EconomyService.super.deleteAccount(uuid);
  }

  @Override
  public AccountDeletionResultType deleteAccount(String identifier) {
    return EconomyService.super.deleteAccount(identifier);
  }

  @Override
  public void registerContextCalculator(ContextCalculator<Account> calculator) {

  }
}