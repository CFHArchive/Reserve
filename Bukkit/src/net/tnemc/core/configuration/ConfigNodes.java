package net.tnemc.core.configuration;

/**
 * @author dumptruckman
 */
public enum ConfigNodes {
  VERSION_HEADER("version", "", ""),
  VERSION(
      "version.version",
      "",
      "# This is the current version of TownyEco.  Please do not edit."
  ),
  LAST_RUN_VERSION(
      "version.last_run_version",
      "",
      "# Please do not edit."
  );


  private final String Root;
  private final String Default;
  private final String[] comments;

  ConfigNodes(String root, String def, String... comments) {
    this.Root = root;
    this.Default = def;
    this.comments = comments;
  }

  /**
   * Retrieves the root for a config option
   *
   * @return The root for a config option
   */
  public String getRoot() {
    return Root;
  }

  /**
   * Retrieves the default value for a config path
   *
   * @return The default value for a config path
   */
  public String getDefault() {
    return Default;
  }

  /**
   * Retrieves the comment for a config path
   *
   * @return The comments for a config path
   */
  public String[] getComments() {
    if(comments != null) {
      return comments;
    }

    String[] comments = new String[1];
    comments[0] = "";
    return comments;
  }
}
