package com.isw.app.helpers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashingHelper {
  private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

  public static String hash(String password) {
    return encoder.encode(password);
  }

  public static boolean verify(String password, String hashed) {
    return encoder.matches(password, hashed);
  }
}
