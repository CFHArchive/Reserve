package net.tnemc.core.generic;

import java.util.UUID;

/**
 * Represents an object that is unique and able to be identified via UUID and/or human-friendly
 * identifying string.
 *
 * @author creatorfromhell
 */
public interface Identifiable {

  /**
   * @return The unique identifier for this object.
   */
  String identifier();

  /**
   * @return The human-friendly identifier for this object. This is not unique.
   */
  String name();
}