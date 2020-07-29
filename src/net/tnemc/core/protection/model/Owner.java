package net.tnemc.core.protection.model;

import java.util.UUID;

public class Owner {

  /**
   * The identifier of the owner.
   */
  private final UUID identifier;

  /**
   * The owner type associated with this owner object.
   * Example: player, entity type, npc, town for Towny, faction for Factions, etc.
   */
  private final String type;

  public Owner(UUID identifier, String type) {
    this.identifier = identifier;
    this.type = type;
  }

  public UUID getIdentifier() {
    return identifier;
  }

  public String getType() {
    return type;
  }
}