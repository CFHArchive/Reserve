package net.tnemc.core.generic;

import java.util.UUID;

/**
 * Represents an {@link Identifiable} object that is uniquely identified by a {@link UUID}.
 *
 * @author creatorfromhell
 * @since 1.0.0
 */
public interface Unique extends Identifiable {

  /**
   * @return The {@link UUID} representing this object.
   *
   * @since 1.0.0
   */
  UUID getUniqueID();

  /**
   * @return The unique identifier for this object.
   *
   * @since 1.0.0
   */
  @Override
  default String identifier() {
    return getUniqueID().toString();
  }
}