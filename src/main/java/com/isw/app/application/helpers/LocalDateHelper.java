package com.isw.app.application.helpers;

import java.time.Period;
import java.time.LocalDate;

public class LocalDateHelper {
  public static boolean hasRequiredAge(LocalDate birthday, int age) {
    if (birthday == null) return false;
    LocalDate today = LocalDate.now();
    return Period.between(birthday, today).getYears() >= age;
  }
}
