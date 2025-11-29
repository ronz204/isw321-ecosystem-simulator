package com.isw.app.helpers;

import java.time.LocalDate;
import java.time.Period;

public class LocalDateHelper {
  public static boolean hasRequiredAge(LocalDate birthday, int age) {
    if (birthday == null) return false;
    LocalDate today = LocalDate.now();
    return Period.between(birthday, today).getYears() >= age;
  }
}
