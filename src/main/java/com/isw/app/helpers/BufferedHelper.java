package com.isw.app.helpers;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import com.isw.app.enums.DataPath;

public class BufferedHelper {
  
  public static BufferedReader getReader(DataPath file) throws IOException {
    FileReader reader = new FileReader(file.getFilename());
    return new BufferedReader(reader);
  }

  public static BufferedWriter getWriter(DataPath file) throws IOException {
    FileWriter writer = new FileWriter(file.getFilename(), true);
    return new BufferedWriter(writer);
  }
}
