package com.isw.app.contexts;

import com.isw.app.models.Customer;

public class AuthContext {
  private static Customer user;

  public static void clearUser() {
    user = null;
  }

  public static Customer getUser() {
    return user;
  }

  public static void setUser(Customer user) {
    AuthContext.user = user;
  }
}
