package net.tnemc.core.permissions.node;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by creatorfromhell on 12/21/2017.
 * <p>
 * Reserve API
 * <p>
 * Copyright (C) 2017 creatorfromhell
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
public class Node {

  Map<String, Node> childNodes;
  String path;

  public Node(String path) {
    this(path, new HashMap<>());
  }

  public Node(String path, Map<String, Node> childNodes) {
    this.path = path;
    this.childNodes = childNodes;
  }

  public Map<String, Node> getChildNodes() {
    return childNodes;
  }

  public String getPath() {
    return path;
  }
}