package net.tnemc.core.chat.json;

import org.bukkit.ChatColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
public class JSONMessageBuilder {
  private JSONObject object;
  private JSONArray extra;

  private final String text;
  private final ChatColor[] color;

  public JSONMessageBuilder(String text, ChatColor... color) {
    this.text = text;
    this.color = color;
    this.object = new JSONObject();
    this.extra = new JSONArray();

    build();
  }

  private void build() {
    object.putAll(JSONTextComponent.buildOptions(text, color));
  }

  public void addHover(HoverEvent event) {
    object.put("hoverEvent", event.getObject());
  }

  public void addExtra(JSONTextComponent... extras) {
    for(JSONTextComponent component : extras) {
      extra.add(component.getObject());
    }
  }

  public String getText() {
    return text;
  }

  public ChatColor[] getColor() {
    return color;
  }

  public JSONObject getObject() {
    JSONObject combined = (JSONObject)object.clone();
    combined.put("extra", extra);
    return combined;
  }
}