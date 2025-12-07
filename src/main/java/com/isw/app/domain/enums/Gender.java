package com.isw.app.domain.enums;

import java.util.Arrays;

public enum Gender {
  MALE("Masculino"),
  FEMALE("Femenino");

  private final String display;

  Gender(String display) {
    this.display = display;
  }

  public String getDisplay() {
    return display;
  }

  public static Gender fromDisplay(String display) {
    return Arrays.stream(Gender.values())
        .filter(g -> g.getDisplay().equals(display))
        .findFirst()
        .orElse(null);
  }
}
