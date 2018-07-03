package net.tnemc.core.chat.json;

import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

/**
 * Created by Daniel.
 *
 * Reserve API
 *
 * Copyright (C) 2018 creatorfromhell
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
public class HoverEvent {

  private final HoverEventType type;
  private final Object value;
  private final JSONObject object;

  public HoverEvent(HoverEventType type, Object value) throws InvalidHoverValueException {
    this.type = type;
    this.value = value;
    this.object = build();
  }

  public JSONObject build() throws InvalidHoverValueException {
    valueCheck(type, value);

    JSONObject object = new JSONObject();
    object.put("action", type.getAction());

    switch(type) {
      case SHOW_ENTITY:
        final Entity entity = (Entity)value;
        JSONObject entityObject = new JSONObject();
        entityObject.put("id", entity.getUniqueId().toString());
        entityObject.put("type", "minecraft:" + entity.getType().toString().toLowerCase());
        entityObject.put("name", entity.getCustomName());
        object.put("value", entityObject);
        break;
      case SHOW_ITEM:
        //TODO: show_item hover event.
        break;
      case SHOW_TEXT:
        object.put("value", ((JSONTextComponent)value).getObject());
        break;
    }
    return object;
  }

  public void valueCheck(HoverEventType type, Object value) throws InvalidHoverValueException {
    switch(type) {
      case SHOW_ENTITY:
        if(!(value instanceof Entity)) throw new InvalidHoverValueException(type, "org.bukkit.entity.Entity");
        break;
      case SHOW_ITEM:
        if(!(value instanceof ItemStack)) throw new InvalidHoverValueException(type, "org.bukkit.inventory.ItemStack");
        break;
      case SHOW_TEXT:
        if(!(value instanceof JSONTextComponent)) throw new InvalidHoverValueException(type, "net.tnemc.core.chat.json.JSONTextComponent");
        break;
      default:
        break;
    }
  }

  public HoverEventType getType() {
    return type;
  }

  public Object getValue() {
    return value;
  }

  public JSONObject getObject() {
    return object;
  }
}