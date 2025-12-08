package com.isw.app.application.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.HashMap;
import java.util.Map;

public class PropertiesHelper {
  private static final Map<String, PropertiesHelper> instances = new HashMap<>();
  private final Properties properties;

  private PropertiesHelper(String fileName) {
    this.properties = loadProperties(fileName);
  }

  public static PropertiesHelper getInstance(String fileName) {
    return instances.computeIfAbsent(fileName, PropertiesHelper::new);
  }

  private Properties loadProperties(String fileName) {
    Properties props = new Properties();
    String path = "properties/" + fileName;

    try (InputStream input = getClass().getClassLoader().getResourceAsStream(path)) {
      if (input == null) {
        throw new IOException("Unable to find " + fileName);
      }
      props.load(input);
    } catch (IOException e) {
      System.err.println("Error loading properties file: " + fileName);
      e.printStackTrace();
    }
    return props;
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public String getProperty(String key, String defaultValue) {
    return properties.getProperty(key, defaultValue);
  }

  public int getPropertyAsInt(String key, int defaultValue) {
    try {
      String value = properties.getProperty(key);
      return value != null ? Integer.parseInt(value) : defaultValue;
    } catch (NumberFormatException e) {
      System.err.println("Invalid integer value for key: " + key);
      return defaultValue;
    }
  }

  public boolean getPropertyAsBoolean(String key, boolean defaultValue) {
    String value = properties.getProperty(key);
    return value != null ? Boolean.parseBoolean(value) : defaultValue;
  }

  public Properties getAllProperties() {
    return new Properties(properties);
  }
}
