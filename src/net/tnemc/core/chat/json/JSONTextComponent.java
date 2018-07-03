package net.tnemc.core.chat.json;

import org.bukkit.ChatColor;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daniel.
 *
 * Reserve API
 *
 * Copyright (C) 2017 creatorfromhell
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU Affero General Public License as published by the Free Software Foundation, either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 **/
public class JSONTextComponent {

  private final String text;
  private final ChatColor[] color;
  private JSONObject object;

  public JSONTextComponent(String text, ChatColor... color) {
    this.text = text;
    this.color = color;
    object = build();
  }

  public JSONObject build() {
    return new JSONObject(buildOptions(text, color));
  }

  public static Map<String, String> buildOptions(String text, ChatColor... color) {
    Map<String, String> textOptions = new HashMap<>();
    textOptions.put("text", text);
    for(ChatColor chatColor : color) {
      switch(chatColor) {
        case BOLD:
          textOptions.put("bold", "true");
          break;
        case ITALIC:
          textOptions.put("italic", "true");
          break;
        case MAGIC:
          textOptions.put("obfuscated", "true");
          break;
        case STRIKETHROUGH:
          textOptions.put("strikethrough", "true");
          break;
        case UNDERLINE:
          textOptions.put("underlined", "true");
          break;
        default:
          textOptions.put("color", chatColor.name().toLowerCase());
          break;
      }
    }
    return textOptions;
  }

  public JSONTextComponent addHover(HoverEvent event) {
    object.put("hoverEvent", event.getObject());
    return this;
  }

  public JSONTextComponent addClick(ClickEvent event) {
    object.put("clickEvent", event.getObject());
    return this;
  }

  public String getText() {
    return text;
  }

  public ChatColor[] getColor() {
    return color;
  }

  public JSONObject getObject() {
    return object;
  }
}