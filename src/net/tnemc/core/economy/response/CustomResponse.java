package net.tnemc.core.economy.response;

public class CustomResponse implements EconomyResponse {

  private final boolean success;
  private final String response;

  public CustomResponse(boolean success, String response) {
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