package net.tnemc.core;

import com.google.inject.Inject;
import net.tnemc.core.economy.strict.EconomyAdapter;
import net.tnemc.core.service.ReserveEconomyService;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.service.ChangeServiceProviderEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.service.ProviderRegistration;
import org.spongepowered.api.service.economy.EconomyService;

import java.util.Optional;

@Plugin(id = "reserve", name = "Reserve", version = "1.0", description = "A modern set of APIs to interconnect Spigot plugins. This plugin is meant to connect that system to Sponge's existing APIs.")
public class Reserve {

  private static Reserve instance;

  private boolean providing = false;
  private EconomyAdapter econAPI;
  private ReserveEconomyService econService;

  @Inject
  Logger logger;

  @Listener
  public void onServerStart(GameStartedServerEvent event) {
    econService = new ReserveEconomyService();

    //initialize here.
    Optional<EconomyAdapter> api = Sponge.getServiceManager().provide(EconomyAdapter.class);

    if(api.isPresent()) {
      this.econAPI = api.get();
      provide();
    }
  }

  @Listener
  public void onServiceUpdated(ChangeServiceProviderEvent event) {
    if(event.getService().equals(EconomyAdapter.class)) {

      if(event.isReplacement()) {

        final EconomyAdapter newAdapter = ((EconomyAdapter)event.getNewProvider());

        Optional<ProviderRegistration<?>> old = event.getPreviousProviderRegistration();

        if(old.isPresent()) {
          final EconomyAdapter oldAdapter = ((EconomyAdapter)old.get().getProvider());

          if(newAdapter.override()) {
            if(newAdapter.priority() > oldAdapter.priority() || !oldAdapter.override()) {

              this.econAPI = newAdapter;

            } else {
              Sponge.getServiceManager().setProvider(old.get().getPlugin(), EconomyAdapter.class, oldAdapter);
            }
          } else {
            Sponge.getServiceManager().setProvider(old.get().getPlugin(), EconomyAdapter.class, oldAdapter);
          }
        }
      }

      if(!providing && econAPI != null) {
        provide();
      }
    }
  }

  private void provide() {
    providing = true;
    Sponge.getServiceManager().setProvider(this, EconomyService.class, econService);
  }

  public static Reserve instance() {
    return instance;
  }

  public static EconomyAdapter adapter() {
    return instance.econAPI;
  }
}