package com.isw.app.application.helpers;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import com.isw.app.domain.enums.TLQPath;

public class BufferedHelper {
  
  public static BufferedReader getReader(TLQPath file) throws IOException {
    FileReader reader = new FileReader(file.getFilename());
    return new BufferedReader(reader);
  }

  public static BufferedWriter getWriter(TLQPath file) throws IOException {
    FileWriter writer = new FileWriter(file.getFilename(), true);
    return new BufferedWriter(writer);
  }
}