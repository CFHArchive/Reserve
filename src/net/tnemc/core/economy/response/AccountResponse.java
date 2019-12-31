package net.tnemc.core.economy.response;

public enum AccountResponse implements EconomyResponse {

  /**
   * The action performed on a given account was successful.
   */
  SUCCESS {
    @Override
    public boolean success() {
      return true;
    }
  },

  /**
   * The action was successfully completed, and during it an account was created.
   */
  CREATED {
    @Override
    public boolean success() {
      return true;
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
  },

  /**
   * The account specified during a call doesn't exist.
   */
  DOESNT_EXIST {
    @Override
    public boolean success() {
      return false;
    }
  },

  /**
   * The action is not supported by this Reserve implementation.
   */
  UNSUPPORTED {
    @Override
    public boolean success() {
      return false;
    }
  };

}