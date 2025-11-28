package com.isw.app.repositories;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class BaseTLQRepository {
  public static final String DELIMITER = "$$";

  protected static void writeDelimiter(BufferedWriter writer) throws IOException {
    writer.write(DELIMITER);
    writer.newLine();
  }

  protected static void writeField(BufferedWriter writer, String key, String value) throws IOException {
    writer.write(key + ": " + value);
    writer.newLine();
  }

  protected static boolean isDelimiter(String line) {
    return DELIMITER.equals(line != null ? line.trim() : "");
  }

  protected static List<List<String>> readBlocks(BufferedReader reader) throws IOException {
    List<List<String>> blocks = new ArrayList<>();
    List<String> current = null;
    String line;

    while ((line = reader.readLine()) != null) {
      if (isDelimiter(line)) {
        if (current != null && !current.isEmpty()) {
          blocks.add(current);
        }
        current = new ArrayList<>();
      } else if (current != null) {
        current.add(line);
      }
    }
    
    if (current != null && !current.isEmpty()) {
      blocks.add(current);
    }
    return blocks;
  }

  protected static Map<String, String> parseFields(List<String> block) {
    Map<String, String> fields = new HashMap<>();

    for (String line : block) {
      int idx = line.indexOf(":");
      
      if (idx > 0) {
        String key = line.substring(0, idx).trim();
        String value = line.substring(idx + 1).trim();
        fields.put(key, value);
      }
    }
    return fields;
  }
}
