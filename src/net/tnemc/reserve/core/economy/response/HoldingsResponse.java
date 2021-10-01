package net.tnemc.reserve.core.economy.response;

public enum HoldingsResponse implements EconomyResponse {

  /**
   * The action was unsuccessful due to the account going over the max supported holdings.
   */
  MAX_HOLDINGS {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The holdings change would put this account above the max allowed holdings.";
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

    @Override
    public String response() {
      return "The holdings change would put this account below the minimum allowed holdings.";
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

    @Override
    public String response() {
      return "The specified account has insufficient funds.";
    }
  }
}
