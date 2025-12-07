package com.isw.app.application.helpers;

import java.util.Random;

public class RandomHelper {
  private static final Random random = new Random();

  public static int getRandomBetween(int min, int max) {
    return random.nextInt((max - min) + 1) + min;
  }

  public static int getChooseInt(int bound) {
    return random.nextInt(bound);
  }

  public static boolean coinFlip() {
    return random.nextBoolean();
  }
}
