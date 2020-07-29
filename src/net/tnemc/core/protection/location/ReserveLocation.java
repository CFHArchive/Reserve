package net.tnemc.core.protection.location;

public class ReserveLocation {

  private final String world;

  private final int x;
  private final int y;
  private final int z;

  public ReserveLocation(final String world, final int x, final int y, final int z) {
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public String getWorld() {
    return world;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getZ() {
    return z;
  }

  public int getChunkX() {
    return Math.floorDiv(x, 16);
  }

  public int getChunkZ() {
    return Math.floorDiv(z, 16);
  }

  public int getChunkletY() {
    return Math.floorDiv(y, 16);
  }
}