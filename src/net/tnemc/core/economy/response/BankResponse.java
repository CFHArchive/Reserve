package net.tnemc.core.economy.response;

public enum BankResponse implements EconomyResponse {

  /**
   * The action failed because the bank account doesn't exist.
   */
  INVALID_BANK {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The specified bank account doesn't exist.";
    }
  },

  /**
   * The action failed because the accessor can't access the specified bank account.
   */
  INVALID_ACCESS {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The specified accessor doesn't have access to this bank account.";
    }
  }
}