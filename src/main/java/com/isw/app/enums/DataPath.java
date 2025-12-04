package com.isw.app.enums;

import java.nio.file.Paths;

public enum DataPath {
  CUSTOMERS("customers.txt"),
  ECOSYSTEMS("ecosystems.txt"),
  OCCURRENCES("occurrences.txt");

  private final String filename;

  DataPath(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return Paths.get("src", "main", "java", "com", "isw", "app", "database", filename).toString();
  }
}
