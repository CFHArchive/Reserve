package net.tnemc.core.protection;

import net.tnemc.core.general.response.PluginResponse;
import net.tnemc.core.protection.location.ReserveCuboidLocation;
import net.tnemc.core.protection.location.ReserveLocation;
import net.tnemc.core.protection.model.Owner;

import java.util.Optional;

public interface ProtectionImplementation {

  /**
   * @return The name of the Block Protection implementation.
   */
  String name();

  /**
   * @return The version of Reserve the Block Protection implementation supports.
   */
  String version();

  /**
   * @return Whether or not this implementation is enabled.
   */
  boolean enabled();

  /**
   * @return Whether or not this implementation supports other protection APIs in the world.
   */
  boolean supportOther();

  /**
   * Used to get the owner of the specified location.
   * @param location
   * @return An Optional containing the associated {@link Owner} object, otherwise an empty
   * optional.
   */
  Optional<Owner> getOwner(final ReserveLocation location);

  /**
   * Used to set the owner of the specified location.
   * @param location The location to use.
   * @param owner The owner to set for the location.
   * @return A {@link PluginResponse} pertaining to the action response.
   */
  PluginResponse setOwner(final ReserveLocation location, final Owner owner);

  /**
   * Used to set the owner of the specified {@link ReserveCuboidLocation} location.
   * @param location The {@link ReserveCuboidLocation} to use.
   * @param owner The owner to set for the location.
   * @return A {@link PluginResponse} pertaining to the action response.
   */
  PluginResponse setOwner(final ReserveCuboidLocation location, final Owner owner);


}