package com.isw.app.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogBackHelper {
  private final Logger logger;

  private LogBackHelper(Class<?> clazz) {
    this.logger = LoggerFactory.getLogger(clazz);
  }

  public static LogBackHelper getLogger(Class<?> clazz) {
    return new LogBackHelper(clazz);
  }

  public void info(String message, Object... args) {
    logger.info(message, args);
  }

  public void debug(String message, Object... args) {
    logger.debug(message, args);
  }

  public void warn(String message, Object... args) {
    logger.warn(message, args);
  }

  public void error(String message, Throwable throwable) {
    logger.error(message, throwable);
  }
}
