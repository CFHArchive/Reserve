package net.tnemc.core.command.reserve;

import com.github.tnerevival.commands.TNECommand;
import net.tnemc.core.Reserve;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by creatorfromhell on 12/7/2017.
 * All rights reserved.
 **/
public class ReserveLoadedCommand extends TNECommand {

  public ReserveLoadedCommand(Reserve plugin) {
    super(plugin);
  }

  @Override
  public String getName() {
    return "loaded";
  }

  @Override
  public String[] getAliases() {
    return new String[0];
  }

  @Override
  public String getNode() {
    return "reserve.admin.loaded";
  }

  @Override
  public boolean console() {
    return true;
  }

  @Override
  public String getHelp() {
    return ChatColor.GOLD + "/reserve loaded " + ChatColor.WHITE + "- Displays economy providers that are currently registered.";
  }

  @Override
  public boolean execute(CommandSender sender, String command, String[] arguments) {
    String joined = "";
    for(String name : Reserve.instance().getProviders().keySet()) {
      if(!joined.equalsIgnoreCase("")) joined += ", ";
      joined += name;
    }
    sender.sendMessage("Economy Providers: " + joined);
    return true;
  }
}