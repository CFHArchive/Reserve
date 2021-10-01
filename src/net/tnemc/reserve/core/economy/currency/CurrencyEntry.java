package net.tnemc.reserve.core.economy.currency;

import org.bukkit.World;

import java.math.BigDecimal;

/**
 * Created by creatorfromhell on 8/17/2017.
 * All rights reserved.
 **/
public  class CurrencyEntry {

  /**
   * The name of the {@link World} this {@link CurrencyEntry} references.
   */
  String world;

  /**
   * The {@link Currency} this {@link CurrencyEntry} involves.
   */
  Currency currency;

  /**
   * The {@link BigDecimal} amount this {@link CurrencyEntry} is for.
   */
  BigDecimal amount;

  public CurrencyEntry(String world, Currency currency, BigDecimal amount) {
    this.world = world;
    this.currency = currency;
    this.amount = amount;
  }

  public String getWorld() {
    return world;
  }

  public void setWorld(String world) {
    this.world = world;
  }

  public Currency getCurrency() {
    return currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public CurrencyEntry copy() {
    return new CurrencyEntry(world, currency, amount);
  }

  public CurrencyEntry copy(BigDecimal newAmount) {
    return new CurrencyEntry(world, currency, newAmount);
  }
}