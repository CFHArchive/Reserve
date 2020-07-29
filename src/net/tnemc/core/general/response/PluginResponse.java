package net.tnemc.core.general.response;

public interface PluginResponse {

  /**
   * @return True if the associated action was performed correctly.
   */
  boolean success();

  /**
   * @return The string to return to the performer of the action.
   */
  default String response() {
    return "Default response string";
  }

  /**
   * @return The string to return to the recipient of the action. I.E. the recipient of the payer.
   */
  default String responseRecipient() {
    return "Default response string";
  }
}