package net.tnemc.core.command.reserve;

import net.tnemc.core.Reserve;
import net.tnemc.core.command.TNECommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created by creatorfromhell on 12/7/2017.
 * All rights reserved.
 **/
public class ReserveSetCommand extends TNECommand {

  public ReserveSetCommand(Reserve plugin) {
    super(plugin);
  }

  @Override
  public String getName() {
    return "set";
  }

  @Override
  public String[] getAliases() {
    return new String[0];
  }

  @Override
  public String getNode() {
    return "reserve.admin.set";
  }

  @Override
  public boolean console() {
    return true;
  }

  @Override
  public String getHelp() {
    return ChatColor.GOLD + "/reserve set <name> " + ChatColor.WHITE + "- Set the active economy provider to the one specified.";
  }

  @Override
  public boolean execute(CommandSender sender, String command, String[] arguments) {
    if(Reserve.instance().getProviders().containsKey(arguments[0])) {
      Reserve.instance().setProvider(arguments[0]);
      sender.sendMessage(ChatColor.WHITE + "Successfully set economy provider to " + arguments[0] + ".");
      return true;
    }
    sender.sendMessage(ChatColor.RED + "No economy provider found with the name of " + arguments[0] + ".");
    return false;
  }
}