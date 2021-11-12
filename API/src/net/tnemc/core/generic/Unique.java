package net.tnemc.core.generic;

import java.util.UUID;

public interface Unique extends Identifiable {


  /**
   * @return The {@link UUID} representing this object.
   */
  UUID getUniqueID();

  /**
   * @return The unique identifier for this object.
   */
  @Override
  default String identifier() {
    return getUniqueID().toString();
  }
}