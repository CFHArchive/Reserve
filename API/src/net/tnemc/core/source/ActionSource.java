package net.tnemc.core.source;

public interface ActionSource {

  /**
   *
   * @return The name of the source for a specific action. This could be user-friendly
   * or not. Please note: There is no guarantee of uniqueness.
   */
  String name();

  /**
   *
   * @return The reason for the action that was performed.
   */
  String reason();
}