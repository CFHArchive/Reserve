package net.tnemc.core.generic;

import java.util.UUID;

/**
 * Represents an object that is unique and able to be identified via UUID and/or human-friendly
 * identifying string.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface Identifiable {

  /**
   * @return The unique identifier for this object.
   *
   * @since 1.0.0
   */
  String identifier();

  /**
   * @return The human-friendly identifier for this object. This is not unique.
   *
   * @since 1.0.0
   */
  String name();
}