package net.tnemc.core.permissions.node.hierarchy;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by creatorfromhell on 12/23/2017.
 * <p>
 * Reserve API
 * <p>
 * Copyright (C) 2018 creatorfromhell
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
public class NodeEntry {

  /**
   * A collection of node strings that this entry is for.
   */
  Collection<String> nodes;

  /**
   * The depth in the node string that this entry coincides with.
   * Example: text in the node "plugin.chat.text" would have a depth of 3.
   */
  private int depth;

  /**
   * The wildcard that would go along with nodes at this depth.
   * Example: NodeEntry of depth 2 with nodes of "plugin.test", "plugin.trial", "plugin.example" would have a wildcard
   * of "plugin.*".
   */
  private String wildcard;

  public NodeEntry(int depth) {
    this(depth, "");
  }

  public NodeEntry(int depth, String wildcard) {
    this.depth = depth;
    this.wildcard = wildcard;
    this.nodes = new ArrayList<>();
  }

  public boolean hasWildcard() {
    return !wildcard.trim().equalsIgnoreCase("");
  }

  public void addNode(String node) {
    nodes.add(node);
  }

  public boolean removeNode(String node) {
    return nodes.remove(node);
  }

  public Collection<String> getNodes() {
    return nodes;
  }

  public void setNodes(Collection<String> nodes) {
    this.nodes = nodes;
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public String getWildcard() {
    return wildcard;
  }

  public void setWildcard(String wildcard) {
    this.wildcard = wildcard;
  }
}