package net.tnemc.core.economy.response;

public enum HoldingsResponse implements EconomyResponse {

  /**
   * The action was unsuccessful due to the account going over the max supported holdings.
   */
  MAX_HOLDINGS {
    @Override
    public boolean success() {
      return false;
    }
  },

  /**
   * The action was unsuccessful due to the account going below the minimum holdings.
   */
  MIN_HOLDINGS {
    @Override
    public boolean success() {
      return false;
    }
  },

  /**
   * The action was unsuccessful due to the account not having enough funds.
   */
  INSUFFICIENT {
    @Override
    public boolean success() {
      return false;
    }
  }
}
