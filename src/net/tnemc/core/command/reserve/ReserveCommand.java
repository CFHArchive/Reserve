package net.tnemc.core.command.reserve;

import com.github.tnerevival.commands.TNECommand;
import net.tnemc.core.Reserve;

/**
 * Created by creatorfromhell on 10/16/2017.
 * All rights reserved.
 **/
public class ReserveCommand extends TNECommand {

  public ReserveCommand(Reserve plugin) {
    super(plugin);
    subCommands.add(new ReserveEconomyCommand(plugin));
  }

  @Override
  public String getName() {
    return "reserve";
  }

  @Override
  public String[] getAliases() {
    return new String[] { "rsv" };
  }

  @Override
  public String getNode() {
    return "reserve.admin";
  }

  @Override
  public boolean console() {
    return true;
  }
}