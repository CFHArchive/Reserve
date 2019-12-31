package net.tnemc.core.economy.response;

public enum GeneralResponse implements EconomyResponse {

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
   * The access was unsuccessful.
   */
  FAILED {
    @Override
    public boolean success() {
      return false;
    }
  },

  /**
   * The action was unsuccessful due to the Reserve implementation not supporting it.
   */
  UNSUPPORTED {
    @Override
    public boolean success() {
      return false;
    }
  }
}