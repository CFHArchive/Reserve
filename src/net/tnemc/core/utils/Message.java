package net.tnemc.core.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by creatorfromhell on 8/9/2017.
 *
 * Reserve API
 *
 * Copyright (C) 2021 creatorfromhell
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA, or see
 * it at <https://www.gnu.org/licenses/lgpl-3.0.txt>.
 **/
public class Message {

  public static final Map<String, String> colours;

  static {
    colours = new HashMap<>();
    //Colour Characters
    colours.put("<aqua>", ChatColor.AQUA.toString());
    colours.put("<black>", ChatColor.BLACK.toString());
    colours.put("<blue>", ChatColor.BLUE.toString());
    colours.put("<dark_aqua>", ChatColor.DARK_AQUA.toString());
    colours.put("<dark_blue>", ChatColor.DARK_BLUE.toString());
    colours.put("<dark_gray>", ChatColor.GRAY.toString());
    colours.put("<dark_grey>", ChatColor.GRAY.toString());
    colours.put("<dark_green>", ChatColor.DARK_GREEN.toString());
    colours.put("<dark_purple>", ChatColor.DARK_PURPLE.toString());
    colours.put("<dark_red>", ChatColor.DARK_RED.toString());
    colours.put("<gold>", ChatColor.GOLD.toString());
    colours.put("<gray>", ChatColor.GRAY.toString());
    colours.put("<grey>", ChatColor.GRAY.toString());
    colours.put("<green>", ChatColor.GREEN.toString());
    colours.put("<purple>", ChatColor.LIGHT_PURPLE.toString());
    colours.put("<red>", ChatColor.RED.toString());
    colours.put("<white>", ChatColor.WHITE.toString());
    colours.put("<yellow>", ChatColor.YELLOW.toString());

    //Special Characters
    colours.put("<magic>", ChatColor.MAGIC.toString());
    colours.put("<bold>", ChatColor.BOLD.toString());
    colours.put("<strike>", ChatColor.STRIKETHROUGH.toString());
    colours.put("<underline>", ChatColor.UNDERLINE.toString());
    colours.put("<italic>", ChatColor.ITALIC.toString());
    colours.put("<reset>", ChatColor.RESET.toString());
  }

  private final HashMap<String, String> variables = new HashMap<>();
  private final String node;

  public Message(String node) {
    this.node = node;
  }

  public void addVariable(String variable, String replacement) {
    variables.put(variable, replacement);
  }

  public static String replaceColours(String message, boolean strip) {
    for (Map.Entry<String, String> entry : colours.entrySet()) {
      String replacement = (strip) ? "" : entry.getValue();
      message = message.replace(entry.getKey(), replacement);
    }
    if (strip) {
      return ChatColor.stripColor(message);
    }
    return ChatColor.translateAlternateColorCodes('&', message);
  }

  public String grab(String world, CommandSender sender) {
    String message = this.node;

    for (Map.Entry<String, String> entry : variables.entrySet()) {
      message = message.replace(entry.getKey(), entry.getValue());
    }
    return message;
  }

  public String[] grabWithNew(String world, CommandSender sender) {
    String[] message = new String[]{this.node};

    String[] formatted = new String[message.length];

    for (int i = 0; i < message.length; i++) {
      String send = message[i];
      if (!send.equals(this.node)) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
          send = send.replace(entry.getKey(), entry.getValue());
        }
      }
      boolean strip = !(sender instanceof Player);
      formatted[i] = replaceColours(send, strip);
    }
    return formatted;
  }

  public void translate(String world, CommandSender sender) {
    if (sender == null) return;

    String[] message = new String[]{this.node};
    for (String s : message) {
      String send = s;
      if (!send.equals(this.node)) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
          send = send.replace(entry.getKey(), entry.getValue());
        }
      }
      boolean strip = !(sender instanceof Player);
      sender.sendMessage(replaceColours(send, strip));
    }
  }
}