package net.tnemc.core.economy.lax.currency;

import net.tnemc.core.economy.strict.currency.Currency;

import java.math.BigDecimal;

public class BasicCurrency implements Currency {

  private String identifier;
  private String symbol;
  private char decimal = '.';
  private String display;
  private String plural;
  private int precision = 2;
  private boolean isDefault;

  /**
   * @return A unique non-user friendly identifier for the currency.
   *
   * @since 1.0.0
   */
  @Override
  public String identifier() {
    return null;
  }

  /**
   * @return The currency's symbol.
   *
   * @since 1.0.0
   */
  @Override
  public String symbol() {
    return null;
  }

  /**
   * @return The currency's decimal character.
   *
   * @since 1.0.0
   */
  @Override
  public char decimal() {
    return 0;
  }

  /**
   * @return The currency's user-friendly display name.
   *
   * @since 1.0.0
   */
  @Override
  public String display() {
    return null;
  }

  /**
   * @return The plural form of the currency's user-friendly display name.
   *
   * @since 1.0.0
   */
  @Override
  public String displayPlural() {
    return null;
  }

  /**
   * @return The currency's default number of decimal digits when formatting this currency.
   *
   * @since 1.0.0
   */
  @Override
  public int precision() {
    return 0;
  }

  /**
   * @return Whether this currency is the default currency. This method should use a global
   * context if multi-world support is not present, otherwise it should use the default world
   * for this check.
   *
   * @since 1.0.0
   */
  @Override
  public boolean isDefault() {
    return false;
  }

  /**
   * @param world The name of the world to use for the check.
   *
   * @return Whether this currency is the default currency for the specified world. This
   * method should default to a global context if multi-world support is not present. If the world
   * does not exist, this should default to the default world.
   *
   * @since 1.0.0
   */
  @Override
  public boolean isDefault(String world) {
    return false;
  }

  /**
   * Used to translate an amount to a user readable format with the default precision.
   *
   * @param amount The amount to format.
   *
   * @return The formatted text.
   *
   * @since 1.0.0
   */
  @Override
  public String format(BigDecimal amount) {
    return null;
  }

  /**
   * Used to translate an amount to a user readable format with the specified amount of decimal places.
   *
   * @param amount    The amount to format.
   * @param precision The amount of decimal digits to use when formatting.
   *
   * @return The formatted text.
   *
   * @since 1.0.0
   */
  @Override
  public String format(BigDecimal amount, int precision) {
    return null;
  }
}
