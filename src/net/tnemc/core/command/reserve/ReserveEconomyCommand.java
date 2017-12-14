package net.tnemc.core.command.reserve;

import net.tnemc.core.Reserve;
import net.tnemc.core.command.TNECommand;
import net.tnemc.core.economy.EconomyAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by creatorfromhell on 10/16/2017.
 * All rights reserved.
 **/
public class ReserveEconomyCommand extends TNECommand {

  public ReserveEconomyCommand(Reserve plugin) {
    super(plugin);
  }

  @Override
  public String getName() {
    return "economy";
  }

  @Override
  public String[] getAliases() {
    return new String[]{
        "eco"
    };
  }

  @Override
  public String getNode() {
    return "reserve.admin.economy";
  }

  @Override
  public boolean console() {
    return true;
  }

  @Override
  public String getHelp() {
    return ChatColor.GOLD + "/reserve economy " + ChatColor.WHITE + "- Displays the economy service that is currently being used.";
  }

  @Override
  public boolean execute(CommandSender sender, String command, String[] arguments) {
    if(Reserve.instance().economyProvided()) {
      EconomyAPI api = Reserve.instance().economy().get();
      sender.sendMessage(ChatColor.WHITE + "Economy Service: " + ChatColor.GREEN + api.name());
      sender.sendMessage(ChatColor.WHITE + " Supported Reserve Version: " + ChatColor.GREEN + api.version());
      return true;
    }
    sender.sendMessage(ChatColor.WHITE + "There is currently no economy service running.");
    return false;
  }
}