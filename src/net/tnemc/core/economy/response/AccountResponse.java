package net.tnemc.core.economy.response;

public enum AccountResponse implements EconomyResponse {

  /**
   * The action was successfully completed, and during it an account was created.
   */
  CREATED {
    @Override
    public boolean success() {
      return true;
    }

    @Override
    public String response() {
      return "The specified account was created successfully.";
    }
  },

  /**
   * The action was successfully completed, and during it an account was created.
   */
  CREATION_FAILED {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The specified account couldn't be created.";
    }
  },

  /**
   * The account that was attempted to be created already exists.
   */
  ALREADY_EXISTS {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The specified account already exists.";
    }
  },

  /**
   * The account specified during a call doesn't exist.
   */
  DOESNT_EXIST {
    @Override
    public boolean success() {
      return false;
    }

    @Override
    public String response() {
      return "The specified account doesn't exist.";
    }
  }

}