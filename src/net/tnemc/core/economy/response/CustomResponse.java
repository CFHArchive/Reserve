package net.tnemc.core.economy.response;

public class CustomResponse implements EconomyResponse {

  private final boolean success;
  private final String response;

  /**
   * This is a helper class for Reserve implementations to return custom responses during API calls
   * that return an {@link EconomyResponse} object.
   * @param success Whether the action was performed successfully.
   * @param response The message to send describing the response. Example: "Action failed because account
   * doesn't exist"
   */
  public CustomResponse(final boolean success, final String response) {
    this.success = success;
    this.response = response;
  }

  @Override
  public boolean success() {
    return success;
  }

  @Override
  public String response() {
    return response;
  }
}