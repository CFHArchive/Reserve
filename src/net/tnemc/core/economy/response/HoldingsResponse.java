package net.tnemc.core.economy.response;

public enum HoldingsResponse implements EconomyResponse {

  /**
   * The action was successfully performed on the account's holdings.
   */
  SUCCESS {
    @Override
    public boolean success() {
      return true;
    }
  },

  /**
   * The action was unsuccessful due to the account going over the max support holdings.
   */
  MAX_HOLDINGS {
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
  },

  /**
   * The action was unsuccful due to the Reserve implementation not supporting it.
   */
  UNSUPPORTED {
    @Override
    public boolean success() {
      return false;
    }
  }
}
