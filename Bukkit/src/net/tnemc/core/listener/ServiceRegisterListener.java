package net.tnemc.core.listener;

import net.tnemc.core.Reserve;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.ServiceRegisterEvent;

public class ServiceRegisterListener {

  private Reserve plugin;

  public ServiceRegisterListener(Reserve plugin) {
    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onEvent(final ServiceRegisterEvent event) {

  }
}