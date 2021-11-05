package net.tnemc.core;

import net.tnemc.core.configuration.CommentedConfiguration;
import net.tnemc.core.configuration.ConfigNodes;
import net.tnemc.core.utils.FileMgmt;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

import static java.util.logging.Level.SEVERE;

public class ConfigurationManager {

  private static CommentedConfiguration config, newConfig;
  private static JavaPlugin plugin;

  public static void initialize(JavaPlugin plugin) {
    ConfigurationManager.plugin = plugin;
  }

  protected static boolean loadSettings() {
    try {
      FileMgmt.checkFolders(new String[]{
          getRootFolder()});

      loadConfig(getRootFolder() + FileMgmt.fileSeparator() + "config.yml", plugin.getDescription().getVersion());
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  private static void loadConfig(String filepath, String version) {
    File file = FileMgmt.CheckYMLExists(new File(filepath));

    // read the config.yml into memory
    config = new CommentedConfiguration(file);
    if (!config.load()) {
      plugin.getLogger().log(SEVERE, "Failed to load configuration");
    }

    setDefaults(version, file);

    config.save();
  }

  /**
   * Builds a new config reading old config data.
   */
  private static void setDefaults(String version, File file) {
    newConfig = new CommentedConfiguration(file);
    newConfig.load();

    for (ConfigNodes root : ConfigNodes.values()) {
      if (root.getComments().length > 0) {
        addComment(root.getRoot(), root.getComments());
      }
      if (root.getRoot().equals(ConfigNodes.VERSION.getRoot())) {
        setNewProperty(root.getRoot(), version);
      } else if (root.getRoot().equals(ConfigNodes.LAST_RUN_VERSION.getRoot())) {
        setNewProperty(root.getRoot(), getLastRunVersion(version));
      } else
        setNewProperty(root.getRoot(), (config.get(root.getRoot().toLowerCase()) != null) ? config.get(root.getRoot().toLowerCase()) : root.getDefault());
    }

    config = newConfig;
    newConfig = null;
  }

  private static void addComment(String root, String... comments) {
    newConfig.addComment(root.toLowerCase(), comments);
  }

  private static void setNewProperty(String root, Object value) {
    if (value == null) {
      // System.out.print("value is null for " + root.toLowerCase());
      value = "";
    }
    newConfig.set(root.toLowerCase(), value.toString());
  }

  private static String getLastRunVersion(String currentVersion) {
    return getString(ConfigNodes.LAST_RUN_VERSION.getRoot(), currentVersion);
  }

  private static String getString(String root, String def) {
    String data = config.getString(root.toLowerCase(), def);
    if (data == null) {
      sendError(root.toLowerCase() + " from config.yml");
      return "";
    }
    return data;
  }

  private static void sendError(String msg) {
    plugin.getLogger().log(SEVERE, "Error could not read " + msg);
  }

  public static String getRootFolder() {
    return plugin.getDataFolder().getPath();
  }

  public static String getDataFolder() {
    return getRootFolder() + FileMgmt.fileSeparator() + "data";
  }


  public static String getString(ConfigNodes node) {
    return config.getString(node.getRoot().toLowerCase(), node.getDefault());
  }

  public static boolean getBoolean(ConfigNodes node) {
    return Boolean.parseBoolean(config.getString(node.getRoot().toLowerCase(), node.getDefault()));
  }

  public static double getDouble(ConfigNodes node) {
    try {
      return Double.parseDouble(config.getString(node.getRoot().toLowerCase(), node.getDefault()).trim());
    } catch (NumberFormatException e) {
      sendError(node.getRoot().toLowerCase() + " from config.yml");
      return 0.0;
    }
  }

  public static int getInt(ConfigNodes node) {
    try {
      return Integer.parseInt(config.getString(node.getRoot().toLowerCase(), node.getDefault()).trim());
    } catch (NumberFormatException e) {
      sendError(node.getRoot().toLowerCase() + " from config.yml");
      return 0;
    }
  }
}
