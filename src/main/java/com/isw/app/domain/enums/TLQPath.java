package com.isw.app.domain.enums;

import java.nio.file.Paths;

public enum TLQPath {
  ACTIONS("actions.txt"),
  CUSTOMERS("customers.txt"),
  ECOSYSTEM("ecosystems.txt"),;

  private final String filename;

  TLQPath(String filename) {
    this.filename = filename;
  }

  public String getFilename() {
    return Paths.get("src", "main", "java", "com", "isw", "app", "infrastructure", "database", filename).toString();
  }
}
