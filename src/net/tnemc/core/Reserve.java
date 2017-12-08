package net.tnemc.core;

import com.github.tnerevival.TNELib;
import net.tnemc.core.command.reserve.ReserveCommand;
import net.tnemc.core.economy.EconomyAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by creatorfromhell on 8/9/2017.
 * All rights reserved.
 **/
public class Reserve extends TNELib {

  private static Reserve instance;
  private EconomyAPI economy = null;

  private Map<String, EconomyAPI> registeredProviders = new HashMap<>();
  private String ecoProvider = null;

  public void onEnable() {
    instance = this;
    super.onEnable();
    registerCommand(new String[] { "reserve", "rsv" }, new ReserveCommand(this));
  }

  public static Reserve instance() {
    return instance;
  }

  public void registerProvider(EconomyAPI provider) {
    getLogger().info("Economy Provider registered: " + provider.name());
    registeredProviders.put(provider.name(), provider);
  }

  public Map<String, EconomyAPI> getProviders() {
    return registeredProviders;
  }

  public Optional<EconomyAPI> economy() {
    return Optional.of(registeredProviders.get(ecoProvider));
  }

  public boolean economyProvided() {
    return ecoProvider != null;
  }
}