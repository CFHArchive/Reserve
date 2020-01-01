package net.tnemc.core.economy.response;

public interface EconomyResponse {

  boolean success();

  default String response() {
    return "Default response string";
  }
}