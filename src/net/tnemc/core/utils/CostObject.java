package net.tnemc.core.utils;

import java.math.BigDecimal;

/**
 * Created by Daniel.
 *
 * Reserve API
 *
 * Copyright (C) 2018 creatorfromhell
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 **/
public class CostObject {

  /**
   * The name of the currency to use for this cost object.
   */
  private String currency;

  /**
   * The cost of this cost object.
   */
  private BigDecimal cost;

  /**
   * @param currency The name of the currency to use for this cost object.
   * @param cost The cost of this cost object.
   */
  public CostObject(String currency, BigDecimal cost) {
    this.currency = currency;
    this.cost = cost;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public BigDecimal getCost() {
    return cost;
  }

  public void setCost(BigDecimal cost) {
    this.cost = cost;
  }
}