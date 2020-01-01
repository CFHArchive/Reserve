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

    @Override
    public String response() {
      return "The action was successful.";
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

    @Override
    public String response() {
      return "The action was unsuccessful.";
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

    @Override
    public String response() {
      return "This action is not supported by this Reserve implementation.";
    }
  }
}