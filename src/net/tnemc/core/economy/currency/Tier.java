package net.tnemc.core.economy.currency;

/**
 * Created by creatorfromhell on 8/9/2017.
 * All rights reserved.
 **/
public interface Tier {

  /**
   * @return The singular name of this tier.
   */
  String singular();

  /**
   * @return The plural name of this tier.
   */
  String plural();

  /**
   * @return Whether or not this tier is a major tier.
   */
  boolean isMajor();

  /**
   * @return Gets the weight of this tier. A weight of one is equivalent to 1.0 of currency.
   */
  int weight();
}