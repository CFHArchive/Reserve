package net.tnemc.core.protection.location;

/**
 * Represents a location that has two locations. This respresents everything within the
 * two locations.
 */
public class ReserveCuboidLocation {

  private final ReserveLocation location;
  private final ReserveLocation locationTwo;

  public ReserveCuboidLocation(ReserveLocation location, ReserveLocation locationTwo) {
    this.location = location;
    this.locationTwo = locationTwo;
  }

  public ReserveLocation getLocation() {
    return location;
  }

  public ReserveLocation getLocationTwo() {
    return locationTwo;
  }
}