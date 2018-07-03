package net.tnemc.core.chat.json;

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
public enum ClickEventType {
  URL("open_url"),
  RUN_COMMAND("run_command"),
  SUGGEST_COMMAND("suggest_command"),
  PAGE("change_page");

  private String action;

  ClickEventType(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }
}