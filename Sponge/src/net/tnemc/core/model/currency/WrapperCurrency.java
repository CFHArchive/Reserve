package net.tnemc.core.model.currency;

import net.tnemc.core.economy.strict.currency.Currency;
import org.spongepowered.api.text.Text;

import java.math.BigDecimal;

public class WrapperCurrency implements org.spongepowered.api.service.economy.Currency {

  private Currency currency;

  public WrapperCurrency(Currency currency) {
    this.currency = currency;
  }

  @Override
  public Text getDisplayName() {
    return Text.of(currency.display());
  }

  @Override
  public Text getPluralDisplayName() {
    return Text.of(currency.displayPlural());
  }

  @Override
  public Text getSymbol() {
    return Text.of(currency.symbol());
  }

  @Override
  public Text format(BigDecimal amount, int numFractionDigits) {
    return Text.of(currency.format(amount, numFractionDigits));
  }

  @Override
  public int getDefaultFractionDigits() {
    return currency.precision();
  }

  @Override
  public boolean isDefault() {
    return currency.isDefault();
  }

  @Override
  public String getId() {
    return currency.identifier();
  }

  @Override
  public String getName() {
    return currency.identifier();
  }
}