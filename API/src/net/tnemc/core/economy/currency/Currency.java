package net.tnemc.core.economy.currency;

import java.math.BigDecimal;

public interface Currency {

  /**
   * @return A unique non-user friendly identifier for the currency.
   */
  String identifier();

  /**
   * @return The currency's symbol.
   */
  String symbol();

  /**
   * @return The currency's decimal character.
   */
  char decimal();

  /**
   * @return The currency's user-friendly display name.
   */
  String display();

  /**
   * @return The plural form of the currency's user-friendly display name.
   */
  String displayPlural();

  /**
   * @return The currency's default number of decimal digits when formatting this currency.
   */
  int precision();

  /**
   * @return Whether this currency is the default currency. This method should use a global
   * context if multi-world support is not present, otherwise it should use the default world
   * for this check.
   */
  boolean isDefault();

  /**
   *
   * @param world The name of the world to use for the check.
   * @return Whether this currency is the default currency for the specified world. This
   * method should default to a global context if multi-world support is not present. If the world
   * does not exist, this should default to the default world.
   */
  boolean isDefault(String world);
  /**
   * Used to translate an amount to a user readable format with the default precision.
   * @param amount The amount to format.
   * @return The formatted text.
   */
  String format(BigDecimal amount);

  /**
   * Used to translate an amount to a user readable format with the specified amount of decimal places.
   * @param amount The amount to format.
   * @param precision The amount of decimal digits to use when formatting.
   * @return The formatted text.
   */
  String format(BigDecimal amount, int precision);
}